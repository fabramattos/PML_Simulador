/**
 *  DAY TRADE
 *      • Parâmetros: Mov, Contra Mov, Qtde, Lim Op, Offset
 *      • Referência para offset: Abertura do candle selecionado (hora/minuto escolhido, ou abertura do dia)
 *      • Lógica da montagem da pos:
 *          o Realiza uma única entrada (a favor ou contra o movimento)
 *          o Caso não tenha feito nenhuma entrada no dia anterior, repete últimos parâmetros utilizados.
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Gerenciamento de Risco, compensando resultados de dias anteriores
 *          o Reposiciona pela abertura do mercado do dia seguinte
 */

package com.pml.Simulacoes;

import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;
import com.pml.simulacao.Candle;

public class Sim_AberturaMercFut extends Simulacao {
    
    public Sim_AberturaMercFut(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
         // VARIAVEIS
        int diaAtual = super.diaAtual;
        int posAnterior = 0;
        
        boolean
        gravouDia = false,
        atualizouAbertura = false;
        
        double
        aberturaSerie = 0;
        
        Ordem ord = new OrdemSimples();
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                //INICIO DE NOVA SÉRIE
                if(!atualizouAbertura){
                    aberturaSerie = Candle.getListaCandleMinuto().get(i).getAbertura();
                    ord = new OrdemSimples();
                    ord.configuraLinhasEntradaESaidas(aberturaSerie);
                    rDia.adicionaOrdemNaLista(ord);
                    atualizouAbertura = true;
                }
                    
                //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
                if(diaOperando && !rDia.isGerRisco())
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);

                // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
                if(i == Candle.getListaCandleMinuto().size()-1
                || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
                || controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                    if(!gravouDia){
                        gravouDia = true;
                        
                        posAnterior = rDia.getPos();
                        gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia); //NESTE CASO, DESFAZ POSIÇÃO E TENTA DIA SEGUINTE
                    }

                    // ULTIMO DADO
                    if(i == Candle.getListaCandleMinuto().size()-1)
                        break;

                    //FECHAMENTO DO DIA
                   if(controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                        //NÃO EXISTE DADOS SEGUINTE PARA SIMULAÇÃO
                        if (diaAtual == Candle.getListaCandleDiario().size())
                            break;
                        
                        diaAtual++;
                        //DIA NAO OPEROU OU NAO TEVE ENTRADA, DIA SEGUINTE COMEÇA NA ABERTURA
                        if(!rDia.isOrdemExecutada()){
                            rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);

                            posAnterior = ord.getQtde();
                            LadoOrdem ladoOrdemAnterior = ord.getLadoOrdem();
                            double offset = ord.getOffset();
                            ord = new OrdemSimples();
                            ord.setLadoOrdem(ladoOrdemAnterior);
                            ord.setQtde(Math.abs(posAnterior));
                            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                            rDia.adicionaOrdemNaLista(ord);
                            atualizouAbertura = true;
                        }else{
                            if(rDia.isGerRisco()){
                                rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                                atualizouAbertura = false;
                            }else{
                                rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, true);

                                ord = new OrdemSimples();
                                ord.setQtde(Math.abs(posAnterior));
                                ord.setTemOffset(false);
                                ord.setOffset(rDia.getAbertura() - aberturaSerie);
                                if(posAnterior > 0)
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                                ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                                atualizouAbertura = true;
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
