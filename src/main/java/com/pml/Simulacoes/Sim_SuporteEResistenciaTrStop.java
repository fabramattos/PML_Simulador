/**
 *  DAY TRADE / TR.STOP
 *      • Parâmetros: Qtde, Delta, Trailing Stop, Tendencia
 *      • Lógica da montagem da pos:
 *          o Monitora os preços, registrando máx. e min. do dia até o momento
 *          o Ao abrir uma distância Delta entre os extremos, entra na cotação atual com Trailing Stop.
 *          o Inverte em todo Stop entrando a favor da tendencia com novo Trailing Stop.
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Saída pelo Ger. Risco
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.simulacao.Candle;
import com.pml.Resumos.ResumoDia;

public class Sim_SuporteEResistenciaTrStop extends Simulacao {
    
    private int diaAtual, candlesLimite, candlesPassados;
    private boolean diaOperando, podeEntrarAcima,podeEntrarAbaixo;
    private ResumoDia rDia;
    private OrdemOCO ord;
    private double linhaSuporte, linhaResistencia, variacao;
    
    public Sim_SuporteEResistenciaTrStop(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        diaAtual = diaInicial;
        
        candlesLimite = (int)ConfigOrdens.getDelta();
        candlesPassados = 0;
        variacao = Math.abs(ConfigOrdens.getOffset());
        podeEntrarAcima = true;
        podeEntrarAbaixo = true;
        
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        ord = new OrdemOCO();
        
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            logicaDaOperacao(i);
                       
            if(controleTempo.verificaFimDasOperacoes(i)){
                gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        iniciandoNovoDia();
                    }   
                }
            }
        } //FIM SIMULAÇÃO
    }
    
    private void logicaDaOperacao(int i) {
        if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
            linhaSuporte = Math.min(linhaSuporte, Candle.getListaCandleMinuto().get(i).getMinima());
            linhaResistencia = Math.max(linhaResistencia, Candle.getListaCandleMinuto().get(i).getMaxima());
            
            if(candlesPassados == 0 || candlesPassados > candlesLimite){
                candlesPassados = 0;
                linhaSuporte = Candle.getListaCandleMinuto().get(i).getMinima();
                linhaResistencia = Candle.getListaCandleMinuto().get(i).getMaxima();
                podeEntrarAcima = true;
                podeEntrarAbaixo = true;
            }
            
            if(podeEntrarAcima && candlesPassados > 0 && Candle.getListaCandleMinuto().get(i).getMaxima() - linhaSuporte >= variacao){
                ord = new OrdemOCO();
                ord.setOffset(linhaSuporte + variacao - rDia.getAbertura());
                if(!ord.isTemContMov())
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                else
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                
                ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                rDia.adicionaOrdemNaLista(ord);
                podeEntrarAcima = false;
            }       
            
            if(podeEntrarAbaixo && candlesPassados > 0 && Candle.getListaCandleMinuto().get(i).getMinima() - linhaResistencia <= -variacao){
                ord = new OrdemOCO();
                ord.setOffset(linhaResistencia - variacao - rDia.getAbertura());
                if(!ord.isTemContMov())
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                else
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                
                ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                rDia.adicionaOrdemNaLista(ord);
                podeEntrarAbaixo = false;
            }
            controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
        }
        candlesPassados++;
        rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
        Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
    }
    
    private void iniciandoNovoDia() {
        diaAtual++;
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        candlesPassados = 0;
    }
    
}