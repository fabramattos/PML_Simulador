package com.pml.Simulacoes.AberturaLista;

import com.pml.Simulacoes.SimulacaoBase;

public class AberturaLista extends SimulacaoBase {
    
    public AberturaLista() {
        super(false, false);
    }

    @Override
    protected void logicaDaOperacao() {
        controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
    }

    @Override
    protected void primeiroCandleValidoDoDia() {
        rDia.configuraLinhasEntradaESaida_ListaOrdensDoDia(candleMinAtual.getAbertura());
    }
}
