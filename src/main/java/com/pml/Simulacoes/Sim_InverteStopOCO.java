/**
 *  DAY TRADE
 *      • Parâmetros: Qtde 1, Qtde 2, Offset, Gain, Loss
 *      • Lógica da montagem da pos:
 *          o Entra com ordem OCO, com Qtde 1, com referência na abertura
 *          o Caso acione o Stop, inverte a posição com ordem OCO, Qtde 2, sem alvo
 *      ▪ Pode inverter diversas vezes de acordo com o Loss informado para as OCO
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o pelo GAIN da primeira OCO
 *          o pelo Ger. Risco a partir da inversão.
 *          o Não compensa perdas de dias anteriores 
 **/

package com.pml.Simulacoes;

import com.pml.Ordens.LadoOrdem;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

/**
* Monitora os X minutos iniciais de acordo com a interface grafica inicial.
* ao r extremo, entra a favor do mov.
* se sair stop, inverte no stop e busca ptsCont
*/
public class Sim_InverteStopOCO extends Simulacao {
    
    public Sim_InverteStopOCO(boolean aplicado) {
        super(aplicado);
    }

    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = super.diaAtual;
        
        boolean
        gravouDia = false;
        
        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        //CONFIGURA ESTRATEGIAS
        OrdemSimples ord = new OrdemSimples();
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                if(!ord.isAtualizouReferencia())
                    ord.configuraLinhasEntradaESaidas(Candle.getListaCandleMinuto().get(i).getAbertura());
                
                controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                
                //STOP ACIONADO, INVERTE POSIÇÃO
                if(ord.isStopExecutado() || ord.isTrStopExecutado()){
                    LadoOrdem mov = ord.getLadoOrdem();
                    double ref = ord.getLinhaReferencia();
                    double linhaStop = ord.getLinhaStop();

                    if(ord.isTrStopExecutado())
                        linhaStop = ord.getLinhaTrStop();
                    
                    ord = new OrdemSimples();
                    ord.setTemOffset(true);
                    ord.configuraLinhasEntradaESaidas(ref);
                    ord.setOffset(linhaStop - ref);
                    if(mov == LadoOrdem.COMPRA){
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                        ord.setNome("inversão venda");
                    }else{
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                        ord.setNome("inversão compra");
                    }
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                }
            }
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);

            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                if(!gravouDia){
                    gravouDia = true;

                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }

                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1){
                    break;
                }
                
                //FECHAMENTO DO DIA
               if(controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                    diaAtual++;
                    rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                    
                    ord = new OrdemSimples();

                    gravouDia = false;
                    
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        } //FIM SIMULAÇÃO
    }
}