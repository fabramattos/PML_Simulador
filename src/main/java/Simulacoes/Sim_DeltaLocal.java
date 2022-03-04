/**
 *  DAY TRADE
 *  • Parâmetros: Qtde, Delta, Qtde Max, G, L
 *  • Lógica da montagem da pos:
 *      o Uma única ordem ativa por vez
 *      o Espera uma diferença (delta) mínima entre um máximo local e um mínimo local.
 *        Entra a favor do movimento após a inversão.
 *        Ex. 4000 → 3995 (delta = 5, permite entrar em 4000 agora) → retorna a 4000 → entrada em 4000
 *      o Só entra novamente se não tiver atingido Pts/Contrato ou PrejMax
 *      o Ao sair, reseta máximo e mínimo local
 *  • Lógica da saída da pos:
 *      o Day Trade
 *      o Pelos parâmetros da ordem
 **/

package Simulacoes;

import Configuracoes.ConfigBase;
import Configuracoes.ConfigOrdens;
import Ordens.LadoOrdem;
import Ordens.Ordem;
import java.util.ArrayList;
import simulacao.Candle;
import Ordens.OrdemOCO;
import Resumos.ResumoDia;

public class Sim_DeltaLocal extends Simulacao {
    
    public Sim_DeltaLocal(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;
        
        boolean 
        diaGravado = false,
        deltaMax = false,
        deltaMin = false,
        ultimoCandle = true;
        
        double
        offsetMax = 0,
        offsetMin = 0,
        maxLocal = -1*Float.MAX_VALUE,
        minLocal = Float.MAX_VALUE;
        
        ArrayList listaPrecos = new ArrayList();
        
        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));

        // CONFIGURA ESTRATEGIA
        Ordem ord = new OrdemOCO();
        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
        rDia.adicionaOrdemNaLista(ord);
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                //SE SAIU, COLOCA NOVAS ORDENS
                if(!rDia.getListaOrdensDia().isEmpty()
                && (rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isAlvoExecutado()
                    || rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isStopExecutado()
                    || rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isTrStopExecutado())){
                    maxLocal = -1*Float.MAX_VALUE;
                    minLocal = Float.MAX_VALUE;
                    offsetMax = 0;
                    offsetMin = 0;
                    deltaMin = false;
                    deltaMax = false;
                    ord = new OrdemOCO();
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    rDia.adicionaOrdemNaLista(ord);
                }


                //VERIFICA DELTA PARA ENTRADA NA ALTA
                if(!deltaMax
                && !listaPrecos.contains(maxLocal + ConfigBase.getPassoMin())
                && Candle.getListaCandleMinuto().get(i).getMinima() - maxLocal <= -1*Math.abs(ConfigOrdens.getDeltaIni())){
                    deltaMax = true; //ENTRADA ACIMA
                    listaPrecos.add(maxLocal + ConfigBase.getPassoMin());
                    offsetMax = maxLocal + ConfigBase.getPassoMin() - ord.getLinhaReferencia();
//                        System.out.println(Candle.getListaCandleMinuto().get(i).printData()
//                                + " | MaxLocal: " + maxLocal + " | offset: " + offsetMax);
                }
                //VERIFICA DELTA PARA ENTRADA NA BAIXA
                if(!deltaMin
                && !listaPrecos.contains(minLocal - ConfigBase.getPassoMin())
                && Candle.getListaCandleMinuto().get(i).getMaxima() - minLocal >= Math.abs(ConfigOrdens.getDeltaIni())){
                    deltaMin = true; //ENTRADA ABAIXO
                    listaPrecos.add(minLocal - ConfigBase.getPassoMin());
                    offsetMin = minLocal - ConfigBase.getPassoMin() - ord.getLinhaReferencia();
//                        System.out.println(Candle.getListaCandleMinuto().get(i).printData()
//                                + " | MinLocal: " + minLocal + " | offset: " + offsetMin);
                }

                //ENTRADA COM COTAÇÃO SUBINDO
                if(deltaMax 
                && !rDia.getListaOrdensDia().isEmpty()
                && !rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isIniciada()){
                    rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setOffset(offsetMax);
                    if(!rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isTemContMov())
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.COMPRA);
                    else 
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.VENDA);
                }

                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);

                //ENTRADA COM COTAÇÃO DESCENDO
                if(deltaMin
                && !rDia.getListaOrdensDia().isEmpty()
                && !rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isIniciada()){
                    rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setOffset(offsetMin);
                    if(!rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isTemContMov())
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.VENDA);
                    else 
                        rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).setLadoOrdem(LadoOrdem.COMPRA);
                }

                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));

            }//FIM DA VERIFICAÇÃO HORARIO INICIAL
            
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i)) 
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!diaGravado){
                    diaGravado = true;
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
                        
                        ord = new OrdemOCO();
                        ord.configuraLinhasEntradaESaidas(Candle.getListaCandleDiario().get(diaAtual).getAbertura());
                        rDia.adicionaOrdemNaLista(ord);
                        
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                        diaGravado = false;
                        maxLocal = -1*Float.MAX_VALUE;
                        minLocal = Float.MAX_VALUE;
                        offsetMax = 0;
                        offsetMin = 0;
                        deltaMin = false;
                        deltaMax = false;
                        ultimoCandle = true;
                        
                        listaPrecos.clear();
                    }   
                }
            } // FIM DO BLOCO FIM OPERACAO DIA
            
            //CORREÇÃO PARA O INICIO DO DIA SEGUINTE.
            if(!ultimoCandle){
                maxLocal = Double.max(maxLocal, Candle.getListaCandleMinuto().get(i).getMaxima());
                minLocal = Double.min(minLocal, Candle.getListaCandleMinuto().get(i).getMinima());
            }else{
                ultimoCandle = false;
            }
            
        } //FIM SIMULAÇÃO
    }
}