/**
 * Classe que representa Ordens OCO e ordens Simples (sem gain / loss)
 */
package com.pml.Ordens;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Resumos.Relatorios;
import com.pml.simulacao.Arredondamento;
import com.pml.simulacao.Candle;
import com.pml.Resumos.ResumoDia;

public class OrdemOCO extends OrdemSimples{
    
    public OrdemOCO(){
        super();
        
        super.temAlvo = ConfigOrdens.isTemGain();
        super.temStop = ConfigOrdens.isTemLoss();
        super.temTrStop = ConfigOrdens.isTemTrStop();
        super.gain = ConfigOrdens.getGain();
        super.loss = ConfigOrdens.getLoss();
        super.trStopPtsAcionamento = ConfigOrdens.getTrStopAcionamento();
        super.trStopGainMin = ConfigOrdens.getTrStopPtsMin();
        super.trStopFrequenciaAtualizacao = ConfigOrdens.getTrStopFrequeAtualiza();
    }
    
    /**
     * Verifica o valor que foi feita a entrada da ordem, e onde o candle abriu.
     * Permite que simule a saida no mesmo candle ou escolhe esperar o proximo candle
     * para evitar ambiguidade.
     * Caso simule a saída no mesmo candle, ainda pode ocorrer Simultaneidade, tratada em outro método
     * @return TRUE se pode efetuar saída no mesmo candle
     */
    @Override
    boolean verificaSePodeSair(Candle candle, ResumoDia rDia) {
        if(rDia.isGerRisco())
            return false;
        
        if(super.alvoExecutado || super.stopExecutado || super.trStopExecutado)
            return false;
        
        if(!super.iniciada)
            return false;
        
        if(rDia.getDataUltimaOrdemExec() == null)
            return false;
            
        if(this.isGerenciamentoDeRisco() || this.iniciada)
            this.podeSair = this.podeSair || candle.getData().isAfter(rDia.getDataUltimaOrdemExec());

        if(super.podeSair)
            return true;
        
        switch (super.ladoOrdem){
            default:
                return false;

            //verificacoes da movimentacao dos precos (valor de entrada -> valor de saida)
            //precisa verificar todas as possibilidades contra movimento (preco ter atingido
            //o valor de saida antes de ter feito entrada. Fechamento garante que tenha passado novamente
            //no mesmo candle
            case COMPRA:
                if(super.temAlvo)
                    if(candle.getAbertura() > super.linhaCompra)
                        if(candle.getFechamento() <= super.linhaVenda)
                            return false;
                            
                if(super.temStop)
                    if(candle.getAbertura() < super.linhaCompra)
                        if(candle.getFechamento() >= super.linhaStop)
                            return false;
                
                if(super.temTrStop && super.trIniciado)
                    if(candle.getAbertura() < super.linhaCompra)
                        if(candle.getFechamento() >= super.linhaTrStop)
                            return false;
                break;
                
            case VENDA:
                if(super.temAlvo)
                    if(candle.getAbertura() < super.linhaVenda)
                        if(candle.getFechamento() >= super.linhaCompra)
                            return false;
                if(super.temStop)
                     if(candle.getAbertura() > super.linhaVenda)
                        if(candle.getFechamento() <= super.linhaStop)
                            return false;
                if(super.temTrStop && super.trIniciado)
                    if(candle.getAbertura() > super.linhaVenda)
                        if(candle.getFechamento() <= super.linhaTrStop)
                            return false;
                break;
        }
        super.podeSair = true;
        return true;
    }


    @Override
    void setNome() {
        switch (super.ladoOrdem){
            case COMPRA:
                super.nome = "OCO Compra";
                break;
                
            case VENDA:
                super.nome = "OCO Venda";
                break;
                
            default:
                super.nome = null;
                break;
        }
    }

    @Override
    boolean testaEntrada(Candle candle, ResumoDia rDia) {
        super.testaEntrada(candle, rDia);
        super.encerrada = false;
        return super.iniciada;
    }
    
    @Override
    public void configuraLinhasEntradaESaidas(double valorReferencia) {
        super.configuraLinhasEntradaESaidas(valorReferencia);
        switch (super.ladoOrdem){
            case COMPRA:
                if (super.temAlvo)
                    super.linhaVenda = super.linhaCompra + super.gain;
                if (super.temStop)
                    super.linhaStop = super.linhaCompra - super.loss;
                break;
                      
            case VENDA:
                if (super.temAlvo)
                    super.linhaCompra = super.linhaVenda - super.gain;
                if (super.temStop)
                    super.linhaStop = super.linhaVenda + super.loss;
                break;
        }
    }
    
    /**
     * @return TRUE se executou o Stop
     */
    @Override
    boolean testaStop(Candle candle, ResumoDia rDia) {
        if(!super.temStop)
            return false;
        
        //VERIFICAÇÃO STOP
        if (!super.vendido && super.comprado && (candle.getMinima() <= super.linhaStop)) {
            super.stopExecutado = true;
            super.nome = "OCO Venda Stop Loss";
            rDia.executaVendaStop(super.qtde, super.linhaStop);
            return true;
        }
        
        if (!super.comprado && super.vendido && (candle.getMaxima() >= super.linhaStop)) {
            super.stopExecutado = true;
            super.nome = "OCO Compra Stop Loss";
            rDia.executaCompraStop(super.qtde, super.linhaStop);
            return true;
        }
        return false;
        
    }
    
    /**
     * @return TRUE se executou o Trailing Stop
     */
    @Override
    boolean testaTrailingStop(Candle candle, ResumoDia rDia) {
        if (!super.temTrStop)
            return false;
        
        if(trStopTentaIniciar(candle))
            trStopAtualiza(candle);
       
        if(!super.trIniciado)
            return false;
        
        // VERIFICA TRAILING STOP
        if (rDia.getPos() > 0 && !super.vendido && super.comprado && (candle.getMinima() <= super.linhaTrStop)) {
            super.nome = "Tr. Stop Venda";
            super.trStopExecutado = true;
            rDia.executaVendaTrStop(super.qtde, super.linhaTrStop);
            return true;
        }
        if (rDia.getPos() < 0 && !super.comprado && super.vendido && (candle.getMaxima() >= super.linhaTrStop)) {
            super.nome = "Tr. Stop Compra";
            super.trStopExecutado = true;
            rDia.executaCompraTrStop(super.qtde, super.linhaTrStop);
            return true;
        }
        return false;
    }
    
    /**
     * Verifica se as condições para iniciar o Trailing Stop foram atingidas
     */
    @Override
    boolean trStopTentaIniciar(Candle candle){
        if(!super.temTrStop)
            return false;
        
        if(super.trIniciado)
            return true;
        
        if(super.comprado){
            if (candle.getMaxima() - super.linhaCompra < super.trStopPtsAcionamento)
                return false;
            super.linhaTrStop = super.linhaCompra + super.trStopGainMin;
        }

        if(super.vendido){
            if(candle.getMinima() - super.linhaVenda > -super.trStopPtsAcionamento)
                return false;
            super.linhaTrStop = super.linhaVenda - super.trStopGainMin;
        }
        
        super.trIniciado = true;
        return true;
    }
    
    /**
     * Atualiza a linha de saída pelo Trailing Stop
     */
    @Override
     void trStopAtualiza(Candle candle){
        if(!super.trIniciado)
            return;
        
        if(super.comprado){
            // CALCULO DE QUANTOS PONTOS VARIOU A PARTIR DO ACIONAMENTO
            double novoTrStop = candle.getMaxima() - (super.linhaCompra + super.trStopPtsAcionamento);
            novoTrStop =  (double) (( (int)(novoTrStop / super.trStopFrequenciaAtualizacao)) * super.trStopFrequenciaAtualizacao);
            novoTrStop = super.linhaCompra + super.trStopGainMin + novoTrStop;
            novoTrStop = new Arredondamento().arredondaCimaMultiplo(novoTrStop, this);
            //A LINHA ABAIXO FAZ O UPDATE SE NECESSARIO
            super.linhaTrStop = Double.max(super.linhaTrStop, novoTrStop);
        }

        if(super.vendido){
            // CALCULO DE QUANTOS PONTOS VARIOU A PARTIR DO ACIONAMENTO
            double novoTrStop = candle.getMinima() - (super.linhaVenda - super.trStopPtsAcionamento);
            novoTrStop =  (double) (( (int)(novoTrStop / super.trStopFrequenciaAtualizacao)) * super.trStopFrequenciaAtualizacao);
            novoTrStop = super.linhaVenda - super.trStopGainMin + novoTrStop;
            novoTrStop = new Arredondamento().arredondaBaixoMultiplo(novoTrStop, this);
            //A LINHA ABAIXO FAZ O UPDATE SE NECESSARIO
            super.linhaTrStop = Double.max(super.linhaTrStop, novoTrStop);
        }
    }
    

    @Override
    boolean testaGain(Candle candle, ResumoDia rDia){
        if (!super.temAlvo)
            return false;
        
        switch(super.ladoOrdem){
            case COMPRA:
                if(super.linhaVenda >= candle.getMaxima())
                    return false;
                
                super.vendido = true;
                super.nome = "OCO Venda Gain";
                rDia.executaVenda(super.qtde, super.linhaVenda);
                break;
                
            case VENDA:
                if(super.linhaCompra <= candle.getMinima())
                    return false;
                
                super.comprado = true;
                super.nome = "OCO Compra Gain";
                rDia.executaCompra(super.qtde, super.linhaCompra);
                break;
                
            default:
                return false;
        }
        super.alvoExecutado = true;
        return true;
    }
    
    @Override
    public void setDistLinhaExecucao(Candle candle, ResumoDia rDia) {
        if(!super.iniciada){
            super.setDistLinhaExecucao(candle, rDia);
            return;
        }
        
        if(!super.temAlvo && !super.temTrStop && !super.temStop)
            return;
        
        double distanciaAlvo = Double.POSITIVE_INFINITY;
        double distanciaStop = Double.POSITIVE_INFINITY;
        
        if(super.temAlvo){
            if(super.ladoOrdem == LadoOrdem.COMPRA)
                distanciaAlvo = Math.abs(super.linhaVenda - rDia.getUltimoValorExecutado());
            if(super.ladoOrdem == LadoOrdem.VENDA)
                distanciaAlvo = Math.abs(super.linhaCompra - rDia.getUltimoValorExecutado());
        }
        if(super.temTrStop)
            distanciaStop = Math.abs(super.linhaTrStop - rDia.getUltimoValorExecutado());
                
        if(super.temStop)
            distanciaStop = Math.abs(super.linhaStop - rDia.getUltimoValorExecutado());
        
        super.distSaidaDoUltimoValorExec = Double.min(distanciaAlvo, distanciaStop);
        
    }
}
