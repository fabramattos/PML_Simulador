/**
 *  DAY TRADE
 *      • Parâmetros: Contra Mov, Pos Ini, Pos Max, Offset, Delta
 *      • Referência para offset: Abertura do candle selecionado (hora/minuto escolhido, ou abertura do dia)
 *      • Lógica da montagem da pos:
 *          o Realiza compras e vendas (a favor ou contra o movimento) conforme os preços oscilam a partir da abertura,
 *            respeitando o limite de Pos Max informado
 *          o Primeira ordem de compra ou venda tem offset, as demais seguem o passo informado
 *          o Ao realizar a primeira entrada (offset atingido), as ordens do outro lado são deslocadas para uma distância Delta da primeira entrada
 *          o Ex. Abertura 4000, offset = 10, delta = 5 -> Entrada em 4010 -> Desloca as ordens que estavam abaixo de 3990 para 4005
 *           (primeira entrada 4010 – delta 5 = 4005)
 *          o Não repete preços no dia
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Gerenciamento de Risco
 **/
package Simulacoes;

import Configuracoes.ConfigOrdens;
import Ordens.LadoOrdem;
import java.util.ArrayList;
import java.util.List;
import simulacao.Candle;
import Ordens.OrdemSimples;
import Resumos.ResumoDia;

public class Sim_MontaPos_DayTrade1 extends Simulacao {
    
    public Sim_MontaPos_DayTrade1(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;
        
        boolean
        gravouDia = false,
        entrouCompra = false,
        entrouVenda = false;
        
        double
        deltaCompra = 0,
        deltaVenda = 0;
        
        List listaOffset = new ArrayList<>();
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);

            //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                //ENCONTRANDO PRIMEIRA ENTRADA
                if(!rDia.isAtualizouReferencia()){
                    if(!entrouCompra
                    && (Candle.getListaCandleMinuto().get(i).getMinima() <= (rDia.getAbertura() - ConfigOrdens.getOffset()))){
                        rDia.setAberturaSerie(rDia.getAbertura() - ConfigOrdens.getOffset());
                        entrouVenda = true;
                        deltaCompra = ConfigOrdens.getDelta();
                        deltaVenda = 0;
                    }
                    
                    if(!entrouVenda
                    && (Candle.getListaCandleMinuto().get(i).getMaxima() >= (rDia.getAbertura() + ConfigOrdens.getOffset()))){
                        rDia.setAberturaSerie(rDia.getAbertura() + ConfigOrdens.getOffset());
                        entrouCompra = true;
                        deltaCompra = 0;
                        deltaVenda = ConfigOrdens.getDelta();
                    }
                }
                
                //VERIFICANDO VALORES ACIMA DA ABERTURA DA SÉRIE
                if((entrouCompra || entrouVenda)
                && Candle.getListaCandleMinuto().get(i).getMaxima() >= rDia.getAberturaSerie()){
                    double dif = Candle.getListaCandleMinuto().get(i).getMaxima() - rDia.getAberturaSerie();
                    for(double offset = deltaCompra; offset <= dif; offset += ConfigOrdens.getPassoOffset()){
                        if(listaOffset.contains(offset))
                            continue;
                        OrdemSimples ord = new OrdemSimples();
                        if(!ord.isTemContMov())
                            ord.setLadoOrdem(LadoOrdem.COMPRA);
                        else
                            ord.setLadoOrdem(LadoOrdem.VENDA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());
                        ord.setOffset(offset);
                        controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                        if(ord.isEncerrada())
                            listaOffset.add(offset);
                        gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                    }
                }

                //VERIFICANDO VALORES ABAIXO DA ABERTURA DA SERIE
                if((entrouCompra || entrouVenda)
                && Candle.getListaCandleMinuto().get(i).getMinima() <= rDia.getAberturaSerie()){
                    double dif = Candle.getListaCandleMinuto().get(i).getMinima() - rDia.getAberturaSerie();
                    for(double offset = - deltaVenda; offset >= dif; offset -= ConfigOrdens.getPassoOffset()){
                        if(listaOffset.contains(offset))
                            continue;
                        OrdemSimples ord = new OrdemSimples();
                        if(!ord.isTemContMov())
                            ord.setLadoOrdem(LadoOrdem.VENDA);
                        else
                            ord.setLadoOrdem(LadoOrdem.COMPRA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());
                        ord.setOffset(offset);
                        controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                        if(ord.isEncerrada())
                            listaOffset.add(offset);
                        gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                    }
                }
            }// diasOperando
            
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!gravouDia){
                    gravouDia = true;
                    
                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        diaAtual++;
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                        gravouDia = false;
                        
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                        listaOffset.clear();
                        
                        if(!rDia.isAtualizouReferencia()){
                            entrouCompra = false;
                            entrouVenda = false;
                        }
                    }   
                }
            }
        } //FIM SIMULAÇÃO
    }
}
