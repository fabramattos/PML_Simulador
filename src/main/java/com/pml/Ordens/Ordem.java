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

public abstract class Ordem implements Comparable<Ordem>, Serializable{

    protected LadoOrdem ladoOrdem;
    protected TipoAtivo ativo;
    protected LocalDateTime data, dataExecucao, horaAnalise;
    protected int qtde;
    protected double linhaReferencia, linhaCompra, linhaVenda, linhaStop,
                    delta, offset, limOp, gain, loss, trStopPtsAcionamento, trStopGainMin, trStopFrequenciaAtualizacao, linhaTrStop,
                    prejPerm, ptsContDesej, saldoDesej, distSaidaDoUltimoValorExec;
                    
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
        String retorno = "";
        if(data==null)
            retorno += "Data: null";
        else
            retorno += ("Data: " + data.getYear() + "/" + data.getMonth() + "/" + data.getDayOfMonth()
                        + " | " + data.getHour() + ":" + data.getMinute());
        retorno +=   (" | " + nome
                    + " | DistUltimaExec = "  + distSaidaDoUltimoValorExec
                    + " | Comprado = "  + comprado
                    + " | Vendido = "   + vendido
                    + " | LinC = "      + linhaCompra
                    + " | LinV = "      + linhaVenda
                    + " | LinS = "      + linhaStop
                    + " | LinTrS = "    + linhaTrStop);
        return retorno;
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

    public void setLinhaStop(double linhaStop) {
        this.linhaStop = linhaStop;
    }
    
    public double getLinhaVenda() {
        return linhaVenda;
    }

    public void setLinhaReferencia(double linhaReferencia) {
        this.linhaReferencia = linhaReferencia;
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
    
    public void setGain(double gain) {
        this.gain = gain;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public void setTrStopPtsAcionamento(double trStopPtsAcionamento) {
        this.trStopPtsAcionamento = trStopPtsAcionamento;
    }

    public void setTrStopGainMin(double trStopGainMin) {
        this.trStopGainMin = trStopGainMin;
    }

    public void setTrStopFrequenciaAtualizacao(double trStopFrequenciaAtualizacao) {
        this.trStopFrequenciaAtualizacao = trStopFrequenciaAtualizacao;
    }

    public void setTemTrStop(boolean temTrStop) {
        this.temTrStop = temTrStop;
    }

    public void setTemAlvo(boolean temAlvo) {
        this.temAlvo = temAlvo;
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

    public double getDistUltimoValorExecutado() {
        return distSaidaDoUltimoValorExec;
    }
    
    @Override
    public int compareTo(Ordem o) {
        if(this.dataExecucao == null || o.dataExecucao == null)
            return this.data.compareTo(o.data);
        return this.dataExecucao.compareTo(o.dataExecucao);
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
    
    public abstract void setDistLinhaExecucao(Candle candle, ResumoDia rDia); 
    
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
        if(!verificaSePodeEntrar(candle, rDia))
            return false;
        
        if(!testaEntrada(candle, rDia))
            return false;
        
        this.data = candle.getData();
        this.dataExecucao = candle.getData();
        rDia.setDataUltimaOrdemExec(candle.getData());
        Relatorios.gravaOrdemExecutada(this);
        return true;
    }

    
    /**
     * @return TRUE se executou a ordem
     */
    public boolean verificaExecucaoSaidas(Candle candle, ResumoDia rDia) {
        if(!this.iniciada)
            return false;
        
        //Verificando se alguma outra ordem (entrada) na lista seria executada no mesmo candle.
        //Caso sim -> força a verificação da ordem atual (saida)
        //Pode não ser o mais preciso como analisar se poderia sair ou não, ou usar o fechamento como garantia,
        //mas até o momento é a melhor forma de preservar a logica da operação quando se trabalha com lista de ordens
        for(Ordem ord : rDia.getListaOrdensDia()){
            if(ord.testaEntrada_SemExecucao(candle, rDia)){
                this.podeSair = true;
                break;
            }
        }
        
        //TESTA SIMULTANEIDADE E VERIFICASEPODESAIR PODEM PERMUTAR DE LOCAL
        if(testaSimultaneidade_SemExecucao(candle, rDia)){
            this.simultaneo = true;
            rDia.setSimultaneo(rDia.getSimultaneo() + 1);
            testaStops(candle, rDia);
            this.encerrada = true;
            this.dataExecucao = candle.getData();
            rDia.setDataUltimaOrdemExec(candle.getData());
            Relatorios.gravaOrdemExecutada(this);
            return true;
        }
        
        
        if(!verificaSePodeSair(candle, rDia))
            return false;
        
        
        if(!testaStops(candle, rDia) && !testaGain(candle, rDia))
            return false;
        
        this.encerrada = true;
        this.dataExecucao = candle.getData();
        rDia.setDataUltimaOrdemExec(candle.getData());
        Relatorios.gravaOrdemExecutada(this);
        return true;
    }

    /**
     * @return TRUE se executou algum Stop
     */
    private boolean testaStops(Candle candle, ResumoDia rDia){
        return (testaTrailingStop(candle, rDia) || testaStop(candle, rDia));
    }
    
    
    abstract boolean testaTrailingStop(Candle candle, ResumoDia rDia);
    abstract boolean testaStop(Candle candle, ResumoDia rDia);
    
    private boolean testaStops_SemExecucao(Candle candle, ResumoDia rDia){
        return (testaTrailingStop_SemExecucao(candle, rDia) || testaStop_SemExecucao(candle, rDia));
    }

    private boolean testaTrailingStop_SemExecucao(Candle candle, ResumoDia rDia) {
        if (!this.temTrStop)
            return false;
        
        if(trStopTentaIniciar(candle))
            trStopAtualiza(candle);
       
        if(!this.trIniciado)
            return false;
        
        // VERIFICA TRAILING STOP
        if (rDia.getPos() > 0 && !this.vendido && this.comprado && (candle.getMinima() <= this.linhaTrStop))
            return true;
        
        if (rDia.getPos() < 0 && !this.comprado && this.vendido && (candle.getMaxima() >= this.linhaTrStop))
            return true;

        return false;
    }
    
     boolean testaStop_SemExecucao(Candle candle, ResumoDia rDia) {
        if(!this.temStop)
            return false;
        
        //VERIFICAÇÃO STOP
        if (!this.vendido && this.comprado && (candle.getMinima() <= this.linhaStop))
            return true;
        
        if (!this.comprado && this.vendido && (candle.getMaxima() >= this.linhaStop))
            return true;

        return false;
    }
    
    /**
     * Verifica se as condições para iniciar o Trailing Stop foram atingidas
     */
    abstract boolean trStopTentaIniciar(Candle candle);
    
    /**
     * Atualiza a linha de saída pelo Trailing Stop
     */
    abstract void trStopAtualiza(Candle candle);
    

    abstract boolean testaGain(Candle candle, ResumoDia rDia);
    
    private boolean testaGain_SemExecucao(Candle candle, ResumoDia rDia){
        if (!this.temAlvo)
            return false;
        
        if(this.comprado && candle.getMaxima() > this.linhaVenda)
            return true;
        
        if(this.vendido && candle.getMinima() < this.linhaCompra)
            return true;
        
        return false;
    }
    
    private boolean testaSimultaneidade_SemExecucao(Candle candle, ResumoDia rDia){
        return (testaStops_SemExecucao(candle, rDia) && testaGain_SemExecucao(candle, rDia));
    }

    private boolean testaEntrada_SemExecucao(Candle candle, ResumoDia rDia) {
        if(!verificaSePodeEntrar(candle, rDia))
            return false;
        
        switch (this.ladoOrdem){
        
            case COMPRA:
                return candle.getMaxima() > this.linhaCompra && this.linhaCompra > candle.getMinima();
        
            case VENDA:
                return candle.getMaxima() > this.linhaVenda && this.linhaVenda > candle.getMinima();
                
            default:
                return false;
        }
    }
    
}