/**
 * Classe que representa Ordens OCO e ordens Simples (sem gain / loss)
 */
package com.pml.Ordens;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.simulacao.Candle;
import com.pml.Resumos.ResumoDia;

public class OrdemSimples extends Ordem{
    
    public OrdemSimples(){
        super.ativo = ConfigOrdens.getAtivo();
        super.setLadoOrdem(ConfigOrdens.getLadoOrdem());
        super.temDelta = ConfigOrdens.isTemDelta();
        super.temOffset = ConfigOrdens.isTemOffset();
        super.temLimOp = ConfigOrdens.isTemLimOp();
        super.temContMov = ConfigOrdens.isTemContMov();
        configuraOffset();
        super.delta = ConfigOrdens.getDelta();
        super.limOp = ConfigOrdens.getLimOp();
        super.qtde = ConfigOrdens.getQtde();
    }
    
    /**
    * Correção do offset baseado no mov e contra/fav
    **/
    private void configuraOffset(){
        if(!super.temOffset){
            super.offset = 0;
            return;
        }
            
        switch (super.ladoOrdem){
            case INDEF:
                // Para as simulações que não tem mov pré determinado, mas dependem
                // de algo imposto pela forma de operar, definido na simulação em si.
                super.offset = Math.abs(ConfigOrdens.getOffset());
                break;
                
            case COMPRA:
                if(super.temContMov)
                    super.offset = (-1*(Math.abs(ConfigOrdens.getOffset())));
                else
                    super.offset = Math.abs(ConfigOrdens.getOffset());
                break;
                
            case VENDA:
                if(super.temContMov)
                    super.offset = Math.abs(ConfigOrdens.getOffset());
                else
                    super.offset = (-1*(Math.abs(ConfigOrdens.getOffset())));
                break;
        }
    }

    @Override
    boolean testaEntrada(Candle candle, ResumoDia rDia) {
        if (verificaLimOp(rDia))
            return false;
        
        switch (super.ladoOrdem){
            case COMPRA:
                if (super.linhaCompra > candle.getMaxima() || super.linhaCompra < candle.getMinima())
                    return false;
                rDia.executaCompra(super.qtde, super.linhaCompra);
                super.comprado = true;
                break;
        
            case VENDA:
                if (super.linhaVenda > candle.getMaxima() || super.linhaVenda < candle.getMinima())
                    return false;
                rDia.executaVenda(super.qtde, super.linhaVenda);
                super.vendido = true;
                break;
                
            default:
                return false;
        }
        super.iniciada = true;
        super.encerrada = true; // SE FOR ORDEM OCO, A INSTANCIA MUDARÁ PARA FALSE;
        return true;
    }

    /**
     * 
     * @param rDia
     * @return TRUE se fez o limOp. FALSE permite executar ordem
     */
    private boolean verificaLimOp(ResumoDia rDia){
        if (!super.temOffset || !super.temLimOp)
            return false;

        //VERIFICAÇÃO
        if ((super.offset > 0 && rDia.getMinLocal() - rDia.getAbertura() <= -1 * Math.abs(super.limOp)) 
        || (super.offset < 0 && rDia.getMaxLocal() - rDia.getAbertura() >= Math.abs(super.limOp))) {
            super.ladoOrdem = LadoOrdem.INDEF;
            return true;
        }
        return false;    
    }
    
    /**
     * Atualiza a quantidade para a nova estrategia baseado no saldo do dia e um gain max.
     * Ex.Ordens sairam no stop, compensa a perda com maior quantidade de contratos, respeitado
     * um gain maximo previsto pós entrada.
     * @param rDia resumo do dia até o candle atual.
     * @param saldoDesej quantos pontos deseja para o dia
     * @param gainMax o quanto permite de gain pós entrada
     */
    public void atualizaQtde_SaldoDesej(ResumoDia rDia, double saldoDesej, double gainMax){
        double saldoFuturo = rDia.getSaldo() + (gainMax*super.qtde);
        while(saldoFuturo < saldoDesej){
            super.qtde++;
            saldoFuturo = rDia.getSaldo() + (gainMax*super.qtde);
        }
    }
    
    /**
     * Atualiza a quantidade para a nova estrategia baseado no saldo do dia.Ex.
     * Ordens sairam no stop, compensa a perda com maior quantidade de contratos.
     * @param rDia resumo do dia até o candle atual.
     * @param ptsContratoDesej
     */
    public void atualizaQtde_PtsCont(ResumoDia rDia, double ptsContratoDesej){
        //CORRIGINDO LOOP INFINITO
        if(super.gain == ptsContratoDesej)
            super.gain++;
        
        int qtdeFutura = Math.abs(rDia.getPos()) + super.qtde;
        double saldoFuturo = rDia.getSaldo() + (super.gain*super.qtde);
        double ptsContratoFut = saldoFuturo/qtdeFutura;
        while(ptsContratoFut < ptsContratoDesej){
            super.qtde++;
            qtdeFutura = Math.abs(rDia.getPos()) + super.qtde;
            saldoFuturo = rDia.getSaldo() + (super.gain*super.qtde);
            ptsContratoFut = saldoFuturo/qtdeFutura;
            System.out.println("Qtde = " + super.qtde + " | offset = " + super.offset +  " | Gain = " + super.gain
                                + " | PtsCont = " + ptsContratoFut + " | PtsDesej = " + ptsContratoDesej);
            if(super.qtde > rDia.getPosMaxPerm())
                break;
        }
    }
    
     /**
     * Atualiza a quantidade de contratos de forma que a Pos corresponda a lógica de 
     * Marin Galle.
     * Ex. Pos = 1 + Qtde = 1 -> Pos = 2
     * Ex. Pos = 8 + qtde = 8 -> Pos = 16
     * 
     * Se não está posicionado, não altera o valor padrão da ordem
     * @param rDia
     *
     */
    public void atualizaQtde_MartinGalle(ResumoDia rDia) {
        //PREPARA ESTRATEGIA PARA MUDAR POS, NOVA ENTRADA
        if(rDia.getPos() == 0)
            return;
        
        if(rDia.getPos() > 0)
            super.nome = "Compra Val. Médio";
        else
            super.nome = "Venda Val. Médio";
        super.qtde = Math.abs(rDia.getPos());
        
    }
    
    @Override
    boolean verificaSePodeSair(Candle candle, ResumoDia rDia) {
       return false; // OrdemSimples não deve verificar saída
    }
    
    @Override
    boolean testaSaidas(Candle candle, ResumoDia rDia) {
        return false; // OrdemSimples não deve verificar saída
    }
    
    @Override
    public void configuraLinhasEntradaESaidas(double valorReferencia) {
        if(super.atualizouReferencia)
            return;
        
        super.linhaReferencia = valorReferencia;
        super.atualizouReferencia = true;
        switch (ladoOrdem){
            case COMPRA:
                this.linhaCompra = linhaReferencia + offset;
                break;
                
            case VENDA:
                super.linhaVenda = linhaReferencia + offset;
                break;
        }
    }

    @Override
    void setNome() {
        switch (super.ladoOrdem){
            case COMPRA:
                super.nome = "Compra";
                break;
                
            case VENDA:
                super.nome = "Venda";
                break;
                
            default:
                super.nome = null;
                break;
        }
    }

    
    @Override
    public void setDistLinhaExecucao(Candle candle, ResumoDia rDia) {
        if(super.iniciada){
            super.distSaidaDoUltimoValorExec = Double.POSITIVE_INFINITY;
            return; 
        }
        
        switch (super.ladoOrdem){
            case COMPRA:
                super.distSaidaDoUltimoValorExec = Math.abs(super.linhaCompra - rDia.getUltimoValorExecutado());
                return;
                
            case VENDA:
                super.distSaidaDoUltimoValorExec = Math.abs(super.linhaVenda - rDia.getUltimoValorExecutado());
                return;
        }
    }
    
    
    
}