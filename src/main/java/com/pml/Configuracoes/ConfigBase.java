package com.pml.Configuracoes;

public class ConfigBase {
    private static String nomeSimulacao;
    
    private static boolean temDataIni, temDataFin, temHorarioIni, temHorarioFin, temHorarioLimEntrada, sugerida,
                            temRelatorioCandles;
    private static int diaIni, mesIni, anoIni, diaFin, mesFin, anoFin,
                        horaInicial, horaFinal, minutoInicial, minutoFinal, limEntradaHora, limEntradaMinuto;
    private static float passoMin;
    
    public static void configuraPeriodoInicialBancoDeDados(boolean data, int dia, int mes, int ano){
        ConfigBase.temDataIni = data;
        ConfigBase.diaIni = dia;
        ConfigBase.mesIni = mes;
        ConfigBase.anoIni = ano;
    }
    
    public static void configuraPeriodoFinalBancoDeDados(boolean data, int dia, int mes, int ano){
        ConfigBase.temDataFin = data;
        ConfigBase.diaFin = dia;
        ConfigBase.mesFin = mes;
        ConfigBase.anoFin = ano;
    }

    public static void configuraHorarioFinal(boolean temLim, int hora, int min){
        ConfigBase.temHorarioFin = temLim;
        ConfigBase.horaFinal = hora;
        ConfigBase.minutoFinal = min;
    }
    
    public static void configuraHorarioInicial(boolean temLim, int hora, int min){
        ConfigBase.temHorarioIni = temLim;
        ConfigBase.horaInicial = hora;
        ConfigBase.minutoInicial = min;
    }
    
    public static void configuraHorarioLimiteEntrada(boolean temLim, int hora, int min){
        ConfigBase.temHorarioLimEntrada = temLim;
        ConfigBase.limEntradaHora = hora;
        ConfigBase.limEntradaMinuto = min;
    }
    
    public static boolean isTemRelatorioCandles() {
        return temRelatorioCandles;
    }

    public static void setTemRelatorioCandles(boolean temRelatorioCandles) {
        ConfigBase.temRelatorioCandles = temRelatorioCandles;
    }

    public static boolean isTemHorarioIni() {
        return temHorarioIni;
    }

    public static void setTemHorarioIni(boolean temHorarioIni) {
        ConfigBase.temHorarioIni = temHorarioIni;
    }

    public static int getHoraInicial() {
        return horaInicial;
    }

    public static void setHoraInicial(int horaInicial) {
        ConfigBase.horaInicial = horaInicial;
    }

    public static int getMinutoInicial() {
        return minutoInicial;
    }

    public static void setMinutoInicial(int minutoInicial) {
        ConfigBase.minutoInicial = minutoInicial;
    }

    public static boolean isTemHorarioFin() {
        return temHorarioFin;
    }

    public static int getHoraFinal() {
        return horaFinal;
    }

    public static int getMinutoFinal() {
        return minutoFinal;
    }
    
    public static float getPassoMin() {
        return passoMin;
    }

    public static void setPassoMin(int ativo) {
        switch (ativo){
            default: //DOL
                ConfigBase.passoMin = 0.5f;
                break;
                
            case 1: //IND
                ConfigBase.passoMin = 0.005f;
                break;
                
            case 2: //AÇÕES
                ConfigBase.passoMin = 0.01f;
                break;
        }
    }

    public static boolean isSugerida() {
        return sugerida;
    }

    public static void setSugerida(boolean sugerida) {
        ConfigBase.sugerida = sugerida;
    }

    public static boolean isTemDataIni() {
        return temDataIni;
    }

    public static void setTemDataIni(boolean temDataIni) {
        ConfigBase.temDataIni = temDataIni;
    }

    public static boolean isTemDataFin() {
        return temDataFin;
    }

    public static void setTemDataFin(boolean temDataFin) {
        ConfigBase.temDataFin = temDataFin;
    }

    public static int getDiaIni() {
        return diaIni;
    }

    public static void setDiaIni(int diaIni) {
        ConfigBase.diaIni = diaIni;
    }

    public static int getMesIni() {
        return mesIni;
    }

    public static void setMesIni(int mesIni) {
        ConfigBase.mesIni = mesIni;
    }

    public static int getAnoIni() {
        return anoIni;
    }

    public static void setAnoIni(int anoIni) {
        ConfigBase.anoIni = anoIni;
    }

    public static int getDiaFin() {
        return diaFin;
    }

    public static void setDiaFin(int diaFin) {
        ConfigBase.diaFin = diaFin;
    }

    public static int getMesFin() {
        return mesFin;
    }

    public static void setMesFin(int mesFin) {
        ConfigBase.mesFin = mesFin;
    }

    public static int getAnoFin() {
        return anoFin;
    }

    public static void setAnoFin(int anoFin) {
        ConfigBase.anoFin = anoFin;
    }

    public static boolean isTemHorarioLimEntrada() {
        return temHorarioLimEntrada;
    }

    public static int getLimEntradaHora() {
        return limEntradaHora;
    }

    public static int getLimEntradaMinuto() {
        return limEntradaMinuto;
    }
    
    /**
     * Utilizado para enviar uma STRING com a configuração utilizada
     * @return String com o horario configurado na interface
     */
    public static String printHorarioInicial(){
        if(temHorarioIni)
            return "Horário Inicial: " + horaInicial + ":" + minutoInicial;
        return "Horário Inicial: Não informado";
    }
    
    /**
     * Utilizado para enviar uma STRING com a configuração utilizada
     * @return String com o horario configurado na interface
     */
    public static String printHorarioFinal(){
        if(temHorarioFin)
            return "Horário Final: " + horaFinal + ":" + minutoFinal;
        return "Horário Final: Não informado";
    }
    
    /**
     * Utilizado para enviar uma STRING com a configuração utilizada
     * @return String com o horario configurado na interface
     */
    public static String printHorarioLimiteEntrada(){
        if(temHorarioLimEntrada)
            return "Horário Limite Entrada: " + limEntradaHora + ":" + limEntradaMinuto;
        return "Horário Limite Entrada: Não informado";
    }
    
    public static void setNomeSimulacao(String nome){
        nomeSimulacao = nome;
    }
    
    public static String printNomeSimulacao(){
        return nomeSimulacao;
    }
    
}
