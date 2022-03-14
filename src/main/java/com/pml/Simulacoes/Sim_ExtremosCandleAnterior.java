/**
 *  DAY TRADE
 *      • Parâmetros: Pos, Pos Max, Delta
 *      • Lógica da montagem da pos:
 *          o Registra a Máxima e Mínima no intervalo de tempo selecionado (candles de 5, 10, 15 ou 30 min)
 *          o Entra uma única vez ao superar em Delta pontos um dos extremos do candle anterior
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Pelo Gerenciamento de Risco
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

public class Sim_ExtremosCandleAnterior extends Simulacao {
    
    public Sim_ExtremosCandleAnterior(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;

        boolean
        gravouDia = false,
        executouOrdem = false;
        
        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        //INSTANCIA ESTRATEGIA
        OrdemSimples ord = new OrdemSimples();
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){

                //VERIFICA SUPERAÇÃO DA MAX
                if(!executouOrdem && !rDia.getListaOrdensDia().isEmpty()){
                    rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setOffset(Candle.getMaxCandleYMinAnterior() + ConfigOrdens.getDelta() - rDia.getAbertura());
                    if(!ConfigOrdens.isTemContMov())
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.COMPRA);
                    else
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.VENDA);

                    controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);

                    for(Ordem item : rDia.getListaOrdensDia()){
                        if(item.isIniciada()){
                            executouOrdem = true;
                            break;
                        }
                    }
                }
                
                
                //VERIFICAO SUPERAÇÃO DA MIN
                if(!executouOrdem && !rDia.getListaOrdensDia().isEmpty()){
                    rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setOffset(Candle.getMinCandleYMinAnterior() - ConfigOrdens.getDelta() - rDia.getAbertura());
                    if(!ConfigOrdens.isTemContMov())
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.VENDA);
                    else
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.COMPRA);
                
                    controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                    
                    for(Ordem item : rDia.getListaOrdensDia()){
                        if(item.isIniciada()){
                            executouOrdem = true;
                            break;
                        }
                    }
                }
                
                if(Candle.getListaCandleMinuto().get(i).criaCandleIntermediario(ConfigOrdens.getAguardaFormacaoCandle())){
                    rDia.getListaOrdensDia().clear();
                        
                    ord = new OrdemSimples();
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    rDia.adicionaOrdemNaLista(ord);
                    
                    executouOrdem = false;
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

                //ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;

                // FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // NÃO EXISTE DADOS SEGUINTE PARA SIMULAÇÃO
                    if (diaAtual == Candle.getListaCandleDiario().size())
                        break;

                    diaAtual++;
                    rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                    gravouDia = false;
                    executouOrdem = false;
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        } //FIM SIMULAÇÃO
    }
}