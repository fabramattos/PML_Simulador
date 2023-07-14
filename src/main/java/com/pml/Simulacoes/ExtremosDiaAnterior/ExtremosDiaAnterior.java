package com.pml.Simulacoes.ExtremosDiaAnterior;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import com.pml.Ordens.OrdemOCO;
import com.pml.infra.Candle;
import com.pml.Simulacoes.SimulacaoBase;

public class ExtremosDiaAnterior extends SimulacaoBase {
    
    public ExtremosDiaAnterior() {
        super(false, false);
    }
    
    private boolean superouExtremo = false;

    private OrdemOCO ord;
    
    private void verificaEntradaNaMin() {
        if(!superouExtremo && rDia.getMinLocal() >= Candle.getListaCandleDiario().get(diaAtual-1).getMinima() - ConfigOrdens.getDelta()){
            for(Ordem item : rDia.getListaOrdensDia()){
                item.setOffset(Candle.getListaCandleDiario().get(diaAtual-1).getMinima() - ConfigOrdens.getDelta() - rDia.getAbertura());
                if(!item.isTemContMov())
                    item.setLadoOrdem(LadoOrdem.VENDA);
                else
                    item.setLadoOrdem(LadoOrdem.COMPRA);
            };
            superouExtremo = true;
        }
    }

    private void verificaEntradaNaMax() {
        if(!superouExtremo && rDia.getMaxLocal() >= Candle.getListaCandleDiario().get(diaAtual-1).getMaxima() + ConfigOrdens.getDelta() ){
            for(Ordem item : rDia.getListaOrdensDia()){
                item.setOffset(Candle.getListaCandleDiario().get(diaAtual-1).getMaxima() + ConfigOrdens.getDelta() - rDia.getAbertura());
                if(!item.isTemContMov())
                    item.setLadoOrdem(LadoOrdem.COMPRA);
                else
                    item.setLadoOrdem(LadoOrdem.VENDA);
            }
            superouExtremo = true;
        }
    }

    private void verificaAberturaAbaixoDoExtremo() {
        if(!superouExtremo && rDia.getAbertura() <= Candle.getListaCandleDiario().get(diaAtual-1).getMinima() - ConfigOrdens.getDelta()){
            for(Ordem item : rDia.getListaOrdensDia()){
                item.setOffset(0);
                if(!item.isTemContMov())
                    item.setLadoOrdem(LadoOrdem.VENDA);
                else
                    item.setLadoOrdem(LadoOrdem.COMPRA);
            }
            superouExtremo = true;
        }
    }

    private void verificaAberturaAcimaDoExtremo() {
        if(!superouExtremo && rDia.getAbertura() >= Candle.getListaCandleDiario().get(diaAtual-1).getMaxima() + ConfigOrdens.getDelta()){
            for(Ordem item : rDia.getListaOrdensDia()){
                item.setOffset(0);
                if(!item.isTemContMov())
                    item.setLadoOrdem(LadoOrdem.COMPRA);
                else
                    item.setLadoOrdem(LadoOrdem.VENDA);
            }
            superouExtremo = true;
        }
    }

    @Override
    protected void logicaDaOperacao() {
        rDia.configuraLinhasEntradaESaida_ListaOrdensDoDia(candleMinAtual.getAbertura());
        verificaAberturaAcimaDoExtremo();       
        verificaAberturaAbaixoDoExtremo();       
        verificaEntradaNaMax();       
        verificaEntradaNaMin();       
        controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
    }

    @Override
    protected void primeiroCandleValidoDoDia() {
        ord = new OrdemOCO();
        rDia.adicionaOrdemNaLista(ord);
        superouExtremo = false;
    }
}