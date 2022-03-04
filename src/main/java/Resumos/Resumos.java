package Resumos;

import Configuracoes.ConfigOrdens;
import java.io.Serializable;
import static java.lang.Math.abs;
import java.time.LocalDateTime;
import java.util.Comparator;
import java.util.List;

/**
 *
 * @author Felipe Mattos
 */
public abstract class Resumos implements Serializable{
    
    // Parâmetros
    protected LocalDateTime data;
    
    protected int
    entradas, saidas, qtdeMax, qtdeNegociada, qtdeNegociadaSerie,
    stops, tStops, pos, posMax, posMaxPerm, simultaneo,
    diasPos, diasNeg;
    
    protected double
    abertura, maxima, minima, fechamento, indicadorExtra, media, aberturaSerie,
    delta, offset, limOposto, gain, loss, gAnalise, lAnalise, saldoAbertoNeg, saldoAbertoNeg_Max, prejAcum,
    trStopAcionamento, trStopPtsMin, trStopFreqAtualizacao, maxLocal, minLocal,
    gerRisco_Saldo, gerRisco_PtsCont, gerRisco_PrejPerm,
    saldo, saldoSerie, saldoMin, saldoContrato, saldoContratoSerie, saldoAcumulado,
    posValMed, posValTotal, saldoMax, prejAcumMax, saldoPrejMax, saldoDrawDown, saodoAbertoMin_Min;
    
    protected boolean
    gerRisco, ordemExecutada, atualizouReferencia, podeReposicionarPelaAbertura, gravado;
    
    Resumos(){
        this.posMaxPerm = ConfigOrdens.getPosMaxPerm();
        this.delta = ConfigOrdens.getDelta();
        this.offset = ConfigOrdens.getOffset();
        this.limOposto = ConfigOrdens.getLimOp();
        this.gain = ConfigOrdens.getGain();
        this.loss = ConfigOrdens.getLoss();
        this.trStopAcionamento = ConfigOrdens.getTrStopAcionamento();
        this.trStopPtsMin = ConfigOrdens.getTrStopPtsMin();
        this.trStopFreqAtualizacao = ConfigOrdens.getTrStopFrequeAtualiza();
        this.gerRisco_Saldo = ConfigOrdens.getGerRisco_SaldoDesej_Original();
        this.gerRisco_PtsCont = ConfigOrdens.getGerRisco_PtsCont();
        this.gerRisco_PrejPerm = ConfigOrdens.getGerRisco_PrejPerm();
        this.maxLocal = Double.NEGATIVE_INFINITY;
        this.minLocal = Double.POSITIVE_INFINITY;
        this.maxima = Double.NEGATIVE_INFINITY;
        this.minima = Double.POSITIVE_INFINITY;
        this.podeReposicionarPelaAbertura = true;
        this.gravado = false;
        this.gerRisco = false;
    }

    public boolean isGravado() {
        return gravado;
    }

    public void setGravado(boolean gravado) {
        this.gravado = gravado;
    }

    public boolean isPodeReposicionarPelaAbertura() {
        return podeReposicionarPelaAbertura;
    }

    public double getTrStopFreqAtualizacao() {
        return trStopFreqAtualizacao;
    }

    public void setPodeReposicionarPelaAbertura(boolean podeReposicionarPelaAbertura) {
        this.podeReposicionarPelaAbertura = podeReposicionarPelaAbertura;
    }

    public void setTrStopFreqAtualizacao(double trStopFreqAtualizacao) {
        this.trStopFreqAtualizacao = trStopFreqAtualizacao;
    }

    public double getSaodoAbertoMin_Min() {
        return saodoAbertoMin_Min;
    }

    public void setSaodoAbertoMin_Min(double saodoAbertoMin_Min) {
        this.saodoAbertoMin_Min = saodoAbertoMin_Min;
    }

    public int getDiasPos() {
        return diasPos;
    }

    public void setDiasPos(int diasPos) {
        this.diasPos = diasPos;
    }

    public int getDiasNeg() {
        return diasNeg;
    }

    public void setDiasNeg(int diasNeg) {
        this.diasNeg = diasNeg;
    }

    public double getSaldoMax() {
        return saldoMax;
    }

    public void setSaldoMax(double saldoMax) {
        this.saldoMax = saldoMax;
    }

    public double getPrejAcumMax() {
        return prejAcumMax;
    }

    public void setPrejAcumMax(double prejAcumMax) {
        this.prejAcumMax = prejAcumMax;
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public int getEntradas() {
        return entradas;
    }

    public void setEntradas(int entradas) {
        this.entradas = entradas;
    }

    public int getSaidas() {
        return saidas;
    }

    public void setSaidas(int saidas) {
        this.saidas = saidas;
    }
    
    public int getQtdeMax() {
        return qtdeMax;
    }

    public int getQtdeNegociada() {
        return qtdeNegociada;
    }

    public void setQtdeNegociada(int qtdeNegociada) {
        this.qtdeNegociada = qtdeNegociada;
    }

    public int getQtdeNegociadaSerie() {
        return qtdeNegociadaSerie;
    }

    public void setQtdeNegociadaSerie(int qtdeNegociadaSerie) {
        this.qtdeNegociadaSerie = qtdeNegociadaSerie;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getTrStops() {
        return tStops;
    }

    public void settStops(int tStops) {
        this.tStops = tStops;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int qtde) {
        this.pos = qtde;
    }
    
    public int getPosMax() {
        return posMax;
    }

    public void setPosMax(int posMax) {
        this.posMax = posMax;
    }

    public int getPosMaxPerm() {
        return posMaxPerm;
    }

    public void setPosMaxPerm(int posMaxPerm) {
        this.posMaxPerm = posMaxPerm;
    }

    public int getSimultaneo() {
        return simultaneo;
    }

    public void setSimultaneo(int simultaneo) {
        this.simultaneo = simultaneo;
    }

    public double getAbertura() {
        return abertura;
    }

    public void setAbertura(double abertura) {
        this.abertura = abertura;
    }

    public double getMaxima() {
        return maxima;
    }

    public void setMaxima(double maxima) {
        this.maxima = maxima;
    }

    public double getMinima() {
        return minima;
    }

    public void setMinima(double minima) {
        this.minima = minima;
    }

    public double getFechamento() {
        return fechamento;
    }

    public void setFechamento(double fechamento) {
        this.fechamento = fechamento;
    }

    public double getIndicadorExtra() {
        return indicadorExtra;
    }

    public void setIndicadorExtra(double indicadorExtra) {
        this.indicadorExtra = indicadorExtra;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double max, double min) {
        this.media = (max + min)/2;
    }

    public double getAberturaSerie() {
        return aberturaSerie;
    }

    public void setAberturaSerie(double aberturaSerie) {
        this.aberturaSerie = aberturaSerie;
    }

    public double getDelta() {
        return delta;
    }

    public void setDelta(double delta) {
        this.delta = delta;
    }

    public double getOffset() {
        return offset;
    }

    public void setOffset(double offset) {
        this.offset = offset;
    }

    public double getLimOposto() {
        return limOposto;
    }

    public void setLimOposto(double limOposto) {
        this.limOposto = limOposto;
    }

    public double getGain() {
        return gain;
    }

    public void setGain(double gain) {
        this.gain = gain;
    }

    public double getLoss() {
        return loss;
    }

    public void setLoss(double loss) {
        this.loss = loss;
    }

    public double getgAnalise() {
        return gAnalise;
    }

    public void setgAnalise(double gAnalise) {
        this.gAnalise = gAnalise;
    }

    public double getlAnalise() {
        return lAnalise;
    }

    public void setlAnalise(double lAnalise) {
        this.lAnalise = lAnalise;
    }

    public double getSaldoAbertoNeg() {
        return saldoAbertoNeg;
    }

    public double getPrejAcum() {
        return prejAcum;
    }

    public void setPrejAcum(double prejAcum) {
        this.prejAcum = prejAcum;
    }

    public double getTrStopAcionamento() {
        return trStopAcionamento;
    }

    public void setTrStopAcionamento(double trStopAcionamento) {
        this.trStopAcionamento = trStopAcionamento;
    }

    public double getTrStopPtsMin() {
        return trStopPtsMin;
    }

    public void setTrStopPtsMin(double trStopPtsMin) {
        this.trStopPtsMin = trStopPtsMin;
    }

    public double getMaxLocal() {
        return maxLocal;
    }

    public void setMaxLocal(double maxLocal) {
        this.maxLocal = maxLocal;
    }

    public double getMinLocal() {
        return minLocal;
    }

    public void setMinLocal(double minLocal) {
        this.minLocal = minLocal;
    }

    public double getGerRisco_Saldo() {
        return gerRisco_Saldo;
    }

    public void setGerRisco_Saldo(double gerRisco_Saldo) {
        this.gerRisco_Saldo = gerRisco_Saldo;
    }

    public double getGerRisco_PtsCont() {
        return gerRisco_PtsCont;
    }

    public void setGerRisco_PtsCont(double gerRisco_PtsCont) {
        this.gerRisco_PtsCont = gerRisco_PtsCont;
    }

    public double getGerRisco_PrejPerm() {
        return gerRisco_PrejPerm;
    }

    public void setGerRisco_PrejPerm(double gerRisco_PrejPerm) {
        this.gerRisco_PrejPerm = gerRisco_PrejPerm;
    }

    public double getSaldo() {
        return saldo;
    }

    public void setSaldo(double saldo) {
        this.saldo = saldo;
    }

    public double getSaldoSerie() {
        return saldoSerie;
    }

    public double getSaldoMin() {
        return saldoMin;
    }

    public void setSaldoMin(double saldoMin) {
        this.saldoMin = saldoMin;
    }

    public double getSaldoContrato() {
        return saldoContrato;
    }

    public void setSaldoContrato(double saldoContrato) {
        this.saldoContrato = saldoContrato;
    }

    public double getSaldoContratoSerie() {
        return saldoContratoSerie;
    }

    public void setSaldoContratoSerie(double saldoContratoSerie) {
        this.saldoContratoSerie = saldoContratoSerie;
    }

    public double getSaldoAcumulado() {
        return saldoAcumulado;
    }

    public void setSaldoAcumulado(double saldoAcumulado) {
        this.saldoAcumulado = saldoAcumulado;
    }

    public double getPosValMed() {
        return posValMed;
    }

    public void setPosValMed(double posValMed) {
        this.posValMed = posValMed;
    }

    public void setPosValTotal(double posValTotal) {
        this.posValTotal = posValTotal;
    }

    public double getPosValTotal() {
        return posValTotal;
    }

    public void posValTotal_Soma(double valor) {
        this.posValTotal+= valor;
    }
    
    public void posValTotal_Subtrai(double valor) {
        this.posValTotal-= valor;
    }

    
    /**
     * 
     * @return TRUE se o Ger.Risco foi acionado
     */
    public boolean isGerRisco() {
        return gerRisco;
    }

    public boolean isOrdemExecutada() {
        return ordemExecutada;
    }

    public void setOrdemExecutada(boolean ordemExecutada) {
        this.ordemExecutada = ordemExecutada;
    }

    public boolean isAtualizouReferencia() {
        return atualizouReferencia;
    }

    public void setAtualizouReferencia(boolean atualizouReferencia) {
        this.atualizouReferencia = atualizouReferencia;
    }

    public int gettStops() {
        return tStops;
    }

    public double getSaldoAbertoNeg_Max() {
        return saldoAbertoNeg_Max;
    }

    public double getSaldoPrejMax() {
        return saldoPrejMax;
    }

    public double getSaldoDrawDown() {
        return saldoDrawDown;
    }
    
    /**
     * Gera o resumo para o período desejado, dependendo da instancia que chamou o método
     * Ex. ResumoMes -> Gera relatorio mensal na classe ResumoMes
     */
    public void geraResumo(){
        Resumos resumo = criaInstancia();
        List<Resumos> diario = Relatorios.getRelatorioDiario();
        resumo.setData(diario.get(0).getData());
        for (int i = 0; i < diario.size(); i++){
            try{
                if (verificaPassagemPeriodo(diario.get(i), diario.get(i-1))){
                    gravaResumo(resumo);
                    resumo = criaInstancia();
                    resumo.setData(diario.get(i).getData());
                }
            }catch(IndexOutOfBoundsException e){} // -1, primeira iteração
            
            resumo.maxima = Double.max(resumo.maxima, diario.get(i).getMaxima());
            resumo.minima = (Double.min(resumo.minima, diario.get(i).getMinima()));
            resumo.qtdeMax = Integer.max(resumo.qtdeMax, diario.get(i).getQtdeMax());
            resumo.saldoAbertoNeg_Max = Double.min(resumo.saldoAbertoNeg_Max, diario.get(i).getSaldoAbertoNeg());
            if(diario.get(i).getSaldo() < 0)
                resumo.diasNeg++;
            else
                resumo.diasPos++;
            resumo.prejAcum = Double.min(resumo.prejAcum +  diario.get(i).getSaldo(), 0);
            resumo.prejAcumMax = Double.min(resumo.prejAcumMax, resumo.prejAcum);
            resumo.simultaneo += diario.get(i).getSimultaneo();
            resumo.qtdeNegociada += diario.get(i).getQtdeNegociada();
            resumo.stops += diario.get(i).getStops();
            resumo.tStops += diario.get(i).getTrStops();
            resumo.saldo += diario.get(i).getSaldo();
            resumo.saldoMax = Double.max(resumo.saldoMax, resumo.saldo);
            resumo.saldoMin = Double.min(resumo.saldoMin, diario.get(i).getSaldoMin());
            resumo.entradas += diario.get(i).getEntradas();
            resumo.saidas += diario.get(i).getSaidas();
            resumo.fechamento = diario.get(i).getFechamento();
            resumo.saldoContrato = resumo.saldo/resumo.qtdeNegociada;
            if(Math.abs(resumo.posMax) < Math.abs(resumo.pos))
                resumo.posMax = resumo.pos;
        }
        resumo.saldoPrejMax = resumo.saldoMax/abs(resumo.prejAcumMax);
        resumo.saldoDrawDown = resumo.saldoMax/abs(resumo.saldoAbertoNeg_Max);
        gravaResumo(resumo);
    }
    
    @Override
    public String toString() {
        return "Data: " + data.getDayOfMonth() + "/" + data.getMonth() + "/" + data.getYear()
                + " | abe = "           + abertura
                + " | max = "           + maxima
                + " | min = "           + minima
                + " | fech = "          + fechamento
                + " | ind = "           + indicadorExtra
                + " | pos = "           + pos
                + " | saldo = "         + saldo
                + " | saldoSerie = "    + saldoSerie
                + " | GR Saldo = "      + gerRisco_Saldo;
    }
    
    
    public static Comparator<Resumos> comparadorPtsContrato = (Resumos o1, Resumos o2) -> {
        Double valor1 = o1.getSaldoContrato();
        Double valor2 = o2.getSaldoContrato();
        
        return valor1.compareTo(valor2);
    };
    
    public static Comparator<Resumos> comparadorSaldoAcum = (Resumos o1, Resumos o2) -> {
        Double valor1 = o1.getSaldoAcumulado();
        Double valor2 = o2.getSaldoAcumulado();
        
        return valor1.compareTo(valor2);
    };

    public abstract void gravaResumo(Resumos resumo);

    public abstract boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior);

    public abstract Resumos criaInstancia();

}