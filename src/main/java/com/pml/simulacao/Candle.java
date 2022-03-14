/**
 * Classe que contém todos dados do CANDLE obtido no Profit Chart
 * Ex: Planilha Excel 10min, 1min....
 *
 * Obs: MES começa em 0
 * */
package com.pml.simulacao;

import com.pml.Resumos.ResumoDia;
import com.pml.Resumos.Relatorios;
import com.pml.Excel.ExcelHandler;
import com.pml.Controladores.ControleTempo;
import com.pml.Configuracoes.ConfigBase;
import com.pml.Controladores.GerenciamentoDeRisco;
import com.pml.InterfaceGrafica.IG;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;

/**
 * @author Felipe Mattos
 */
public class Candle implements Comparable<Candle> {

    private static List<Candle> listaCandleMinuto = new ArrayList();
    private static List<Candle> listaCandleDiario = new ArrayList();
    private static double maxCandleYMinAnterior, minCandleYMinAnterior, maxCandleTemp, minCandleTemp;
    private LocalDateTime data;
    private int pos;
    private double abertura, maxima, minima, media, fechamento, indicadorExtra,
                    posValMed, saldoAbertoFechamento, saldoRealizado;
                    
    private String indicador;

    public Candle(){
    }
    
    public Candle(Candle candle) {
        this.data = candle.getData();
        this.abertura = candle.getAbertura();
        this.maxima = candle.getMaxima();
        this.minima = candle.getMinima();
        this.fechamento = candle.getFechamento();
        this.indicador = candle.getIndicador();
        this.indicadorExtra = candle.getIndicadorExtra();
        setMedia(this.abertura, this.fechamento);
    }

    public LocalDateTime getData() {
        return data;
    }

    public void setData(LocalDateTime data) {
        this.data = data;
    }

    public static double getMaxCandleYMinAnterior() {
        return maxCandleYMinAnterior;
    }

    public static void setMaxCandleYMinAnterior(double maxCandleYMinAnterior) {
        Candle.maxCandleYMinAnterior = maxCandleYMinAnterior;
    }

    public static double getMinCandleYMinAnterior() {
        return minCandleYMinAnterior;
    }

    public static void setMinCandleYMinAnterior(double minCandleYMinAnterior) {
        Candle.minCandleYMinAnterior = minCandleYMinAnterior;
    }
    
    public double getSaldoRealizado() {
        return saldoRealizado;
    }

    public void setSaldoRealizado(double saldoRealizado) {
        this.saldoRealizado = saldoRealizado;
    }

    public double getPosValMed() {
        return posValMed;
    }

    public void setPosValMed(double posValMed) {
        this.posValMed = posValMed;
    }

    public int getPos() {
        return pos;
    }

    public void setPos(int pos) {
        this.pos = pos;
    }

    public double getSaldoAbertoFechamento() {
        return saldoAbertoFechamento;
    }

    public void setSaldoAbertoFechamento(double saldoAbertoFechamento) {
        this.saldoAbertoFechamento = saldoAbertoFechamento;
    }
    
    public String getIndicador() {
        return indicador;
    }

    public void setIndicador(String indicador) {
        this.indicador = indicador;
    }

    public double getMedia() {
        return media;
    }

    public void setMedia(double valor1, double valor2) {
        this.media = Math.round(((valor1 + valor2)/2)*100)/100;
    }

    public double getIndicadorExtra() {
        return indicadorExtra;
    }

    public void setIndicadorExtra(double indicadorExtra) {
        this.indicadorExtra = indicadorExtra;
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

//    @Override
//    public String toString() {
//        return "CANDLE: Data: " + dia + "/" + (mes + 1) + "/" + ano
//                + " | " + hora + ":" + minuto
//                + " | abertura = " + abertura
//                + " | maxima = " + maxima
//                + " | minima = " + minima
//                + " | fechamento = " + fechamento
//                + " | indicador extra = " + indicadorExtra
//                + " | pos = " + pos
//                + " | posValMedio = "
//                + " | saldo Aberto (abertura) = " + saldoAbertoFechamento;
//    }
    
    @Override
    public String toString() {
        return "CANDLE: Data: " + data.getDayOfMonth() + "/" + (data.getMonth()) + "/" + data.getYear()
                + " | " + data.getHour() + ":" + data.getMinute()
                + " | Aber = " + abertura
                + " | Max = " + maxima
                + " | Min = " + minima
                + " | Fech = " + fechamento;
    }

    /**
     * Atualiza o candle com os valores do dia ate o minuto lido.Passa os valores da Posição,
     * do Valor Medio da Posição, e do Saldo Aberto comparando com o fechamento do candle.
     * 
     * Atualiza a barra de progresso detalhado e completo
     * @param rDia
     * @param swingTrade
     */
    public void registraResultados(ResumoDia rDia, boolean swingTrade){
        IG.progressoAtualiza();
        ControleTempo tempo = new ControleTempo();
        GerenciamentoDeRisco gerRisco = new GerenciamentoDeRisco();
        
        if(tempo.verificaHorarioFinal(this))
            gerRisco.encerraDia(this, swingTrade, rDia);
        
        if(!ConfigBase.isTemRelatorioCandles())
            return;
        
        this.pos = rDia.getPos();
        this.posValMed = rDia.getPosValMed();
        this.saldoRealizado = rDia.getSaldo();
        
        //CALCULANDO O RESULTADO EM ABERTO NO FECHAMENTO DO CANDLE
        
        double candleFechamento = this.fechamento;
        double saldoAberto = 0;
        if(rDia.getPos() > 0)
            saldoAberto = (candleFechamento - rDia.getPosValMed())*Math.abs(rDia.getPos());
        if(rDia.getPos() < 0)
            saldoAberto = (rDia.getPosValMed() - candleFechamento)*Math.abs(rDia.getPos());
        this.saldoAbertoFechamento = saldoAberto;
        
        if(IG.isTemRelCandles())
            Relatorios.gravaCandleOperado(this);
    }
    
    /**
     * Faz a atualização do candle intermediário (para simulações em candles de 1, 5, 10 min...)
     * e a cada periodo, os extremos são regravados, simulando os extremos mais recentes
     * 
     * Precisa reiniciar variaveis temporárias a cada inicio de simulação
     * 
     * @return TRUE se registrou o candle intermediario e ja pode operar baseado nesses dados passados 
     */
    public boolean criaCandleIntermediario(int minuto){
        Candle.maxCandleTemp = Double.max(Candle.maxCandleTemp, this.maxima);
        Candle.minCandleTemp = Double.min(Candle.minCandleTemp, this.minima);
        
        
        if (minuto == 1
        || new ControleTempo().verificaMinutoMultiplo(this, minuto)){
            Candle.maxCandleYMinAnterior = maxCandleTemp;
            Candle.minCandleYMinAnterior = minCandleTemp;
            resetaCandleIntermediárioTemp();
//            System.out.println("CANDLE: " + this.toStringDataSimples() + " | Extremo Max: " + Candle.maxCandleYMinAnterior  + " | Extremo Min: " + Candle.minCandleYMinAnterior);
            return true;
        }
        return false;
    }
    
    public static void resetaCandleIntermediárioTemp(){
        Candle.maxCandleTemp = Double.NEGATIVE_INFINITY;
        Candle.minCandleTemp = Double.POSITIVE_INFINITY;;
    }
    
    public void atualizaCandleResultados(ResumoDia rDia){
        if(!ConfigBase.isTemRelatorioCandles())
            return;
        
        this.pos = rDia.getPos();
        this.posValMed = rDia.getPosValMed();
        this.saldoRealizado = rDia.getSaldo();
        
        //CALCULANDO O RESULTADO EM ABERTO NO FECHAMENTO DO CANDLE
        double candleFechamento = this.fechamento;
        double saldoAberto = 0;
        if(rDia.getPos() > 0)
            saldoAberto = (candleFechamento - rDia.getPosValMed())*Math.abs(rDia.getPos());
        if(rDia.getPos() < 0)
            saldoAberto = (rDia.getPosValMed() - candleFechamento)*Math.abs(rDia.getPos());
        this.saldoAbertoFechamento = saldoAberto;
        Relatorios.atualizaCandleOperado(this);
    }
    
    public String printData(){
        return "Data: " + data.getDayOfMonth() + "/" + data.getMonth() + "/" + data.getYear()
                +" - " + data.getHour() + ":" + data.getMinute();
    }
    
    //Ordem natural de ordenação
    @Override
    public int compareTo(Candle c) {
        return this.data.compareTo(c.data);
    }

    
    /**
     * Limpa a lista de candles antiga, cria uma lista estática com todos os
     * candles de carregados do excel, e cria uma lista auxiliar com os candles diários.
     * @param candles 
     */
    public void coletaDados(HashSet candles) {
        try{
            listaCandleMinuto.clear();
            IG.textoAdd("Base de Dados resetada \n");
            IG.textoAdd("Coletando novos dados \n");
            listaCandleMinuto.addAll(candles);
            IG.textoAdd("Preparando os dados \n");
            listaCandleMinuto.sort(null);
            geraDiario();
        } catch(Exception e) {
            IG.textoAdd("\n-----------------------------\n");
            IG.textoAdd("ERRO na leitura do arquivo!\n");
            IG.textoAdd("-----------------------------\n");
            IG.textoAdd(e.toString());
            e.printStackTrace();
            return;
        }
    }

    private void geraDiario() {
        listaCandleDiario.clear();
        ControleTempo tempo = new ControleTempo();
        System.out.println("Gerando candles diarios \n");
        Candle candleAnterior = new Candle(listaCandleMinuto.get(0));
        Candle candleGravacao = new Candle(listaCandleMinuto.get(0));
        for (Candle candleAtual : listaCandleMinuto) {
            if (tempo.verificaPassagemDia(candleAtual.getData(), candleAnterior.getData())){
                candleGravacao.setFechamento(candleAnterior.getFechamento());
                candleGravacao.setIndicadorExtra(candleAnterior.getIndicadorExtra());
                candleGravacao.setMedia(candleGravacao.getAbertura(), candleGravacao.getFechamento());
                
                listaCandleDiario.add(candleGravacao);
                candleGravacao = new Candle(candleAtual);
            }
            candleGravacao.setMaxima(Double.max(candleGravacao.getMaxima(), candleAtual.getMaxima()));
            candleGravacao.setMinima(Double.min(candleGravacao.getMinima(), candleAtual.getMinima()));
            candleAnterior = candleAtual;
        }
        listaCandleDiario.add(candleGravacao);
        listaCandleDiario.sort(null);
        System.out.println("Candles gerados \n");
        IG.textoAdd("-----------------------------\n");
    }

    public String toStringDataSimples() {
        return "CANDLE: Data: " + data.getDayOfMonth() + "/" + data.getMonth() + "/" + data.getYear()
                +" - " + data.getHour() + ":" + data.getMinute();
    }

    public static List<Candle> getListaCandleMinuto() {
        return Collections.unmodifiableList(listaCandleMinuto);
    }

    public static List<Candle> getListaCandleDiario() {
        return Collections.unmodifiableList(listaCandleDiario);
    }
    
    /**
     * apaga lista minuto e lista diario
     */
    public static void apagaListasCandles(){
        listaCandleMinuto.clear();
        listaCandleDiario.clear();
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 31 * hash + Objects.hashCode(this.data);
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
        final Candle other = (Candle) obj;
        if (!Objects.equals(this.data, other.data)) {
            return false;
        }
        return true;
    }
 
    
    
}
