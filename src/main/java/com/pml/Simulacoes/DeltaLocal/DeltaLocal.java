package com.pml.Simulacoes.DeltaLocal;

import com.pml.Configuracoes.ConfigBase;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import java.util.ArrayList;
import com.pml.Ordens.OrdemOCO;
import com.pml.Simulacoes.SimulacaoBase;

public class DeltaLocal extends SimulacaoBase {
    
    public DeltaLocal() {
        super(false, false);
    }
    
    private boolean 
        deltaMax = false,
        deltaMin = false;
        
    private double 
        offsetMax = 0,
        offsetMin = 0,
        maxLocal = Float.NEGATIVE_INFINITY,
        minLocal = Float.POSITIVE_INFINITY;
        
        private Ordem ord;
        private ArrayList listaPrecos = new ArrayList();
    
    @Override
    protected void logicaDaOperacao() {
        verificaSeSaiu_ColocaNovasOdens();
        atualizaExtremosLocais();
        verificaDeltas();
        verificaEntradaESaida();
    }

    @Override
    protected void primeiroCandleValidoDoDia() {
        ord = new OrdemOCO();
        ord.configuraLinhasEntradaESaidas(candleDiaAtual.getAbertura());
        rDia.adicionaOrdemNaLista(ord);

        maxLocal = Float.NEGATIVE_INFINITY;
        minLocal = Float.POSITIVE_INFINITY;
        
        offsetMax = 0;
        offsetMin = 0;
        deltaMin = false;
        deltaMax = false;

        listaPrecos.clear();
    }

    private void atualizaExtremosLocais(){
        maxLocal = Double.max(maxLocal, candleMinAtual.getMaxima());
        minLocal = Double.min(minLocal, candleMinAtual.getMinima());
    }
    
     private void verificaDeltas() {
        verificaDeltaParaEntrarNaAlta();
        verificaDeltaParaEntarNaBaixa();
    }

    private void verificaEntradaESaida() {
        verificaEntradaComCotacaoSubindo();
        verificaEntradaComCotacaoDescendo();
    }
    
    private void verificaDeltaParaEntrarNaAlta(){
        //VERIFICA DELTA PARA ENTRADA NA ALTA
        if(!deltaMax
        && !listaPrecos.contains(maxLocal + ConfigBase.getPassoMin())
        && candleMinAtual.getMinima() - maxLocal <= -1*Math.abs(ConfigOrdens.getDeltaIni())){
            deltaMax = true; //ENTRADA ACIMA
            listaPrecos.add(maxLocal + ConfigBase.getPassoMin());
            offsetMax = maxLocal + ConfigBase.getPassoMin() - ord.getLinhaReferencia();
        }
    }
    
    private void verificaDeltaParaEntarNaBaixa(){
        //VERIFICA DELTA PARA ENTRADA NA BAIXA
        if(!deltaMin
        && !listaPrecos.contains(minLocal - ConfigBase.getPassoMin())
        && candleMinAtual.getMaxima() - minLocal >= Math.abs(ConfigOrdens.getDeltaIni())){
            deltaMin = true; //ENTRADA ABAIXO
            listaPrecos.add(minLocal - ConfigBase.getPassoMin());
            offsetMin = minLocal - ConfigBase.getPassoMin() - ord.getLinhaReferencia();
        }
    }
    
    private void verificaEntradaComCotacaoSubindo(){
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
        controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
    }
    
    private void verificaEntradaComCotacaoDescendo(){
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
        controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
    }
    
    private void verificaSeSaiu_ColocaNovasOdens(){
        //SE SAIU, COLOCA NOVAS ORDENS
        if(!rDia.getListaOrdensDia().isEmpty()
        && (rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isAlvoExecutado()
            || rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isStopExecutado()
            || rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isTrStopExecutado())){
            maxLocal = Float.NEGATIVE_INFINITY;
            minLocal = Float.POSITIVE_INFINITY;
            offsetMax = 0;
            offsetMin = 0;
            deltaMin = false;
            deltaMax = false;
            ord = new OrdemOCO();
            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
            rDia.adicionaOrdemNaLista(ord);
        }
    }
    
}