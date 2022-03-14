package com.pml.Configuracoes;

import java.util.ArrayList;
import java.util.List;
import com.pml.Ordens.Ordem;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.TipoAtivo;
import java.time.LocalDateTime;
import java.util.Collections;

public class ConfigOrdens {
    private static LadoOrdem ladoOrdem;
    private static TipoAtivo ativo;
    private static LocalDateTime entradaLimHora, entradaLimMinuto;
    private static int qtde, posMaxPerm, posMaxPermFin, passoQtde, aguardaFormacaoCandle;
    private static boolean temAtualQtde, temContMov, temDelta, temLimOp, temOffset, temGain, temLoss, temTrStop,
                            temGerRisco_Saldo, temGerRisco_SaldoMinimo, temGerRisco_PtsCont, temGerRisco_PrejPerm,
                            temGerRisco_CompensaSaldoCumulativo, temGerRisco_CompensaSaldoSimples, temPrejAcum,
                            temHorarioLim, temCompleto, reposicionaPelaAbertura;
    private static double delta, deltaIni, deltaFin, limOp, limOpIni, limOpFin, offset, offsetIni, offsetFin,
                            gain, gainIni, gainFin, loss, lossIni, lossFin, 
                            gerRisco_SaldoDesej_Original, gerRisco_SaldoDesej, gerRisco_SaldoDesejIni, gerRisco_SaldoDesejFin,
                            gerRisco_ZeraSerie,
                            gerRisco_PtsCont, gerRisco_PtsContIni, gerRisco_PtsContFin,
                            gerRisco_PrejPerm, gerRisco_PrejPermIni, gerRisco_PrejPermFin,
                            trStopAcionamento, trStopAcionamentoIni, trStopAcionamentoFin,
                            trStopPtsMin, trStopPtsMinIni, trStopPtsMinFin,
                            trStopFrequeAtualiza, trStopFrequeAtualizaIni, trStopFrequeAtualizaFin,
                            passo, passoDelta, passoOffset, passoLimOp, passoGain, passoLoss,
                            passoGerRisco_SaldoDesej, passoGerRisco_PtsCont, passoGerRisco_PrejMax,
                            passoTrStop_Acion, passoTrStop_PtsMin, passoTrStop_FreqAtual;
    
    private static String configMov, configPos, configContMov, configDelta, configOffset, configLimOp, configGain, configLoss, configTrStop, configAguardaCandle;
    
    /**
     * Lista com ordens que serão executadas todos os dias ao longo da base de dados
     */
    private static List<Ordem> listaOrdensFixas = new ArrayList();
    private static String observacoes;
    
    private static void limpaConfigOrdemComum(){
        ConfigOrdens.temAtualQtde = false;
        ConfigOrdens.temContMov = false;
        ConfigOrdens.configMov = "Lado da Ordem: Não informado";

        ConfigOrdens.temDelta = false;
        ConfigOrdens.delta = 0;
        ConfigOrdens.deltaIni = 0;
        ConfigOrdens.deltaFin = 0;
        ConfigOrdens.configDelta = "Delta: Não informado";
        
        ConfigOrdens.temOffset = false;
        ConfigOrdens.offset = 0;
        ConfigOrdens.offsetIni = 0;
        ConfigOrdens.offsetFin = 0;
        ConfigOrdens.configOffset = "Offset: Não informado";
        
        ConfigOrdens.temLimOp = false;
        ConfigOrdens.limOp = 0;
        ConfigOrdens.limOpIni = 0;
        ConfigOrdens.limOpFin = 0;
        ConfigOrdens.configLimOp = "Lim Op: Não informado";
        
        ConfigOrdens.temGain = false;
        ConfigOrdens.gain = 0;
        ConfigOrdens.gainIni = 0;
        ConfigOrdens.gainFin = 0;
        ConfigOrdens.configGain = "Gain: Não informado";
        
        ConfigOrdens.temLoss = false;
        ConfigOrdens.loss = 0;
        ConfigOrdens.lossIni = 0;
        ConfigOrdens.lossFin = 0;
        ConfigOrdens.configLoss = "Loss: Não informado";
        
        ConfigOrdens.temPrejAcum = false;
        
        ConfigOrdens.aguardaFormacaoCandle = 1;
        ConfigOrdens.configAguardaCandle = "Não informado";
        
        ConfigOrdens.observacoes = "";
        
        
        setPasso(1);
    }
    
    private static void limpaConfigGerRisco(){
        ConfigOrdens.temGerRisco_Saldo = false;
        ConfigOrdens.gerRisco_SaldoDesej = 0;
        ConfigOrdens.gerRisco_SaldoDesej_Original = 0;
        ConfigOrdens.gerRisco_SaldoDesejIni = 0;
        ConfigOrdens.gerRisco_SaldoDesejFin = 0;
        ConfigOrdens.passoGerRisco_SaldoDesej = 1;
        
        ConfigOrdens.temGerRisco_PtsCont = false;
        ConfigOrdens.gerRisco_PtsCont = 0;
        ConfigOrdens.gerRisco_PtsContIni = 0;
        ConfigOrdens.gerRisco_PtsContFin = 0;
        ConfigOrdens.passoGerRisco_PtsCont = 1;
        
        ConfigOrdens.temGerRisco_PrejPerm = false;
        ConfigOrdens.gerRisco_PrejPerm = 0;
        ConfigOrdens.gerRisco_PrejPermIni = 0;
        ConfigOrdens.gerRisco_PrejPermFin = 0;
        ConfigOrdens.passoGerRisco_PrejMax = 1;
        
        ConfigOrdens.temGerRisco_CompensaSaldoCumulativo = false;
        ConfigOrdens.temGerRisco_CompensaSaldoSimples = false;
        ConfigOrdens.reposicionaPelaAbertura = false;
        
        ConfigOrdens.temGerRisco_SaldoMinimo = false;
        ConfigOrdens.gerRisco_ZeraSerie = 0;
    }
    
    public static void limpaConfigTrStop(){
        ConfigOrdens.temTrStop = false;
        
        ConfigOrdens.trStopAcionamento = 0;
        ConfigOrdens.trStopAcionamentoIni = 0;
        ConfigOrdens.trStopAcionamentoFin = 0;
        ConfigOrdens.passoTrStop_Acion = 1;
        
        ConfigOrdens.trStopPtsMin = 0;
        ConfigOrdens.trStopPtsMinIni = 0;
        ConfigOrdens.trStopPtsMinFin = 0;
        ConfigOrdens.passoTrStop_PtsMin = 1;
        
        ConfigOrdens.trStopFrequeAtualiza = 0;
        ConfigOrdens.trStopFrequeAtualizaIni = 0;
        ConfigOrdens.trStopFrequeAtualizaFin = 0;
        ConfigOrdens.passoTrStop_FreqAtual = 1;
        
        ConfigOrdens.configTrStop = "Trailing Stop: Não informado";
    }
    
    
    /**
     * Configura booleans para todos parametros basicos)
     * Executa o limpaConfigOrdemComum();
     * @param delta
     * @param limOp
     * @param offset
     * @param gain
     * @param loss
     * @param contMov
     * @param atualQtde 
     */
    public static void setBooleans(boolean delta, boolean limOp, boolean offset, boolean gain, boolean loss,
                                    boolean contMov, boolean atualQtde){
        limpaConfigOrdemComum();
        ConfigOrdens.temDelta = delta;
        ConfigOrdens.temLimOp = limOp;
        ConfigOrdens.temOffset = offset;
        ConfigOrdens.temGain = gain;
        ConfigOrdens.temLoss = loss;
        ConfigOrdens.temAtualQtde = atualQtde;
        ConfigOrdens.temContMov = contMov;
        if(contMov)
            ConfigOrdens.configContMov = "Tendencia: Contra";
        else
            ConfigOrdens.configContMov = "Tendencia: Favor";
    }
    
    /**
     * Configura parametros para o gerenciamento de risco
     * @param gerRisco_Saldo
     * @param gerRisco_PtsCont
     * @param gerRisco_Stop 
     */
    public static void setBooleans(boolean gerRisco_Saldo, boolean gerRisco_SaldoMin, boolean gerRisco_PtsCont, boolean gerRisco_Stop){
        limpaConfigGerRisco();
        ConfigOrdens.temGerRisco_Saldo = gerRisco_Saldo;
        ConfigOrdens.temGerRisco_SaldoMinimo = gerRisco_SaldoMin;
        ConfigOrdens.temGerRisco_PtsCont = gerRisco_PtsCont;
        ConfigOrdens.temGerRisco_PrejPerm = gerRisco_Stop;
     }
    
    public static void setBase(LadoOrdem ladoOrdem, int qtde, int qtdeMax, int qtdeMaxFin){
        
        ConfigOrdens.ladoOrdem = ladoOrdem;
        if(ladoOrdem == ladoOrdem.COMPRA)
            ConfigOrdens.configMov = "Lado da Ordem: Compra";
        if(ladoOrdem == ladoOrdem.VENDA)
            ConfigOrdens.configMov = "Lado da Ordem: Venda";
        ConfigOrdens.qtde = qtde;
        ConfigOrdens.posMaxPerm = qtdeMax;
        ConfigOrdens.posMaxPermFin = qtdeMaxFin;
        
        ConfigOrdens.configPos = "Pos: Inicial = " + qtde + " | Max = " + qtdeMax;;
        
        if(temCompleto)
            ConfigOrdens.configPos+= " | Max Fin = " + qtdeMaxFin; 
    }
    
    public static void setPosMaxPerm(int posMax){
        ConfigOrdens.posMaxPerm = posMax;
    }
    
    
    /**
     * Se True, o resultado desejado de cada dia é repassado para o dia seguinte:
     * Ex.Dia 00: G.R Saldo Desej = 20 e Saldo dia = 10 -> Dia 01: G.R Saldo Desej = 30
     * Formula: Saldo Desejado base + (Saldo desejado anterior - saldo dia)
     */
    public static void setTemGerRisco_CompensaSaldo(boolean cumulativo, boolean simples){
        ConfigOrdens.temGerRisco_CompensaSaldoCumulativo = cumulativo;
        ConfigOrdens.temGerRisco_CompensaSaldoSimples = simples;
    }

    /**
     * Se True, o resultado desejado de cada dia é repassado para o dia seguinte:
     * Ex.
     * Dia 00: G.R Saldo Desej = 20 e Saldo dia = 10
     * Dia 01: G.R Saldo Desej = 30
     * 
     * Formula: Saldo Desejado base + (Saldo desejado anterior - saldo dia)
     * @return 
     */
    public static boolean isTemGerRisco_CompensaSaldoCumulativo() {
        return temGerRisco_CompensaSaldoCumulativo;
    }

    
    /**
     * Se True, o resultado desejado, caso não atingido, será passado para o dia seguinte:
     * Ex.
     * Dia 00: G.R Saldo Desej = 20 e Saldo dia = 10
     * Dia 01: G.R Saldo Desej = 10
     * 
     * Formula: Saldo Desejado base + (Saldo desejado anterior - saldo dia)
     * @return 
     */
    public static boolean isTemGerRisco_CompensaSaldoSimples() {
        return temGerRisco_CompensaSaldoSimples;
    }
    
    
    /**
     * executar setBooleans que contem booleans primeiro!
     * @param delta
     * @param lim
     * @param offset
     * @param gain
     * @param loss
     */
    public static void setEstrategia(double delta, double lim, double offset, double gain,
                                         double loss){
        if(temDelta){
            ConfigOrdens.delta = delta;
            ConfigOrdens.configDelta = "Delta = " + delta;
        }
        if(temLimOp){
            ConfigOrdens.limOp = lim;
            ConfigOrdens.configLimOp = "Lim Op = " + lim;
        }
        if(temOffset){
            ConfigOrdens.offset = offset;
            ConfigOrdens.configOffset = "Offset = " + offset;
        }
        if(temGain){
            ConfigOrdens.gain = gain;
            ConfigOrdens.configGain = "Gain = " + gain;
        }
        if(temLoss){
            ConfigOrdens.loss = loss;
            ConfigOrdens.configLoss = "Loss = " + loss;
        }
    }
    
    /**
     * 
     * @param saldo
     * @param ptsCont
     * @param prejPerm 
     */
    public static void setGerRisco(double saldo, double saldoMin, double ptsCont, double prejPerm){
        if(temGerRisco_Saldo){
            ConfigOrdens.gerRisco_SaldoDesej = saldo;
            ConfigOrdens.gerRisco_SaldoDesej_Original = saldo;
        }
        if(temGerRisco_SaldoMinimo)
            ConfigOrdens.gerRisco_ZeraSerie = saldoMin;
        if(temGerRisco_PtsCont)
            ConfigOrdens.gerRisco_PtsCont = ptsCont;
        if(temGerRisco_PrejPerm)
            ConfigOrdens.gerRisco_PrejPerm = prejPerm;
    }
    
    public static void setTrStop(boolean temTrStop, double trStopAcionamento, double trStopPtsMin, double trStopFreqAtualizacao){
        limpaConfigTrStop();
        
        if(temTrStop){
            ConfigOrdens.temTrStop = temTrStop;
            ConfigOrdens.trStopAcionamento =  trStopAcionamento;
            ConfigOrdens.trStopPtsMin =  trStopPtsMin;
            ConfigOrdens.trStopFrequeAtualiza =  trStopFreqAtualizacao;
            ConfigOrdens.configTrStop = "Trailing Stop: Acionamento = " + trStopAcionamento + 
                                        " | Alvo Mín = " + trStopPtsMin +
                                        " | Freq. de Atualização = " + trStopFreqAtualizacao + " pts";
        }
    }
    
    
    /**
     * Utilizado para o Loop e repassa valores para o Detalhado.Executar setEstrategia que contem booleans primeiro!
     *
     * @param delta1 
     * @param delta2 
     * @param lim1
     * @param lim2
     * @param offset1
     * @param offset2
     * @param gain1
     * @param gain2
     * @param loss1
     * @param loss2
     **/
    public static void setEstrategiaLoop(double delta1, double delta2, double lim1, double lim2,
                                        double offset1, double offset2,
                                        double gain1, double gain2, double loss1, double loss2){
        
        setEstrategia(delta1, lim1, offset1, gain1, loss1);

        //PARA LOOP
        if(temDelta){
            ConfigOrdens.deltaIni = delta1;
            ConfigOrdens.deltaFin = delta2;
            if(temCompleto)
                ConfigOrdens.configDelta = "Delta: Ini = " + delta1 + " | Fin = " + delta2 + " | Passo = " + passoDelta;
        }
        if(temLimOp){
            ConfigOrdens.limOpIni = lim1;
            ConfigOrdens.limOpFin = lim2;
            if(temCompleto)
                ConfigOrdens.configLimOp = "Lim Op: Ini = " + lim1 + " | Fin = " + lim2 + " | Passo = " + passoLimOp;
        }
        if(temOffset){
            ConfigOrdens.offsetIni = offset1;
            ConfigOrdens.offsetFin = offset2;
            if(temCompleto)
                ConfigOrdens.configOffset = "Offset: Ini = " + offset1 + " | Fin = " + offset2 + " | Passo = " + passoOffset;
        }
        if(temGain){
            ConfigOrdens.gainIni = gain1;
            ConfigOrdens.gainFin = gain2;
            if(temCompleto)
                ConfigOrdens.configGain = "Gain: Ini = " + gain1 + " | Fin = " + gain2 + " | Passo = " + passoGain;
        }
        if(temLoss){
            ConfigOrdens.lossIni = loss1;
            ConfigOrdens.lossFin = loss2;
            if(temCompleto)
                ConfigOrdens.configLoss = "Loss: Ini = " + loss1 + " | Fin = " + loss2 + " | Passo = " + passoLoss;
        }
    }
    
    public static void setGerRiscoLoop(double saldoIni, double saldoFin, double saldoMin, double ptsContIni, double ptsContFin,
                                        double prejPermIni, double prejPermFin){
        
        setGerRisco(saldoIni, saldoMin, ptsContIni, prejPermIni);

        //PARA LOOP
        if(temGerRisco_Saldo){
            ConfigOrdens.gerRisco_SaldoDesejIni = saldoIni;
            ConfigOrdens.gerRisco_SaldoDesejFin = saldoFin;
        }
        if(temGerRisco_PtsCont){
            ConfigOrdens.gerRisco_PtsContIni = ptsContIni;
            ConfigOrdens.gerRisco_PtsContFin = ptsContFin;
        }
        if(temGerRisco_PrejPerm){
            ConfigOrdens.gerRisco_PrejPermIni = prejPermIni;
            ConfigOrdens.gerRisco_PrejPermFin = prejPermFin;
        }
    }
    
    public static void setTrStopLoop(boolean temTrStop, double trStopAcionamentoIni, double trStopAcionamentoFin, 
                                    double trStopPtsMinIni, double trStopPtsMinFin,
                                    double trStopFreqAtualizacaoIni, double trStopFreqAtualizacaoFin){
        
        setTrStop(temTrStop, trStopAcionamentoIni, trStopPtsMinIni, trStopFreqAtualizacaoIni);

        //PARA LOOP
        if(temTrStop){
            ConfigOrdens.trStopAcionamentoIni = trStopAcionamentoIni;
            ConfigOrdens.trStopAcionamentoFin = trStopAcionamentoFin;
            ConfigOrdens.trStopPtsMinIni = trStopPtsMinIni;
            ConfigOrdens.trStopPtsMinFin = trStopPtsMinFin;
            ConfigOrdens.trStopFrequeAtualizaIni = trStopFreqAtualizacaoIni;
            ConfigOrdens.trStopFrequeAtualizaFin = trStopFreqAtualizacaoFin;
            ConfigOrdens.configTrStop = "Trailing Stop: Acionamento: Ini = " + trStopAcionamentoIni + " | Fin = " + trStopAcionamentoFin 
                                        + " | Alvo Mín: Ini = " + trStopPtsMinIni + " | Fin = " + trStopPtsMinFin
                                        + " | Freq. de Atualização: Ini = " + trStopFreqAtualizacaoIni + " | Fin = " + trStopFreqAtualizacaoFin +   " pts";
        }
    }
    
    public static void setTrStopPasso(double passoAcionamento, double passoPtsMin, double passoFreqAtual){
        //PARA LOOP
        if(temTrStop){
            ConfigOrdens.passoTrStop_Acion = passoAcionamento;
            ConfigOrdens.passoTrStop_PtsMin = passoPtsMin;
            ConfigOrdens.passoTrStop_FreqAtual = passoFreqAtual;
        }
    }


    public static void configuraCompleto(boolean completo){
        ConfigOrdens.temCompleto = completo;
    }
    
    
    public static void adicionaNaListaDeOrdensFixas_OCO(Ordem ord){
        listaOrdensFixas.add(ord);
    }

    public static void setPasso(double passo) {
        ConfigOrdens.passo = passo;
        ConfigOrdens.passoQtde = Integer.max(1, (int) Math.ceil(passo));
        ConfigOrdens.passoDelta = passo;
        ConfigOrdens.passoOffset = passo;
        ConfigOrdens.passoLimOp = passo;
        ConfigOrdens.passoGain = passo;
        ConfigOrdens.passoLoss = passo;
    }
    
    public static void setPasso(int qtde, double delta, double offset, double limOp, double gain, double loss){
        ConfigOrdens.passoQtde = Integer.max(1, (int) Math.ceil(qtde));
        ConfigOrdens.passoDelta = delta;
        ConfigOrdens.passoOffset = offset;
        ConfigOrdens.passoLimOp = limOp;
        ConfigOrdens.passoGain = gain;
        ConfigOrdens.passoLoss = loss;
    }
    
     public static void setGerRisco_Passo(double saldoDesej, double ptsCont, double prejPerm){
        if(temGerRisco_Saldo)
            ConfigOrdens.passoGerRisco_SaldoDesej = saldoDesej;
        if(temGerRisco_PtsCont)
            ConfigOrdens.passoGerRisco_PtsCont = ptsCont;
        if(temGerRisco_PrejPerm)
            ConfigOrdens.passoGerRisco_PrejMax = prejPerm;
    }

    public static TipoAtivo getAtivo() {
        return ativo;
    }

    public static void setAtivo(int i) {
        switch (i){
            case 0:
                ConfigOrdens.ativo = TipoAtivo.DOL;
                break;
                
            case 1:
                ConfigOrdens.ativo = TipoAtivo.IND;
                break;

            default:
                ConfigOrdens.ativo = TipoAtivo.ACAO;
                break;
        }
        
    }

    public static LadoOrdem getLadoOrdem() {
        return ConfigOrdens.ladoOrdem;
    }

    public static int getQtde() {
        return ConfigOrdens.qtde;
    }

    public static int getPosMaxPerm() {
        return ConfigOrdens.posMaxPerm;
    }

    public static LocalDateTime getEntradaLimHora() {
        return ConfigOrdens.entradaLimHora;
    }

    public static void setEntradaLimHora(LocalDateTime entradaLimHora) {
        ConfigOrdens.entradaLimHora = entradaLimHora;
    }

    public static LocalDateTime getEntradaLimMinuto() {
        return ConfigOrdens.entradaLimMinuto;
    }

    public static void setEntradaLimMinuto(LocalDateTime entradaLimMinuto) {
        ConfigOrdens.entradaLimMinuto = entradaLimMinuto;
    }

    public static boolean isReposicionaPelaAbertura() {
        return ConfigOrdens.reposicionaPelaAbertura;
    }

    public static void setReposicionaPelaAbertura(boolean reposicionaPelaAbertura) {
        ConfigOrdens.reposicionaPelaAbertura = reposicionaPelaAbertura;
    }

    public static int getPassoQtde() {
        return passoQtde;
    }

    public static void setPassoQtde(int passoQtde) {
        ConfigOrdens.passoQtde = passoQtde;
    }

    public static boolean isTemContMov() {
        return temContMov;
    }

    public static void setTemContMov(boolean temContMov) {
        ConfigOrdens.temContMov = temContMov;
    }

    public static boolean isTemTrStop() {
        return temTrStop;
    }

    public static void setTemTrStop(boolean temTrStop) {
        ConfigOrdens.temTrStop = temTrStop;
    }

    public static boolean isTemPrejAcum() {
        return temPrejAcum;
    }

    public static void setTemPrejAcum(boolean temPrejAcum) {
        ConfigOrdens.temPrejAcum = temPrejAcum;
    }

    public static boolean isTemHorarioLim() {
        return temHorarioLim;
    }

    public static void setTemHorarioLim(boolean temHorarioLim) {
        ConfigOrdens.temHorarioLim = temHorarioLim;
    }

    public static boolean isTemCompleto() {
        return temCompleto;
    }

    public static void setTemCompleto(boolean temCompleto) {
        ConfigOrdens.temCompleto = temCompleto;
    }

    public static double getDelta() {
        return delta;
    }

    public static void setDelta(double delta) {
        ConfigOrdens.delta = delta;
    }

    public static double getLimOp() {
        return limOp;
    }

    public static void setLimOp(double limOp) {
        ConfigOrdens.limOp = limOp;
    }

    public static double getOffset() {
        return offset;
    }

    public static void setOffset(double offset) {
        ConfigOrdens.offset = offset;
    }

    public static double getGain() {
        return gain;
    }

    public static void setGain(double gain) {
        ConfigOrdens.gain = gain;
    }

    public static double getLoss() {
        return loss;
    }

    public static void setLoss(double loss) {
        ConfigOrdens.loss = loss;
    }

    public static double getGerRisco_SaldoDesej() {
        return gerRisco_SaldoDesej;
    }

    public static void setGerRisco_SaldoDesej(double gerRisco_SaldoDesej) {
        ConfigOrdens.gerRisco_SaldoDesej = gerRisco_SaldoDesej;
    }
    
    /**
     * Reseta o valor do Ger.Risco_SaldoDesej para o valor inicial (início de série)
     * informado ao registrar os parâmetros do ger.Risco
     */
    public static void resetGerRisco_SaldoDesej() {
        ConfigOrdens.gerRisco_SaldoDesej = ConfigOrdens.gerRisco_SaldoDesej_Original;
    }

    public static double getGerRisco_SaldoDesej_Original() {
        return gerRisco_SaldoDesej_Original;
    }
    
    public static double getGerRisco_PtsCont() {
        return gerRisco_PtsCont;
    }

    public static void setGerRisco_PtsCont(double gerRisco_PtsCont) {
        ConfigOrdens.gerRisco_PtsCont = gerRisco_PtsCont;
    }

    public static double getGerRisco_PrejPerm() {
        return gerRisco_PrejPerm;
    }

    public static void setGerRisco_PrejPerm(double gerRisco_PrejPerm) {
        ConfigOrdens.gerRisco_PrejPerm = gerRisco_PrejPerm;
    }

    public static double getTrStopAcionamento() {
        return trStopAcionamento;
    }

    public static void setTrStopAcionamento(double trStopAcionamento) {
        ConfigOrdens.trStopAcionamento = trStopAcionamento;
    }

    public static double getTrStopPtsMin() {
        return trStopPtsMin;
    }

    public static void setTrStopPtsMin(double trStopPtsMin) {
        ConfigOrdens.trStopPtsMin = trStopPtsMin;
    }

    public static double getPassoDelta() {
        return passoDelta;
    }

    public static void setPassoDelta(double passoDelta) {
        ConfigOrdens.passoDelta = passoDelta;
    }

    public static double getPassoOffset() {
        return passoOffset;
    }

    public static void setPassoOffset(double passoOffset) {
        ConfigOrdens.passoOffset = passoOffset;
    }

    public static double getPassoLimOp() {
        return passoLimOp;
    }

    public static void setPassoLimOp(double passoLimOp) {
        ConfigOrdens.passoLimOp = passoLimOp;
    }

    public static double getPassoGain() {
        return passoGain;
    }

    public static void setPassoGain(double passoGain) {
        ConfigOrdens.passoGain = passoGain;
    }

    public static double getPassoLoss() {
        return passoLoss;
    }

    public static void setPassoLoss(double passoLoss) {
        ConfigOrdens.passoLoss = passoLoss;
    }

    public static double getPassoGerRisco_SaldoDesej() {
        return passoGerRisco_SaldoDesej;
    }

    public static void setPassoGerRisco_SaldoDesej(double passoGerRisco_SaldoDesej) {
        ConfigOrdens.passoGerRisco_SaldoDesej = passoGerRisco_SaldoDesej;
    }

    public static double getPassoGerRisco_PtsCont() {
        return passoGerRisco_PtsCont;
    }

    public static void setPassoGerRisco_PtsCont(double passoGerRisco_PtsCont) {
        ConfigOrdens.passoGerRisco_PtsCont = passoGerRisco_PtsCont;
    }

    public static double getPassoGerRisco_PrejMax() {
        return passoGerRisco_PrejMax;
    }

    public static void setPassoGerRisco_PrejMax(double passoGerRisco_PrejMax) {
        ConfigOrdens.passoGerRisco_PrejMax = passoGerRisco_PrejMax;
    }

    public static boolean isTemAtualQtde() {
        return temAtualQtde;
    }

    public static boolean isTemDelta() {
        return temDelta;
    }

    public static boolean isTemLimOp() {
        return temLimOp;
    }

    public static boolean isTemOffset() {
        return temOffset;
    }

    public static boolean isTemGain() {
        return temGain;
    }

    public static boolean isTemLoss() {
        return temLoss;
    }

    public static boolean isTemGerRisco_Saldo() {
        return temGerRisco_Saldo;
    }

    public static boolean isTemGerRisco_PtsCont() {
        return temGerRisco_PtsCont;
    }

    public static boolean isTemGerRisco_PrejPerm() {
        return temGerRisco_PrejPerm;
    }

    public static int getPosMaxPermFin() {
        return posMaxPermFin;
    }

    public static double getDeltaIni() {
        return deltaIni;
    }

    public static double getDeltaFin() {
        return deltaFin;
    }

    public static double getLimOpIni() {
        return limOpIni;
    }

    public static double getLimOpFin() {
        return limOpFin;
    }

    public static double getOffsetIni() {
        return offsetIni;
    }

    public static double getOffsetFin() {
        return offsetFin;
    }

    public static double getGainIni() {
        return gainIni;
    }

    public static double getGainFin() {
        return gainFin;
    }

    public static double getLossIni() {
        return lossIni;
    }

    public static double getLossFin() {
        return lossFin;
    }

    public static double getGerRisco_SaldoDesejIni() {
        return gerRisco_SaldoDesejIni;
    }

    public static double getGerRisco_SaldoDesejFin() {
        return gerRisco_SaldoDesejFin;
    }

    public static double getGerRisco_PtsContIni() {
        return gerRisco_PtsContIni;
    }

    public static double getGerRisco_PtsContFin() {
        return gerRisco_PtsContFin;
    }

    public static double getGerRisco_PrejPermIni() {
        return gerRisco_PrejPermIni;
    }

    public static double getGerRisco_PrejPermFin() {
        return gerRisco_PrejPermFin;
    }

    public static double getPasso() {
        return passo;
    }

    public static void setTemGerRisco_Saldo(boolean temGerRisco_Saldo) {
        ConfigOrdens.temGerRisco_Saldo = temGerRisco_Saldo;
    }

    public static void setTemGerRisco_PtsCont(boolean temGerRisco_PtsCont) {
        ConfigOrdens.temGerRisco_PtsCont = temGerRisco_PtsCont;
    }

    public static void setTemGerRisco_PrejPerm(boolean temGerRisco_PrejPerm) {
        ConfigOrdens.temGerRisco_PrejPerm = temGerRisco_PrejPerm;
    }

    public static double getTrStopAcionamentoIni() {
        return trStopAcionamentoIni;
    }

    public static void setTrStopAcionamentoIni(double trStopAcionamentoIni) {
        ConfigOrdens.trStopAcionamentoIni = trStopAcionamentoIni;
    }

    public static double getTrStopAcionamentoFin() {
        return trStopAcionamentoFin;
    }

    public static void setTrStopAcionamentoFin(double trStopAcionamentoFin) {
        ConfigOrdens.trStopAcionamentoFin = trStopAcionamentoFin;
    }

    public static double getTrStopPtsMinIni() {
        return trStopPtsMinIni;
    }

    public static void setTrStopPtsMinIni(double trStopPtsMinIni) {
        ConfigOrdens.trStopPtsMinIni = trStopPtsMinIni;
    }

    public static double getTrStopPtsMinFin() {
        return trStopPtsMinFin;
    }

    public static void setTrStopPtsMinFin(double trStopPtsMinFin) {
        ConfigOrdens.trStopPtsMinFin = trStopPtsMinFin;
    }

    public static double getTrStopFrequeAtualiza() {
        return trStopFrequeAtualiza;
    }

    public static void setTrStopFrequeAtualiza(double trStopFrequeAtualiza) {
        ConfigOrdens.trStopFrequeAtualiza = trStopFrequeAtualiza;
    }

    public static double getTrStopFrequeAtualizaIni() {
        return trStopFrequeAtualizaIni;
    }

    public static void setTrStopFrequeAtualizaIni(double trStopFrequeAtualizaIni) {
        ConfigOrdens.trStopFrequeAtualizaIni = trStopFrequeAtualizaIni;
    }

    public static double getTrStopFrequeAtualizaFin() {
        return trStopFrequeAtualizaFin;
    }

    public static void setTrStopFrequeAtualizaFin(double trStopFrequeAtualizaFin) {
        ConfigOrdens.trStopFrequeAtualizaFin = trStopFrequeAtualizaFin;
    }

    public static double getPassoTrStop_Acion() {
        return passoTrStop_Acion;
    }

    public static void setPassoTrStop_Acion(double passoTrStop_Acion) {
        ConfigOrdens.passoTrStop_Acion = passoTrStop_Acion;
    }

    public static double getPassoTrStop_PtsMin() {
        return passoTrStop_PtsMin;
    }

    public static void setPassoTrStop_PtsMin(double passoTrStop_PtsMin) {
        ConfigOrdens.passoTrStop_PtsMin = passoTrStop_PtsMin;
    }

    public static double getPassoTrStop_FreqAtual() {
        return passoTrStop_FreqAtual;
    }

    public static void setPassoTrStop_FreqAtual(double passoTrStop_FreqAtual) {
        ConfigOrdens.passoTrStop_FreqAtual = passoTrStop_FreqAtual;
    }
    
    /**
     * Utilizado para enviar uma STRING com a configuração utilizada
     * @param numeroInterface numero na interface com os checkbox
     * @return String
     */
    public static String printGerRisco(int numeroInterface){
        String nome;
        
        boolean temGerRisco;
        
        Double valor, valorIni, valorFin, passoGerRisco;
        
        switch (numeroInterface){
            
            case 1:
                nome = "Saldo Desej";
                temGerRisco = temGerRisco_Saldo;
                valor = gerRisco_SaldoDesej_Original;
                valorIni = gerRisco_SaldoDesejIni;
                valorFin = gerRisco_SaldoDesejFin;
                passoGerRisco = passoGerRisco_SaldoDesej;
                break;
                
            case 2:
                nome = "Zera Série";
                valor = gerRisco_ZeraSerie;
                if(temGerRisco_SaldoMinimo)
                    return nome + " = " + valor;
                else
                    return nome + ": Não informado";
            
            case 3:
                nome = "Pts/Contrato";
                temGerRisco = temGerRisco_PtsCont;
                valor = gerRisco_PtsCont;
                valorIni = gerRisco_PtsContIni;
                valorFin = gerRisco_PtsContFin;
                passoGerRisco = passoGerRisco_PtsCont;
                break;
            
            case 4:
                nome = "Prej Perm";
                temGerRisco = temGerRisco_PrejPerm;
                valor = gerRisco_PrejPerm;
                valorIni = gerRisco_PrejPermIni;
                valorFin = gerRisco_PrejPermFin;
                passoGerRisco = passoGerRisco_PrejMax;
                break;
            
            case 5:
                if(temGerRisco_CompensaSaldoCumulativo)
                    return "Compensa saldo = Cumulativo";
                if(temGerRisco_CompensaSaldoSimples)
                    return "Compensa saldo = Simples";
                return "Compensa saldo = Não";
                
            default:
                if(reposicionaPelaAbertura)
                    return "Reposiciona pela abertura = SIM";
                return "Reposiciona pela abertura = NÃO";
        }
        
        if(!temGerRisco)
            return nome + ": Não informado";
        
        if(ConfigOrdens.temCompleto)
            return  nome + " = " + valorIni + " | Fin = " + valorFin + " | Passo = " + passoGerRisco;
        
        return nome + " = " + valor;
    }
    
    public static String getConfigMov() {
        return configMov;
    }

    public static String getConfigPos() {
        return configPos;
    }

    public static String getConfigContMov() {
        return configContMov;
    }

    public static String getConfigDelta() {
        return configDelta;
    }

    public static String getConfigOffset() {
        return configOffset;
    }

    public static String getConfigLimOp() {
        return configLimOp;
    }

    public static String getConfigGain() {
        return configGain;
    }

    public static String getConfigLoss() {
        return configLoss;
    }

    public static String getConfigTrStop() {
        return configTrStop;
    }

    public static int getAguardaFormacaoCandle() {
        return aguardaFormacaoCandle;
    }

    public static void setAguardaFormacaoCandle(int aguardaFormacaoCandle) {
        ConfigOrdens.aguardaFormacaoCandle = aguardaFormacaoCandle;
        ConfigOrdens.configAguardaCandle = "Base de Tempo: " + String.valueOf(aguardaFormacaoCandle) + " min";
    }

    public static String getConfigAguardaCandle() {
        return configAguardaCandle;
    }
    
    public static List<Ordem> getListaOrdensFixas(){
        List<Ordem> lista = Collections.unmodifiableList(listaOrdensFixas);
        return lista;
    }
    
    public static void apagaListaOrdensFixas(){
        listaOrdensFixas.clear();
    }

    public static double getGerRisco_ZeraSerie() {
        return gerRisco_ZeraSerie;
    }

    public static boolean isTemGerRisco_SaldoMinimo() {
        return temGerRisco_SaldoMinimo;
    }

    public static void setTemGerRisco_SaldoMinimo(boolean temGerRisco_SaldoMinimo) {
        ConfigOrdens.temGerRisco_SaldoMinimo = temGerRisco_SaldoMinimo;
    }

    public static String getObservacoes() {
        return observacoes;
    }

    /**
     * utilizar separador do sistema para registrar quebra de linha
     * @param observacoes 
     */
    public static void setObservacoes(String observacoes) {
        ConfigOrdens.observacoes = observacoes;
    }
}
