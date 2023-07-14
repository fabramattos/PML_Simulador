package com.pml.Simulacoes.PreencheGrid_Inverte;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemSimples;
import com.pml.Simulacoes.SimulacaoBase;

public class PreencheGrid_Inverte extends SimulacaoBase {
    
    public PreencheGrid_Inverte() {
        super(false, false);
    }

    private OrdemSimples ord;
    
    @Override
    protected void logicaDaOperacao() {
        controladorOrdens.testaListaOrdens_InverteNaDirecaoDoMovimento(candleMinAtual, rDia);
    }

    @Override
    protected void primeiroCandleValidoDoDia() {
        preencheGrid();
        rDia.configuraLinhasEntradaESaida_ListaOrdensDoDia(candleMinAtual.getAbertura());
    }
    
    private void preencheGrid() {
        ord = new OrdemSimples();
        ord.setLinhaCompra(candleMinAtual.getAbertura());
        rDia.adicionaOrdemNaLista(ord);
        
        for(int i = 0; i<20; i++){
            ord = new OrdemSimples();
            ord.setLadoOrdem(LadoOrdem.COMPRA);
            ord.setOffset(ConfigOrdens.getDelta() + i * ConfigOrdens.getOffset());
            ord.setLinhaCompra(candleMinAtual.getAbertura() + ord.getOffset());
            rDia.adicionaOrdemNaLista(ord);
            
            ord = new OrdemSimples();
            ord.setLadoOrdem(LadoOrdem.VENDA);
            ord.setOffset(-1*ConfigOrdens.getDelta() - i * ConfigOrdens.getOffset());
            ord.setLinhaVenda(candleMinAtual.getAbertura() + ord.getOffset());
            rDia.adicionaOrdemNaLista(ord);
        }
    }
    
}
                
    
