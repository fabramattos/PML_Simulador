/**
 *  DAY TRADE
 *      • Parâmetros: Pos, Pos Max, Delta, Gain, Loss
 *      • Lógica da montagem da pos:
 *          o Registra a Máxima e Mínima no intervalo de tempo selecionado (candles de 5, 10, 15 ou 30 min)
 *          o Entra na superação dos Max e Min em Delta pontos, com ordens OCO de Compra E Venda no mesmo valor.
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Pelo Gain/Loss das ordens OCO
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

/**
* Entradas com ordem OCO. Compra e venda ao atingir extremos.
* Extremos sao montados e permitem a entrada a cada 'x' minutos
* selecionados na interface.
* Saída por OCO ou Ger.Risco.
* @throws OutOfMemoryError 
*/
public class Sim_MontaPos_MaisMenosOCOExtremos extends Simulacao {
    
    public Sim_MontaPos_MaisMenosOCOExtremos(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;

        double
        valorNovaEntrada = 0;
        
        boolean
        gravouDia = false,
        podeInserirOrdens = false,
        superouExtremo = false;

        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                if(podeInserirOrdens){
                    
                    //VERIFICA SUPERAÇÃO DA MAX
                    if(Candle.getListaCandleMinuto().get(i).getMaxima() >= (rDia.getMaxLocal() + ConfigOrdens.getDelta())){
                        superouExtremo = true;
                        valorNovaEntrada = rDia.getMaxLocal() + ConfigOrdens.getDelta();
                    }
                    
                    //VERIFICA SUPERAÇÃO DA MIN
                    if(Candle.getListaCandleMinuto().get(i).getMinima() <= (rDia.getMinLocal() - ConfigOrdens.getDelta())){
                        superouExtremo = true;
                        valorNovaEntrada = rDia.getMinLocal() - ConfigOrdens.getDelta();
                    }
                    
                    if(superouExtremo){
                        OrdemSimples ord = new OrdemSimples();
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ord.setTemOffset(true);
                        ord.setOffset(valorNovaEntrada - ord.getLinhaReferencia());
                        rDia.adicionaOrdemNaLista(ord);
                        
                        ord = new OrdemSimples();
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ord.setTemOffset(true);
                        ord.setOffset(valorNovaEntrada - ord.getLinhaReferencia());
                        rDia.adicionaOrdemNaLista(ord);
                        
                        superouExtremo = false;
                        podeInserirOrdens = false;
                    }
                }

                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                
                if(rDia.atualizaExtremosLocais(Candle.getListaCandleMinuto().get(i), ConfigOrdens.getAguardaFormacaoCandle()))
                    podeInserirOrdens = true;
            }// diasOperando
            
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
                    //NÃO EXISTE DADOS SEGUINTE PARA SIMULAÇÃO
                    if (diaAtual == Candle.getListaCandleDiario().size())
                        break;

                    diaAtual++;
                    //PREPARANDO NOVO DIA
                    rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                    OrdemSimples ord = new OrdemSimples();
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                    rDia.adicionaOrdemNaLista(ord);

                    ord = new OrdemSimples();
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                    rDia.adicionaOrdemNaLista(ord);
                    
                    superouExtremo = false;
                    gravouDia = false;
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        } //FIM SIMULAÇÃO
    }
}
