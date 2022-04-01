/**
 * Classe que representa o conteito de ORDEM. Possui parametros comuns entre
 * Ordem OCO, Ordem Simples e OrdemGerRisco
 */

package com.pml.Ordens;

import com.pml.Configuracoes.ConfigBase;
import com.pml.Controladores.ControleTempo;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Objects;
import com.pml.simulacao.Candle;
import com.pml.Resumos.Relatorios;
import com.pml.Resumos.ResumoDia;
import java.util.Comparator;

public abstract class Ordem implements Comparable<Ordem>, Serializable{

    protected LadoOrdem ladoOrdem;
    protected TipoAtivo ativo;
    protected LocalDateTime data, dataExecucao, horaAnalise;
    protected int qtde;
    protected double linhaReferencia, linhaCompra, linhaVenda, linhaStop,
                    delta, offset, limOp, gain, loss, trStopPtsAcionamento, trStopGainMin, trStopFrequenciaAtualizacao, linhaTrStop,
                    prejPerm, ptsContDesej, saldoDesej, distAberturaCandle;
                    
    protected boolean stopExecutado, alvoExecutado, trStopExecutado,
                    temDelta, temOffset, temLimOp, temAlvo, temStop, temTrStop, temContMov, 
                    simultaneo, gerenciamentoDeRisco,
                    comprado, iniciada, vendido, trIniciado,
                    podeSair, atualizouReferencia, encerrada;
    protected String nome;

    // Construtor
    public Ordem(){
        
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }
    
    public void setLadoOrdem(LadoOrdem ladoOrdem){
        this.ladoOrdem = ladoOrdem;
        switch (this.ladoOrdem){
            case INDEF:
                this.linhaCompra = 0;
                this.linhaVenda = 0;
                this.linhaStop = 0;
                break;
        }
        setNome();
    }
    
    /**
     * registra o horario da execução da ordem simples/entrada da OCO
     * @param data 
     */
    public void setDataEntrada(LocalDateTime data){
        this.dataExecucao = data;
        this.data = data;
    }

    public TipoAtivo getAtivo() {
        return ativo;
    }

    public boolean isEncerrada() {
        return encerrada;
    }
    

    @Override
    public String toString() {
        if(data==null)
            return "ord não iniciada";
        
        return  "Data: " + data.getYear() + "/" + data.getMonth() + "/" + data.getDayOfMonth()
                + " | " + data.getHour() + ":" + data.getMinute()
                + " | " + nome
                + " | Comprado = "  + comprado
                + " | Vendido = "   + vendido
                + " | LinC = "      + linhaCompra
                + " | LinV = "      + linhaVenda
                + " | LinS = "      + linhaStop
                + " | LinTrS = "    + linhaTrStop;
    }
    
    public boolean isTrIniciado() {
        return trIniciado;
    }

    public LocalDateTime getHoraAnalise() {
        return horaAnalise;
    }

    public void setHoraAnalise(LocalDateTime horaAnalise) {
        this.horaAnalise = horaAnalise;
    }

    public int getQtde() {
        return qtde;
    }

    public void setQtde(int qtde) {
        this.qtde = qtde;
    }

    public double getLinhaReferencia() {
        return linhaReferencia;
    }

    /**
     * Ao informar o valorReferencia, aplica o offset/gain/loss juntamente com o LadoOrdem para preparar a ordem
     * @param valorReferencia 
     */
    public abstract void configuraLinhasEntradaESaidas(double valorReferencia);

    public boolean isTemStop() {
        return temStop;
    }

    public boolean isTemTrStop() {
        return temTrStop;
    }

    public double getLinhaCompra() {
        return linhaCompra;
    }

    public void setLinhaCompra(double linhaCompra) {
        this.linhaCompra = linhaCompra;
    }

    public double getLinhaVenda() {
        return linhaVenda;
    }

    public void setLinhaVenda(double linhaVenda) {
        this.linhaVenda = linhaVenda;
    }

    public double getLinhaStop() {
        return linhaStop;
    }

    public boolean isStopExecutado() {
        return stopExecutado;
    }

    public boolean isAlvoExecutado() {
        return alvoExecutado;
    }

    public boolean isTemAlvo() {
        return temAlvo;
    }

    public boolean isSimultaneo() {
        return simultaneo;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getNome() {
        return nome;
    }

    public LocalDateTime getDataEntrada() {
        return dataExecucao;
    }

    public LocalDateTime getData() {
        return data;
    }

    public LadoOrdem getLadoOrdem() {
        return ladoOrdem;
    }

    public double getDelta() {
        return delta;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getLimOp() {
        return limOp;
    }

    public double getGain() {
        return gain;
    }

    public double getLoss() {
        return loss;
    }

    public double getTrStopPtsAcionamento() {
        return trStopPtsAcionamento;
    }

    public double getTrStopGainMin() {
        return trStopGainMin;
    }

    public double getPrejPerm() {
        return prejPerm;
    }

    public double getPtsContDesej() {
        return ptsContDesej;
    }

    public double getSaldoDesej() {
        return saldoDesej;
    }

    public boolean isTemDelta() {
        return temDelta;
    }

    public boolean isTemOffset() {
        return temOffset;
    }

    public void setTemOffset(boolean temOffset) {
        this.temOffset = temOffset;
    }
    
    public boolean isTemLimOp() {
        return temLimOp;
    }

    public void setTemLimOp(boolean temLimOp) {
        this.temLimOp = temLimOp;
    }
    
    public void setTemContMov(boolean temContMov) {
        this.temContMov = temContMov;
    }

    public boolean isTemContMov() {
        return temContMov;
    }

    public boolean isGerenciamentoDeRisco() {
        return gerenciamentoDeRisco;
    }

    public boolean isComprado() {
        return comprado;
    }

    public boolean isIniciada() {
        return iniciada;
    }

    public double getLinhaTrStop() {
        return linhaTrStop;
    }

    public boolean isTrStopExecutado() {
        return trStopExecutado;
    }

    public boolean isVendido() {
        return vendido;
    }

    @Override
    public int compareTo(Ordem o) {
        if(this.dataExecucao == null || o.dataExecucao == null)
            return this.data.compareTo(o.data);
        return this.dataExecucao.compareTo(o.dataExecucao);
    }
    
    public boolean isPodeSair() {
        return podeSair;
    }

    public boolean isAtualizouReferencia() {
        return atualizouReferencia;
    }

    public double getTrStopFrequenciaAtualizacao() {
        return trStopFrequenciaAtualizacao;
    }

    public double getDistAberturaCandle() {
        return distAberturaCandle;
    }
    
    /**
     * Verifica se a ordem será executada no candle passado como argumento.
     * Faz o teste de entradas e saídas
     */
    public boolean verificaExecucao(Candle candle, ResumoDia rDia){
        if(rDia.isGerRisco())
            return false;
        return (verificaExecucaoEntrada(candle, rDia) || verificaExecucaoSaidas(candle, rDia));
    }

    /**
     * Verifica horario limite para entrada.
     * Verifica Posição futura caso execute a ordem.
     * @param candle
     * @param rDia
     * @return TRUE se pode executar a ordem de entrada
     */
    private boolean verificaSePodeEntrar(Candle candle, ResumoDia rDia) {
        if(this.iniciada)
            return false;
        
        if(rDia.isGerRisco())
            return false;
        
        return verificaHorarioLimiteParaEntada(rDia, candle)
            && verificaPosFut(rDia);
    }
    
    
    /**
     * 
     * @return TRUE se pode operar
     */
    private boolean verificaHorarioLimiteParaEntada(ResumoDia rDia, Candle candle){
        if(!ConfigBase.isTemHorarioLimEntrada())
            return true;
        
        switch (this.ladoOrdem){
            case COMPRA:
                if(rDia.getPos() < 0 )
                    return true;
                return new ControleTempo().verificaHorarioLimEntrada(candle);
                
                
            case VENDA:
                if(rDia.getPos() > 0)
                    return true;
                return new ControleTempo().verificaHorarioLimEntrada(candle);
        }
        return false;
    }
    
    /**
     * Compara a quantidade da ord a ser executada com a posição atual do dia
     * e com a posição máxima permitida
     * @param rDia
     * @return TRUE se pode executar a ord
     */
    private boolean verificaPosFut(ResumoDia rDia) {
        switch (this.ladoOrdem) {
            case COMPRA:                
                if (rDia.getPos() >= Math.abs(rDia.getPosMaxPerm()))
                    return false;
                if ((rDia.getPos() + this.qtde) > rDia.getPosMaxPerm())
                    return false;
                break;
                
            
            case VENDA:
                if (rDia.getPos() <= -1 * Math.abs(rDia.getPosMaxPerm()))
                    return false;
                if (Math.abs(rDia.getPos() - this.qtde) > rDia.getPosMaxPerm())
                    return false;
                break;
        }
        return true;
    }

   /**
     * utilizado para gravar no excel as listas de ordens fixas executadas em algumas simulações
     * @return 
     */
    public String printConfigOrdem(){
        String string;
        switch (this.ladoOrdem){
            case COMPRA:
                string = "Compra";
                break;
                
            case VENDA:
                string = "Venda";
                break;
                
            default:
                string = "Lado da Ordem: Indefinido";
                break;
        }
        
        if (this.temContMov)
            string = string + " | Contra Tendência";
        else
            string = string + " | Favor Tendência";
        
        string+= " | Qtde = " + this.qtde + " | Offset = " + this.offset + " | Lim Op = " + this.limOp + " | Gain = " + this.gain + " | Loss = " + this.loss;
        
        return string;
    }
    
    abstract void setNome();
    
    /**
     * @return TRUE se pode sair
     */
    abstract boolean verificaSePodeSair(Candle candle, ResumoDia rDia);
    
    /**
     * @return TRUE se realizou entrada
     */
    abstract boolean testaEntrada(Candle candle, ResumoDia rDia);
    
    /**
     * Testa as saidas (Stop, Gain, Ger.Risco)
     */
    abstract boolean testaSaidas(Candle candle, ResumoDia rDia);
    
    public abstract void setDistLinhaExecucao(Candle candle); 
    
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.ladoOrdem);
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.delta) ^ (Double.doubleToLongBits(this.delta) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.offset) ^ (Double.doubleToLongBits(this.offset) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.limOp) ^ (Double.doubleToLongBits(this.limOp) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.gain) ^ (Double.doubleToLongBits(this.gain) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.loss) ^ (Double.doubleToLongBits(this.loss) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.trStopPtsAcionamento) ^ (Double.doubleToLongBits(this.trStopPtsAcionamento) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.trStopGainMin) ^ (Double.doubleToLongBits(this.trStopGainMin) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.trStopFrequenciaAtualizacao) ^ (Double.doubleToLongBits(this.trStopFrequenciaAtualizacao) >>> 32));
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ordem other = (Ordem) obj;
        if (Double.doubleToLongBits(this.delta) != Double.doubleToLongBits(other.delta)) {
            return false;
        }
        if (Double.doubleToLongBits(this.offset) != Double.doubleToLongBits(other.offset)) {
            return false;
        }
        if (Double.doubleToLongBits(this.limOp) != Double.doubleToLongBits(other.limOp)) {
            return false;
        }
        if (Double.doubleToLongBits(this.gain) != Double.doubleToLongBits(other.gain)) {
            return false;
        }
        if (Double.doubleToLongBits(this.loss) != Double.doubleToLongBits(other.loss)) {
            return false;
        }
        if (Double.doubleToLongBits(this.trStopPtsAcionamento) != Double.doubleToLongBits(other.trStopPtsAcionamento)) {
            return false;
        }
        if (Double.doubleToLongBits(this.trStopGainMin) != Double.doubleToLongBits(other.trStopGainMin)) {
            return false;
        }
        if (Double.doubleToLongBits(this.trStopFrequenciaAtualizacao) != Double.doubleToLongBits(other.trStopFrequenciaAtualizacao)) {
            return false;
        }
        if (this.ladoOrdem != other.ladoOrdem) {
            return false;
        }
        return true;
    }

    /**
     * @return TRUE se executou a ordem
     */
    public boolean verificaExecucaoEntrada(Candle candle, ResumoDia rDia){
        if(verificaSePodeEntrar(candle, rDia))
            if(testaEntrada(candle, rDia)){
                this.data = candle.getData();
                this.dataExecucao = candle.getData();
                Relatorios.gravaOrdemExecutada(this);
                return true;
            }
        return false;
    }

    
    /**
     * @return TRUE se executou a ordem
     */
    public boolean verificaExecucaoSaidas(Candle candle, ResumoDia rDia) {
        if(verificaSePodeSair(candle, rDia))
            if(testaSaidas(candle, rDia)){
                this.encerrada = true;
                this.dataExecucao = candle.getData();
                Relatorios.gravaOrdemExecutada(this);
                return true;
            }
        // SE CHEGOU NESTE PONTO, NAO PODIA SAIR OU NAO SAIU. CASO NÃO PUDESSE SAIR NO CANDLE, FORÇA A PERMISSAO PRO CANDLE SEGUINTE
        if(this.iniciada && !this.podeSair)
            this.podeSair = true;
        
        return false;
    }
}