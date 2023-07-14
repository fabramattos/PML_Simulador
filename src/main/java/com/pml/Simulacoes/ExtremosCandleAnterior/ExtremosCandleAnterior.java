package com.pml.Simulacoes.ExtremosCandleAnterior;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import com.pml.infra.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Simulacoes.SimulacaoBase;

public class ExtremosCandleAnterior extends SimulacaoBase {
    
    public ExtremosCandleAnterior() {
        super(false, false);
    }

    OrdemSimples ord;
    
    @Override
    protected void logicaDaOperacao() {
        verificaSuperacaoDaMax();
        verificaSuperacaoDaMin();
        if(candleMinAtual.criaCandleIntermediario(ConfigOrdens.getAguardaFormacaoCandle())){
            rDia.getListaOrdensDia().clear();
            ord = new OrdemSimples();
            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
            rDia.adicionaOrdemNaLista(ord);
        }
    }

    @Override
    protected void primeiroCandleValidoDoDia() {
    }
    
    
   private void verificaSuperacaoDaMax(){
        if(!rDia.executouAlgumaOrdem() && !rDia.getListaOrdensDia().isEmpty()){
            ultimaOrdemNaLista()
                    .setOffset(Candle.getMaxCandleYMinAnterior() + ConfigOrdens.getDelta() - rDia.getAbertura());
            if(!ConfigOrdens.isTemContMov())
                ultimaOrdemNaLista()
                        .setLadoOrdem(LadoOrdem.COMPRA);
            else
                ultimaOrdemNaLista()
                        .setLadoOrdem(LadoOrdem.VENDA);

            controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
            }
        }
   
    private void verificaSuperacaoDaMin(){
        //VERIFICAO SUPERAÇÃO DA MIN
        if(!rDia.executouAlgumaOrdem() && !rDia.getListaOrdensDia().isEmpty()){
           ultimaOrdemNaLista()
                        .setOffset(Candle.getMinCandleYMinAnterior() - ConfigOrdens.getDelta() - rDia.getAbertura());
            if(!ConfigOrdens.isTemContMov())
                ultimaOrdemNaLista()
                        .setLadoOrdem(LadoOrdem.VENDA);
            else
                ultimaOrdemNaLista()
                        .setLadoOrdem(LadoOrdem.COMPRA);

            controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
        }
    }
    
    private Ordem ultimaOrdemNaLista(){
        return rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1);
    }
    
}