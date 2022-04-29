package com.pml.Resumos;

import com.pml.ArquivosTemporarios.ArquivoTemp;
import com.pml.Ordens.Ordem;
import com.pml.Controladores.ControleTempo;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Configuracoes.ConfigBase;
import com.pml.Excel.AcoesExcel;
import com.pml.Excel.ExcelCandleResultados;
import com.pml.Excel.ExcelCompleto;
import com.pml.Excel.ExcelCompletoDiario;
import com.pml.Excel.ExcelConfiguracoes;
import com.pml.Excel.ExcelDiario;
import com.pml.Excel.ExcelHandler;
import com.pml.Excel.ExcelMensal;
import com.pml.Excel.ExcelOrdensExecutadas;
import com.pml.Excel.ExcelOrdensSugeridas;
import com.pml.InterfaceGrafica.IG;
import com.pml.Simulacoes.Simulacao;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import com.pml.simulacao.Candle;
import com.pml.simulacao.Clone;

/**
 * @author Felipe Mattos
 **/

public class Relatorios{
    
    /**
     * todos relatórios que serão gerados para serem exportados em ExcelHandler estão nesta Classe
     */
    private static List<Resumos> relatorioDiario = new ArrayList<>();
    private static List<Resumos> relatorioDiasPosStop = new ArrayList<>();
    private static List<List<Resumos>> relatorioCompletoDiario = new ArrayList<>();
    private static List<Resumos> relatorioMensal = new ArrayList<>();
    private static List<Resumos> relatorioAnual = new ArrayList<>();
    private static List<Resumos> relatorioCompleto = new ArrayList<>();
    private static List<Ordem> relatorioOrdensExecutadas = new ArrayList<>();
    private static List<Resumos> relatorioCompletoPosPeriodo = new ArrayList<>();
    private static List<Candle> relatorioCandleMinutoOperado = new ArrayList<>();
    /**
     * estrategiaSugerida. Lista que contem as estratégias ja para as datas que serão utilizadas.
     * Primeira lista representa o dia, segunda lista contem as estrategias em si
     * Simulações e análises geram a lista, e a classe SimulacaoAplicada poe a lista em execução
     */
    private static List<List<Ordem>> relatorioOrdensSugeridas = new ArrayList();
    private static boolean gravouRelatorios = false;

    /**
     * 
     * @param rDia a ser procurado
     * @return TRUE se o dia ja foi gravado na lista
     */
    public static boolean verificaDiaGravado(ResumoDia rDia) {
        return relatorioDiario.contains(rDia);
    }

    //ATRIBUTOS
    private int agrupamento, posMaxIni, posMaxFin, passoPosMax;
    private double deltaIni, deltaFin, limOpIni, limOpFin,
                    offsetIni, offsetFin, gIni, gFin, lIni, lFin, 
                    gerRisco_SaldoIni, gerRisco_SaldoFin, gerRisco_SaldoMinimo,
                    gerRisco_PtsContIni, gerRisco_PtsContFin,
                    gerRisco_PrejPermIni, gerRisco_PrejPermFin,
                    trStopAcionamentoIni, trStopAcionamentoFin,
                    trStopPtsMinIni, trStopPtsMinFin,
                    trStopFreqAtualizacaoIni, trStopFreqAtualizacaoFin,
                    passo, passoDelta, passoOffset, passoLimOp, passoGain, passoLoss,
                    passoGerRisco_Saldo, passoGerRisco_PtsCont, passoGerRisco_PrejPerm,
                    passoTrStop_Acionamento, passoTrStop_PtsMin, passoTrStop_FreqAtualizacao;
                    
    private boolean temAtualQtde, temDelta, temLimOp, temOffset, temGain, temLoss,
                    temGerRisco_Saldo, temGerRisco_PtsCont, temGerRisco_PrejPerm, temTrStop;
    private boolean atualizaGainIgualLoss;
    private int simulacoesTotais;
    
    // Métodos Especiais
    
    public Relatorios(){
    }

    public static void gravaOrdemExecutada(Ordem ord){
        if(ord.getData() == null){
            System.out.println(ord);
            return;
        }
        Ordem ordClone = (Ordem) Clone.deepClone(ord);
        relatorioOrdensExecutadas.add(ordClone);
    }
    
    public static void removeUltimaOrdemExecutada(){
        System.out.println("ultima ordem: " + relatorioOrdensExecutadas.get(relatorioOrdensExecutadas.size()-1));
        relatorioOrdensExecutadas.remove(relatorioOrdensExecutadas.size()-1);
    }
    
    public static void gravaResumoMensal(Resumos resumo){
        relatorioMensal.add(resumo);
    }
    
    public static void gravaResumoAnual(Resumos resumo){
        relatorioAnual.add(resumo);
    }
    
    public static void gravaResumoCompleto(Resumos resumo){
        relatorioCompleto.add(resumo);
    }
    
     public static void gravaResumoCompletoDiario(){
        List<Resumos> clone = (List<Resumos>) Clone.deepClone(relatorioDiario);
        relatorioCompletoDiario.add(clone);
    }
    
    /**
     * retorna uma lista de lista imodificável com as ordens sugeridas para todas datas analisadas
     * lista externa -> lista de ordens para o dia
     * lista interna -> ordens
     * @return 
     */
    public static List<List<Ordem>> getListaOrdensSugeridas (){
        List<List<Ordem>> lista = Collections.unmodifiableList(relatorioOrdensSugeridas);
        return lista;
    }
    
    
    
    public double getPassoTrStop_Acionamento() {
        return passoTrStop_Acionamento;
    }

    public void setPassoTrStop_Acionamento(double passoTrStop_Acionamento) {
        this.passoTrStop_Acionamento = passoTrStop_Acionamento;
    }

    public double getPassoTrStop_PtsMin() {
        return passoTrStop_PtsMin;
    }

    public void setPassoTrStop_PtsMin(double passoTrStop_PtsMin) {
        this.passoTrStop_PtsMin = passoTrStop_PtsMin;
    }

    public double getPassoTrStop_FreqAtualizacao() {
        return passoTrStop_FreqAtualizacao;
    }

    public void setPassoTrStop_FreqAtualizacao(double passoTrStop_FreqAtualizacao) {
        this.passoTrStop_FreqAtualizacao = passoTrStop_FreqAtualizacao;
    }
    
    public boolean isTemDelta() {
        return temDelta;
    }

    public boolean isTemOffset() {
        return temOffset;
    }

    public boolean isTemLimOp() {
        return temLimOp;
    }

    public boolean isTemGain() {
        return temGain;
    }

    public boolean isTemGerRisco_Saldo() {
        return temGerRisco_Saldo;
    }

    public boolean isTemGerRisco_PtsCont() {
        return temGerRisco_PtsCont;
    }

    public boolean isTemLoss() {
        return temLoss;
    }
    
    public int getAgrupamento() {
        return agrupamento;
    }
    
    public void setAgrupamento(int agrupamento){
        this.agrupamento = agrupamento;
    }

    public double getTrStopAcionamentoIni() {
        return trStopAcionamentoIni;
    }

    public void setTrStopAcionamentoIni(double trStopAcionamentoIni) {
        this.trStopAcionamentoIni = trStopAcionamentoIni;
    }

    public double getTrStopAcionamentoFin() {
        return trStopAcionamentoFin;
    }

    public void setTrStopAcionamentoFin(double trStopAcionamentoFin) {
        this.trStopAcionamentoFin = trStopAcionamentoFin;
    }

    public double getTrStopPtsMinFin() {
        return trStopPtsMinFin;
    }

    public void setTrStopPtsMinFin(double trStopPtsMinFin) {
        this.trStopPtsMinFin = trStopPtsMinFin;
    }

    public double getTrStopFreqAtualizacaoIni() {
        return trStopFreqAtualizacaoIni;
    }

    public void setTrStopFreqAtualizacaoIni(double trStopFreqAtualizacaoIni) {
        this.trStopFreqAtualizacaoIni = trStopFreqAtualizacaoIni;
    }

    public double getTrStopFreqAtualizacaoFin() {
        return trStopFreqAtualizacaoFin;
    }

    public void setTrStopFreqAtualizacaoFin(double trStopFreqAtualizacaoFin) {
        this.trStopFreqAtualizacaoFin = trStopFreqAtualizacaoFin;
    }
    
    private void resetLoop(){
        this.temAtualQtde = ConfigOrdens.isTemAtualQtde();
        this.temDelta = ConfigOrdens.isTemDelta();
        this.temLimOp = ConfigOrdens.isTemLimOp();
        this.temOffset = ConfigOrdens.isTemOffset();
        this.temGain = ConfigOrdens.isTemGain();
        this.temLoss = ConfigOrdens.isTemLoss();
        this.temTrStop = ConfigOrdens.isTemTrStop();
        this.temGerRisco_Saldo = ConfigOrdens.isTemGerRisco_Saldo();
        this.temGerRisco_PtsCont = ConfigOrdens.isTemGerRisco_PtsCont();
        this.temGerRisco_PrejPerm = ConfigOrdens.isTemGerRisco_PrejPerm();
        this.posMaxIni = ConfigOrdens.getPosMaxPerm();
        this.posMaxFin = ConfigOrdens.getPosMaxPermFin();
        this.deltaIni = ConfigOrdens.getDeltaIni();
        this.deltaFin = ConfigOrdens.getDeltaFin();
        this.limOpIni = ConfigOrdens.getLimOpIni();
        this.limOpFin = ConfigOrdens.getLimOpFin();
        this.offsetIni = ConfigOrdens.getOffsetIni();
        this.offsetFin = ConfigOrdens.getOffsetFin();
        this.gIni = ConfigOrdens.getGainIni();
        this.gFin = ConfigOrdens.getGainFin();
        this.lIni = ConfigOrdens.getLossIni();
        this.lFin = ConfigOrdens.getLossFin();
        this.gerRisco_SaldoIni = ConfigOrdens.getGerRisco_SaldoDesejIni();
        this.gerRisco_SaldoFin = ConfigOrdens.getGerRisco_SaldoDesejFin();
        this.gerRisco_SaldoMinimo = ConfigOrdens.getGerRisco_SaldoDesejIni();
        this.gerRisco_PtsContIni = ConfigOrdens.getGerRisco_PtsContIni();
        this.gerRisco_PtsContFin = ConfigOrdens.getGerRisco_PtsContFin();
        this.gerRisco_PrejPermIni = ConfigOrdens.getGerRisco_PrejPermIni();
        this.gerRisco_PrejPermFin = ConfigOrdens.getGerRisco_PrejPermFin();
        this.trStopAcionamentoIni = ConfigOrdens.getTrStopAcionamentoIni();
        this.trStopAcionamentoFin = ConfigOrdens.getTrStopAcionamentoFin();
        this.trStopPtsMinIni = ConfigOrdens.getTrStopPtsMinIni();
        this.trStopPtsMinFin = ConfigOrdens.getTrStopPtsMinFin();
        this.trStopFreqAtualizacaoIni = ConfigOrdens.getTrStopFrequeAtualizaIni();
        this.trStopFreqAtualizacaoFin = ConfigOrdens.getTrStopFrequeAtualizaFin();
        this.passo = ConfigOrdens.getPasso();
        this.passoPosMax = Integer.max(1, ConfigOrdens.getPassoQtde());
        this.passoDelta = Double.max(0.01, ConfigOrdens.getPassoDelta());
        this.passoOffset = Double.max(0.01, ConfigOrdens.getPassoOffset());
        this.passoLimOp = Double.max(0.01, ConfigOrdens.getPassoLimOp());
        this.passoGain = Double.max(0.01, ConfigOrdens.getPassoGain());
        this.passoLoss = Double.max(0.01, ConfigOrdens.getPassoLoss());
        this.passoGerRisco_Saldo = Double.max(0.01, ConfigOrdens.getPassoGerRisco_SaldoDesej());
        this.passoGerRisco_PtsCont = Double.max(0.01, ConfigOrdens.getPassoGerRisco_PtsCont());
        this.passoGerRisco_PrejPerm = Double.max(0.01, ConfigOrdens.getPassoGerRisco_PrejMax());
        this.passoTrStop_Acionamento = Double.max(0.01, ConfigOrdens.getPassoTrStop_Acion());
        this.passoTrStop_PtsMin = Double.max(0.01, ConfigOrdens.getPassoTrStop_PtsMin());
        this.passoTrStop_FreqAtualizacao = Double.max(0.01, ConfigOrdens.getPassoTrStop_FreqAtual());
        this.atualizaGainIgualLoss = false;
        Relatorios.gravouRelatorios = false;
    }
    
    public void detalhado(Simulacao simulacao) throws OutOfMemoryError{
        IG.textoAdd("\nDeletando arquivos temporários\n");
        ArquivoTemp.apagaArquivosTemp();
        IG.setPodeSelecionarRelatorios(false);
        ControleTempo timmer = new ControleTempo();
        String tempoDecorrido;
        
        // INICIA ANALISE
        IG.textoAdd("SIMULANDO\n");
        
        instanciaRelatorios_PrimeiraSimulacao();
        timmer.disparaTimmer();
        simulacao.simula();
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Tempo da simulação: " + tempoDecorrido);
        geraRelatoriosDetalhados();
        IG.setPodeSelecionarRelatorios(true);
    }
    
    
    public void completo(Simulacao simulacao) throws OutOfMemoryError{
        IG.textoAdd("\nDeletando arquivos temporários\n");
        ArquivoTemp.apagaArquivosTemp();
        
        IG.setPodeSelecionarRelatorios(false);
        IG.setSimulacaoCompleta();
        ControleTempo controleTempo = new ControleTempo();
        
        long numSimulacao = 1;
        resetLoop();
        //CORREÇÃO PARA NAO GERAR RELATORIO DE CANDLE NO COMPLETO
        ConfigBase.setTemRelatorioCandles(false);
        IG.textoAdd("\nSIMULANDO\n");
        progressoAtualiza();
        IG.progressoCompletoReseta();
        IG.textoAdd("=======INICIANDO ITERAÇÃO======= \n");
        instanciaRelatorios_PrimeiraSimulacao();
        for(int posMax = posMaxIni; posMax <= posMaxFin; posMax+= passoPosMax){
            for(double delta = deltaIni; delta<=deltaFin; delta = arredondaSoma(delta, passoDelta)){
                for(double limOp = limOpIni; limOp<= limOpFin; limOp = arredondaSoma(limOp, passoLimOp)){
                    for(double offSet = offsetIni; offSet<=offsetFin; offSet = arredondaSoma(offSet, passoOffset)){
                        for(double gain = gIni; gain<=gFin; gain = arredondaSoma(gain, passoGain)){
                            for(double loss = lIni; loss<=lFin; loss = arredondaSoma(loss, passoLoss)){
                                for(double saldoDesej = gerRisco_SaldoIni; saldoDesej<=gerRisco_SaldoFin; saldoDesej = arredondaSoma(saldoDesej, passoGerRisco_Saldo)){
                                    for(double ptsCont = gerRisco_PtsContIni; ptsCont<=gerRisco_PtsContFin; ptsCont = arredondaSoma(ptsCont, passoGerRisco_PtsCont)){
                                        for(double prejPerm = gerRisco_PrejPermIni; prejPerm<=gerRisco_PrejPermFin; prejPerm = arredondaSoma(prejPerm, passoGerRisco_PrejPerm)){
                                            for(double trStopPtsAcion = trStopAcionamentoIni; trStopPtsAcion<=trStopAcionamentoFin; trStopPtsAcion = arredondaSoma(trStopPtsAcion, passoTrStop_Acionamento)){
                                                for(double trStopPtsMin = trStopPtsMinIni; trStopPtsMin<=trStopPtsMinFin; trStopPtsMin = arredondaSoma(trStopPtsMin, passoTrStop_PtsMin)){
                                                    for(double trStopFreqAtualiz = trStopFreqAtualizacaoIni; trStopFreqAtualiz<=trStopFreqAtualizacaoFin; trStopFreqAtualiz = arredondaSoma(trStopFreqAtualiz, passoTrStop_FreqAtualizacao)){
                                                        controleTempo.disparaTimmer();
                                                        limpaRelatorios_Loop();
                                            
                                                        if(atualizaGainIgualLoss){
                                                            loss = gain;
                                                            lFin = loss;
                                                        }
                                                        
                                                        /*
                                                        printLoop(posMax, delta, offSet, limOp, gain, loss,
                                                                saldoDesej, ptsCont, prejPerm,
                                                                trStopPtsAcion, trStopPtsMin, trStopFreqAtualiz);
                                                        */

                                                        ConfigOrdens.setPosMaxPerm(posMax);
                                                        ConfigOrdens.setEstrategia(delta, limOp, offSet, gain, loss);
                                                        ConfigOrdens.setTrStop(temTrStop, trStopPtsAcion, trStopPtsMin, trStopFreqAtualiz);
                                                        ConfigOrdens.setGerRisco(saldoDesej, gerRisco_SaldoMinimo, ptsCont, prejPerm);
                                                        
                                                        IG.atualizaSimulacaoAtual(numSimulacao++);
                                                        simulacao.simula();
                                                        geraRelatoriosCompleto();
                                                        IG.atualizaTempoEstimado(controleTempo.calculaTempoNecessarioMedio(simulacoesTotais));
                                                        
                                                    }
                                                }
                                            }
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
//        verificaAnalisePeriodos();
//        if(verificaSugerida())
//            simulaSugerida(simulacao);
        IG.textoAdd("Fim da simulação Completa \n");
        geraExcelCompleto();
        IG.setPodeSelecionarRelatorios(true);
    }
    
    private void simulaSugerida(Simulacao simulacao){
        limpaRelatorios_Aplicado();
        simulacao.configura(true);
        simulacao.simula();
        geraRelatorioSugerido();
    }
    
    /**
     * Limpa TODOS relatórios, fixos e os temporarios.
     * Utilizado ao se executar uma simulação detalhada e ao iniciar completo
     * Utilizado para iniciar nova simulação
     */
     private void instanciaRelatorios_PrimeiraSimulacao(){
        relatorioCandleMinutoOperado.clear();
        relatorioDiario.clear();
        relatorioMensal.clear();
        relatorioAnual.clear();
        relatorioOrdensSugeridas.clear();
        relatorioOrdensExecutadas.clear();
        relatorioCompleto.clear();
        relatorioCompletoDiario.clear();
        relatorioCompletoPosPeriodo.clear();
        Candle.resetaCandleIntermediárioTemp();
    }
    
    /**
     * Limpa relatórios, durante o loop completo
     */
    private void limpaRelatorios_Loop(){
        relatorioCandleMinutoOperado.clear();
        relatorioDiario.clear();
        relatorioMensal.clear();
        relatorioAnual.clear();
        relatorioOrdensSugeridas.clear();
        relatorioOrdensExecutadas.clear();
        relatorioCompletoPosPeriodo.clear();
        Candle.resetaCandleIntermediárioTemp();
    }
    
    /**
     * Limpa relatórios para cada loop completo.
     * lista com estrategias sugeridas
     */
    public void limpaRelatorios_Aplicado(){
        relatorioCandleMinutoOperado.clear();
        relatorioDiario.clear();
        relatorioCompletoDiario.clear();
        relatorioMensal.clear();
        relatorioAnual.clear();
        relatorioOrdensExecutadas.clear();
        relatorioCompletoPosPeriodo.clear();
        //new ResumoPeriodoRelatorio().limpaRelatorios();
        //new ResumoPeriodoSugestao().limpaRelatorios();
    }
    
    private void progressoAtualiza(){
        atualizaStopIgualGain();
        double simTotalMinutos = 1;
        if(temAtualQtde)
            simTotalMinutos = simTotalMinutos*(1+(((double)posMaxFin - (double)posMaxIni)*(1/(double)passoPosMax)));
        if(temDelta)
            simTotalMinutos = simTotalMinutos*(1+((deltaFin - deltaIni)*(1/passoDelta)));
        if(temOffset)
            simTotalMinutos = simTotalMinutos*(1+((offsetFin - offsetIni)*(1/passoOffset)));
        if(temLimOp)
            simTotalMinutos = simTotalMinutos*(1+((limOpFin - limOpIni)*(1/passoLimOp)));
        if(temGain)
            simTotalMinutos = simTotalMinutos*(1+((gFin - gIni)*(1/passoGain)));
        if(temLoss && !atualizaGainIgualLoss)
            simTotalMinutos = simTotalMinutos*(1+((lFin - lIni)*(1/passoLoss)));
        if(temGerRisco_Saldo)
            simTotalMinutos = simTotalMinutos*(1+((gerRisco_SaldoFin - gerRisco_SaldoIni)*(1/passoGerRisco_Saldo)));
        if(temGerRisco_PtsCont)
            simTotalMinutos = simTotalMinutos*(1+((gerRisco_PtsContFin - gerRisco_PtsContIni)*(1/passoGerRisco_PtsCont)));
        if(temGerRisco_PrejPerm)
            simTotalMinutos = simTotalMinutos*(1+((gerRisco_PrejPermFin - gerRisco_PrejPermIni)*(1/passoGerRisco_PrejPerm)));
        if(temTrStop){
            simTotalMinutos = simTotalMinutos*(1+((trStopAcionamentoFin - trStopAcionamentoIni)*(1/passoTrStop_Acionamento)));
            simTotalMinutos = simTotalMinutos*(1+((trStopPtsMinFin - trStopPtsMinIni)*(1/passoTrStop_PtsMin)));
            simTotalMinutos = simTotalMinutos*(1+((trStopFreqAtualizacaoFin - trStopFreqAtualizacaoIni)*(1/passoTrStop_FreqAtualizacao)));
        }
        
        IG.atualizaTotalSimulacoes((int)simTotalMinutos);
        simulacoesTotais = (int)simTotalMinutos;
        simTotalMinutos = Math.round(simTotalMinutos * (double)Simulacao.getTotalDeMinutos());
        IG.progressoCompletoAtualiza((int)simTotalMinutos, 0);
        
    }
    
    /**
     * Verifica se IG solicita análise por períodos. Caso sim, faz a analise desejada
     * @return TRUE se possui alguma analise de periodos
     */
//    private boolean verificaAnalisePeriodos(){
//        if(!ConfigBase.isAnalisePeriodos())
//            return false;
//        
//        if(ConfigBase.isSugerida()){
//            new ResumoPeriodoSugestao().geraResumo(ConfigBase.getDiasAnalise(), ConfigBase.getDiasAplicado());
//            return true;
//        }
//
//        if(!ConfigBase.isAnaliseSaldo())
//            new ResumoPeriodoRelatorio().geraResumo(ConfigBase.getDiasAnalise(), ConfigBase.getDiasAplicado());
//////        else
//////            new ResumoPeriodoRelatorio().geraResumo(ConfigBase.getDiasAnalise(),ConfigBase.getSaldoDesj() , ConfigBase.getPrejMax());
//        
//        return true;
//    }
    
//    private boolean verificaSugerida(){
//        if(ConfigBase.isSugerida()){
//            new GeraEstrategias().geraListaEstrategias();
//            return true;
//        }
//        return false;
//    }
//    
//    public static boolean verificaAnalisePeriodos2(){
//        if(!ConfigBase.isAnalisePeriodos())
//            return false;
//        
//        if(ConfigBase.isSugerida()){
//            new ResumoPeriodoSugestao().geraResumo(ConfigBase.getDiasAnalise(), ConfigBase.getDiasAplicado());
//            return true;
//        }
//
//        if(!ConfigBase.isAnaliseSaldo())
//            new ResumoPeriodoRelatorio().geraResumo(ConfigBase.getDiasAnalise(), ConfigBase.getDiasAplicado());
//////        else
//////            new ResumoPeriodoRelatorio().geraResumo(ConfigBase.getDiasAnalise(),ConfigBase.getSaldoDesj() , ConfigBase.getPrejMax());
//        
//        return true;
//    }
//    
//    public static boolean verificaSugerida2(){
//        if(ConfigBase.isSugerida()){
//            new GeraEstrategias().geraListaEstrategias();
//            return true;
//        }
//        return false;
//    }
    
    private double arredondaSoma(double valor1, double valor2){
        return (Math.round((valor1 + valor2) * 100.0)) / 100.0;
    }
    
    /**
     * Mostra no console a configuração executada do loop.
     * Colocar logo após o ultimo laço do loop, antes de iniciar as simulações
     */
    private void printLoop(double qtde, double delta, double offset, double limOp, double gain, double loss,
                            double saldoDesej, double ptsCont, double prejPerm,
                            double trStopAcion, double trStopPtsMin, double trStopFreqAtualiz){
         System.out.println("Q = " + qtde + "/" + posMaxFin
                            + " | D =  " + delta + "/" + deltaFin                                    
                            + " | E =  " + offset + "/" + offsetFin 
                            + " | LimOp =  " + limOp + "/" + limOpFin 
                            + " | G =  " + gain + "/" + gFin 
                            + " | L =  " + loss + "/" + lFin
                            + " | SaldoDesej =  " + saldoDesej + "/" + gerRisco_SaldoFin
                            + " | PtsCont =  " + ptsCont + "/" + gerRisco_PtsContFin
                            + " | PrejPerm =  " + prejPerm + "/" + gerRisco_PrejPermFin
                            + " | TrStopAcion =  " + trStopAcion + "/" + trStopAcionamentoFin
                            + " | TrStopPtsMin =  " + trStopPtsMin + "/" + trStopPtsMinFin
                            + " | TrStopFreqAtualiz =  " + trStopFreqAtualiz + "/" + trStopFreqAtualizacaoFin);
    }

    private void printRelatorio(ArrayList lista){
        lista.forEach((obj) -> System.out.println(obj));
    }
    
    /**
     * para recalcular corretamente a quantidade de simulações necessárias. Dado é exibido na interface gráfica
     * e usado para a barra de progresso.
     */
    private void atualizaStopIgualGain(){
        if(IG.getNomeSimulacao().equals("Inverte Stop OCO"))
            atualizaGainIgualLoss = true;
        else
            atualizaGainIgualLoss = false;
    }
    
    /**
     * @return a primeira ordem sugerida para o primeiro dia a ser simulado
     */
    public static Ordem getPrimeiraOrdemSugerida(){
       return relatorioOrdensSugeridas.get(0).get(0); 
    }

    public static List<Resumos> getRelatorioDiario() {
        return Collections.unmodifiableList(relatorioDiario);
    }
    
    public static void gravaDia(Resumos resumo){
        if(resumo.gravado)
            return;
        
        resumo.gravado = true;
        relatorioDiario.add(resumo);
    }
    
    public static void gravaCandleOperado(Candle candle){
        relatorioCandleMinutoOperado.add(candle);
    }
    
    public static void atualizaCandleOperado(Candle candle){
        relatorioCandleMinutoOperado.set(relatorioCandleMinutoOperado.size()-1, candle);
    }
    
    public static List<List<Resumos>> getRelatorioCompletoDiario() {
        return Collections.unmodifiableList(relatorioCompletoDiario);
    }

    public static List<Resumos> getRelatorioDiasPosStop() {
        return Collections.unmodifiableList(relatorioDiasPosStop);
    }

    public static List<Resumos> getRelatorioMensal() {
        return Collections.unmodifiableList(relatorioMensal);
    }

    public static List<Resumos> getRelatorioAnual() {
        return Collections.unmodifiableList(relatorioAnual);
    }

    public static List<Resumos> getRelatorioCompleto() {
        return Collections.unmodifiableList(relatorioCompleto);
    }

    public static List<Ordem> getRelatorioOrdensExecutadas() {
        return Collections.unmodifiableList(relatorioOrdensExecutadas);
    }

    public static List<Resumos> getRelatorioCompletoPosPeriodo() {
        return Collections.unmodifiableList(relatorioCompletoPosPeriodo);
    }

    public static List<Candle> getRelatorioCandleMinutoOperado() {
        return Collections.unmodifiableList(relatorioCandleMinutoOperado);
    }

    public static List<List<Ordem>> getRelatorioOrdensSugeridas() {
        return Collections.unmodifiableList(relatorioOrdensSugeridas);
    }
    
    private void geraRelatoriosDetalhados(){
        ControleTempo timmer = new ControleTempo();
        String tempoDecorrido;
        
        IG.textoAdd("\n=====GERANDO RELATORIOS ===== \n");
        timmer.disparaTimmer();
        new ResumoMes().geraResumo();
        new ResumoAno().geraResumo();
        new ResumoSimulacao().geraResumo();
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Relatorios gerados em: " + tempoDecorrido);
        
        geraExcelDetalhado();
    }
    
    private void geraRelatoriosCompleto(){
        new ResumoMes().geraResumo();
        new ResumoAno().geraResumo();
        new ResumoSimulacao().geraResumo();
        if(IG.isTemRelCompletoDiario())
            ArquivoTemp.gravaArqTemp(relatorioDiario);
    }
    
    public static void geraExcelDetalhado(){
        ControleTempo timmer = new ControleTempo();
        String tempoDecorrido;
        
        IG.textoAdd("\n=====PREPARANDO EXCEL===== \n");
        timmer.disparaTimmer();
        
        List<AcoesExcel> listaRelatoriosExcel = new ArrayList();
        listaRelatoriosExcel.add(new ExcelConfiguracoes());
        listaRelatoriosExcel.add(new ExcelCandleResultados());
        listaRelatoriosExcel.add(new ExcelOrdensExecutadas());
        listaRelatoriosExcel.add(new ExcelDiario());
        listaRelatoriosExcel.add(new ExcelMensal());
        listaRelatoriosExcel.add(new ExcelCompleto());
        
        ExcelHandler excelHandler = new ExcelHandler(listaRelatoriosExcel);
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Excel preparado em: " + tempoDecorrido + "\n");
        
        timmer.disparaTimmer();
        excelHandler.gravaArquivo();
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Excel gravado em: " + tempoDecorrido + "\n");
        Relatorios.gravouRelatorios = true;
    }
    
    public static void geraExcelCompleto(){
        ControleTempo timmer = new ControleTempo();
        String tempoDecorrido;
        
        IG.textoAdd("\n=====PREPARANDO EXCEL===== \n");
        timmer.disparaTimmer();
        List<AcoesExcel> listaRelatoriosExcel = new ArrayList();
        listaRelatoriosExcel.add(new ExcelConfiguracoes());
        listaRelatoriosExcel.add(new ExcelCompleto());
        listaRelatoriosExcel.add(new ExcelCompletoDiario());
        
        ExcelHandler excelHandler = new ExcelHandler(listaRelatoriosExcel);
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Excel preparado em: " + tempoDecorrido + "\n");
        
        timmer.disparaTimmer();
        excelHandler.gravaArquivo();
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Excel gravado em: " + tempoDecorrido + "\n");
        Relatorios.gravouRelatorios = true;
    }

    private void geraRelatorioSugerido() {
        ControleTempo timmer = new ControleTempo();
        String tempoDecorrido;
        
        IG.textoAdd("\n=====GERANDO RELATORIOS EXCEL===== \n");
        
        List<AcoesExcel> listaRelatoriosExcel = new ArrayList();
        listaRelatoriosExcel.add(new ExcelConfiguracoes());
        listaRelatoriosExcel.add(new ExcelOrdensSugeridas());
        listaRelatoriosExcel.add(new ExcelDiario());
        listaRelatoriosExcel.add(new ExcelMensal());
        listaRelatoriosExcel.add(new ExcelOrdensExecutadas());
        
        timmer.disparaTimmer();
        ExcelHandler excel = new ExcelHandler(listaRelatoriosExcel);
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Arquivos gerados em: " + tempoDecorrido);
        
        timmer.disparaTimmer();
        excel.gravaArquivo();
        tempoDecorrido = timmer.getTempoDecorridoString();
        IG.textoAdd("Arquivos gravados em: " + tempoDecorrido);
    }
    
    public static boolean isGravouRelatorios(){
        return Relatorios.gravouRelatorios;
    }
    
}



