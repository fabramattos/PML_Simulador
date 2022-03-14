/**
 *  DAY TRADE 
 *      • Parâmetros: Pos, Pos Max, Delta, Offset, Gain, Loss
 *      • Lógica da montagem da pos:
 *          o Realiza entradas com ordens OCO (compra e venda) em todos os preços a partir da abertura do dia +/- offset,
 *            seguindo um passo Delta para as ordens seguintes. 
 *          o Ex. Se offset = 0, entrará em todos preços. Se offset = 10, só realizará entradas a partir da abertura do dia +/- 10 pontos
 *          o O Delta determina o passo das ordens de entrada a partir da primeira entrada (se Delta = 0, o código ficará em loop, ainda preciso filtrar este input)
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Pelo Gain/Loss das ordens OCO ou se necessário, pelo Gerenciamento de Risco
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import java.util.ArrayList;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemOCO;
import com.pml.Resumos.ResumoDia;

/**
* Entradas com ordem OCO. Compra e venda em todos os preços afastados do offset
* Saída por OCO ou Ger.Risco.
* @throws OutOfMemoryError 
*/
public class Sim_MontaPos_MaisMenosOCOPrecos extends Simulacao {
    
    public Sim_MontaPos_MaisMenosOCOPrecos(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;

        double
        offset = 0;
        
        boolean
        gravouDia = false;
        
        ArrayList<Double> listaOffset = new ArrayList<>();
        
        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){

                //PREÇOS SUBINDO
                if(Candle.getListaCandleMinuto().get(i).getMaxima() > rDia.getMaxLocal()){
                    if(listaOffset.isEmpty())
                        offset = Math.abs(ConfigOrdens.getOffset());
                    else
                        offset = Double.max(ConfigOrdens.getOffset(), listaOffset.get(listaOffset.size()-1));
                    
                    for(double j = offset; j<= Candle.getListaCandleMinuto().get(i).getMaxima() - rDia.getAbertura(); j+= ConfigOrdens.getDelta()){
                        if(listaOffset.contains(j))
                            continue;
                        
                        OrdemOCO ord = new OrdemOCO();
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ord.setTemOffset(true);
                        ord.setOffset(j);
                        rDia.adicionaOrdemNaLista(ord);

                        ord = new OrdemOCO();
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ord.setTemOffset(true);
                        ord.setOffset(j);
                        rDia.adicionaOrdemNaLista(ord);
                        
                        listaOffset.add(j);
                    }
                    listaOffset.sort(null);
                }

                //PREÇOS DESCENDO
                if(Candle.getListaCandleMinuto().get(i).getMinima() < rDia.getMinLocal()){
                    if(listaOffset.isEmpty())
                        offset = (-1*Math.abs(ConfigOrdens.getOffset()));
                    else
                        offset = Double.min(-1*Math.abs(ConfigOrdens.getOffset()), listaOffset.get(0));
                    
                    if(offset == -0)
                        offset = 0;
                    
                    for(double j = offset; j >= Candle.getListaCandleMinuto().get(i).getMinima() - rDia.getAbertura(); j-= ConfigOrdens.getDelta()){
                        if(listaOffset.contains(j))
                            continue;
                        
                        OrdemOCO ord = new OrdemOCO();
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ord.setTemOffset(true);
                        ord.setOffset(j);
                        rDia.adicionaOrdemNaLista(ord);

                        ord = new OrdemOCO();
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ord.setTemOffset(true);
                        ord.setOffset(j);
                        rDia.adicionaOrdemNaLista(ord);
                        
                        listaOffset.add(j);
                    }
                    listaOffset.sort(null);
                }

                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                
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

                    //PREPARANDO NOVO DIA
                    diaAtual++;
                    rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                    
                    listaOffset.clear();
                    gravouDia = false;
                    
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        } //FIM SIMULAÇÃO
    }
}
