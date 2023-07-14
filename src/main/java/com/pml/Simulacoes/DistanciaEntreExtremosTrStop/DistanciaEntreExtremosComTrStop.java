package com.pml.Simulacoes.DistanciaEntreExtremosTrStop;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.Simulacoes.SimulacaoBase;

public class DistanciaEntreExtremosComTrStop extends SimulacaoBase {
    
    private int candlesPassados = 0;
    private int candlesLimite = (int)ConfigOrdens.getDelta();
    private boolean podeEntrarAcima,podeEntrarAbaixo;
    private OrdemOCO ord;
    private double minLocal, maxLocal;
    private double distanciamentoDesejado = Math.abs(ConfigOrdens.getOffset());
    
    
    public DistanciaEntreExtremosComTrStop() {
        super(false, false);
    }
    
    @Override
    protected void logicaDaOperacao() {
        verificaResetTempoLimite();
        atualizaExtremosLocais();
        verificaDistanciamentoDaMaxima();
        verificaDistanciamentoDaMinima();
        controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
        candlesPassados++;
    }

    @Override
    protected void primeiroCandleValidoDoDia() {
        candlesPassados = 0;
        minLocal = Double.POSITIVE_INFINITY;
        maxLocal = Double.NEGATIVE_INFINITY;
        podeEntrarAcima = true;
        podeEntrarAbaixo = true;
    }
    
    
    private void verificaDistanciamentoDaMaxima() {
        if(podeEntrarAbaixo && candlesPassados > 0 && candleMinAtual.getMinima() - maxLocal <= -distanciamentoDesejado){
            ord = new OrdemOCO();
            ord.setOffset(maxLocal - distanciamentoDesejado - rDia.getAbertura());
            if(!ord.isTemContMov())
                ord.setLadoOrdem(LadoOrdem.VENDA);
            else
                ord.setLadoOrdem(LadoOrdem.COMPRA);
            
            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
            rDia.adicionaOrdemNaLista(ord);
            podeEntrarAbaixo = false;
        }
    }

    private void verificaDistanciamentoDaMinima() {
        if(podeEntrarAcima && candlesPassados > 0 && candleMinAtual.getMaxima() - minLocal >= distanciamentoDesejado){
            ord = new OrdemOCO();
            ord.setOffset(minLocal + distanciamentoDesejado - rDia.getAbertura());
            if(!ord.isTemContMov())
                ord.setLadoOrdem(LadoOrdem.COMPRA);
            else
                ord.setLadoOrdem(LadoOrdem.VENDA);
            
            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
            rDia.adicionaOrdemNaLista(ord);
            podeEntrarAcima = false;
        }
    }

    private void verificaResetTempoLimite() {
        if(candlesPassados == 0 || candlesPassados > candlesLimite){
            candlesPassados = 0;
            minLocal = candleMinAtual.getMinima();
            maxLocal = candleMinAtual.getMaxima();
            podeEntrarAcima = true;
            podeEntrarAbaixo = true;
        }
    }
    
    private void atualizaExtremosLocais() {
        minLocal = Math.min(minLocal, candleMinAtual.getMinima());
        maxLocal = Math.max(maxLocal, candleMinAtual.getMaxima());        
    }
    
}