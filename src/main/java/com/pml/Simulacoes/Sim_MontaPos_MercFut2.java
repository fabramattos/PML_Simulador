/**
 *  DAY TRADE / SWING TRADE
 *  • Parâmetros: Contra Mov, Pos Ini, Pos Max, Offset
 *  • Referência para offset: Abertura do candle selecionado (hora/minuto escolhido, ou abertura do dia)
 *  • Lógica da montagem da pos:
 *      o Realiza compras e vendas (a favor ou contra o movimento) conforme os preços oscilam a partir
 *        da abertura, respeitando o limite de Pos Max informado
 *      o Não repete preços até sair pelo gerenciamento de risco (alvo ou loss)
 *      o Primeira ordem de compra e venda do dia tem offset, as demais seguem o passo informado a partir da primeira compra e venda
 *  • Lógica da saída da pos:
 *      o Day Trade
 *      o Gerenciamento de risco
 *      o Tenta compensar resultados de dias anteriores alterando o ger. de risco
 *      o Reposiciona pela abertura do mercado do dia seguinte
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import java.util.ArrayList;
import java.util.List;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

public class Sim_MontaPos_MercFut2 extends Simulacao {
    
    public Sim_MontaPos_MercFut2(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial,
        posAnterior = 0;
        
        boolean
        gravouDia = false,
        correcaoEntradaVenda = false,
        atualizouAbertura = false;
        
        double
        aberturaSerie = 0;
        
        List listaPrecos = new ArrayList<>();
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                //INICIO DE NOVA SÉRIE
                if(!atualizouAbertura && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                    aberturaSerie = Candle.getListaCandleMinuto().get(i).getAbertura();
                    atualizouAbertura = true;
                }
                
                //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
                if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                    //VERIFICAÇÕES SE PRECISA GERAR EST INICIAIS OU SE FEZ NOVOS EXTREMOS
                    if(listaPrecos.isEmpty()
                    || Candle.getListaCandleMinuto().get(i).getMaxima() > rDia.getMaxLocal()
                    || Candle.getListaCandleMinuto().get(i).getMinima() < rDia.getMinLocal()){

                        //VERIFICANDO VALORES ACIMA DA ABERTURA
                        if(Candle.getListaCandleMinuto().get(i).getMaxima() - aberturaSerie > 0){
                            for(double preco = aberturaSerie; preco <= Candle.getListaCandleMinuto().get(i).getMaxima(); preco += ConfigOrdens.getPassoOffset()){
                                if(listaPrecos.contains(preco))
                                    continue;
                                OrdemSimples ord = new OrdemSimples();
                                if(!ord.isTemContMov())
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                                ord.configuraLinhasEntradaESaidas(aberturaSerie);
                                ord.setTemOffset(true);
                                ord.setOffset(preco - aberturaSerie + Math.abs(ConfigOrdens.getOffset()));
                                rDia.adicionaOrdemNaLista(ord);
                                listaPrecos.add(preco);
                            }
                        }

                        //VERIFICANDO VALORES ABAIXO DA ABERTURA
                        if(Candle.getListaCandleMinuto().get(i).getMinima() - aberturaSerie < 0){
                            for(double preco = aberturaSerie; preco >= Candle.getListaCandleMinuto().get(i).getMinima(); preco -= ConfigOrdens.getPassoOffset()){
                                if(listaPrecos.contains(preco)){
                                    if(preco != aberturaSerie || ConfigOrdens.getOffset() == 0 || correcaoEntradaVenda)
                                        continue;
                                }   
                                OrdemSimples ord = new OrdemSimples();
                                if(!ord.isTemContMov())
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                ord.configuraLinhasEntradaESaidas(aberturaSerie);
                                ord.setTemOffset(true);
                                ord.setOffset(preco - aberturaSerie - 1*Math.abs(ConfigOrdens.getOffset()));
                                rDia.adicionaOrdemNaLista(ord);
                                listaPrecos.add(preco);
                                //SÓ UMA UNICA VEZ QUE PASSARIA PELO ZERO E ATIVARIA.
                                correcaoEntradaVenda = true;
                            }
                        }
                    } // FIM DA VERIFICAÇÃO E GERAÇÃO DE ESTRATEGIAS OFFSET
                    
                    controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                }// diasOperando

                rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
                Candle.getListaCandleMinuto().get(i).registraResultados(rDia, true);
                
                // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
                if(i == Candle.getListaCandleMinuto().size()-1
                || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
                || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    if(!gravouDia){
                        gravouDia = true;
                        
                        //EVITA PROBLEMA COM DIA NAO OPERANDO OU SEM ENTRADAS
                        if(rDia.getPos() != 0)
                            posAnterior = rDia.getPos();
                        
                        gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia); //NESTE CASO, DESFAZ POSIÇÃO E TENTA DIA SEGUINTE
                    }

                    // ULTIMO DADO
                    if(i == Candle.getListaCandleMinuto().size()-1)
                        break;

                    //FECHAMENTO DO DIA
                    if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                        //NÃO EXISTE DADOS SEGUINTE PARA SIMULAÇÃO
                        if (diaAtual == Candle.getListaCandleDiario().size())
                            break;
                        
                        diaAtual++;
                        if(!rDia.isOrdemExecutada()){
                            rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual++), rDia, true);

                            OrdemSimples ord = new OrdemSimples();
                            ord.setOffset(rDia.getAbertura() - aberturaSerie);
                            ord.configuraLinhasEntradaESaidas(aberturaSerie);
                            rDia.adicionaOrdemNaLista(ord);
                            if(!listaPrecos.contains(rDia.getAbertura()))
                                listaPrecos.add(rDia.getAbertura());
                            atualizouAbertura = true;
                        }else{
                            rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual++), rDia, true);
                            // ATINGIU ALVO OU PREJMAX
                            if(rDia.isGerRisco()){
                                listaPrecos.clear();
                                atualizouAbertura = false;
                                correcaoEntradaVenda = false;
                                //VOLTA A CONFIGURAÇÃO SE TEM OU NAO HORARIO DE ACORDO COM O INICIO
                            }else{
                                atualizouAbertura = true;

                                OrdemSimples ord = new OrdemSimples();
                                ord.setQtde(Math.abs(posAnterior));
                                if(posAnterior > 0)
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                                ord.configuraLinhasEntradaESaidas(aberturaSerie);
                                ord.setTemOffset(true);
                                ord.setOffset(rDia.getAbertura() - aberturaSerie);
                                
                                rDia.adicionaOrdemNaLista(ord);
                                if(!listaPrecos.contains(rDia.getAbertura()))
                                    listaPrecos.add(rDia.getAbertura());
                            }
                        }
                        gravouDia = false;
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                    }
                }
            }
        }// Fim da simulação
    }
}
