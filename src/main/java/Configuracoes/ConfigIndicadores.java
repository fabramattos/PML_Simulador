package Configuracoes;

public class ConfigIndicadores {
    private static boolean
    temGen = false,
    temDirInd = false,
    temAbeFech = false,
    temAbeInd = false,        
    temFechAbe = false,
    temVolatilidade = false;
    
    private static String 
    genOpe = "",
    dirOpe = "",
    abeFechOpe = "",
    fechAbeOpe = "",
    abeIndOpe = "",
    volatilidadeOpe = "";
    
    private static double
    genVal1 = 0, genVal2 = 0,
    dirVal1 = 0, dirVal2 = 0,
    fechAbeVal1 = 0, fechAbeVal2 = 0,
    abeFechVal1 = 0, abeFechVal2 = 0,
    abeAbeIndVal1 = 0, abeAbeIndVal2 = 0,
    volatilidadeVal1 = 0, volatilidadeVal2 = 0;
    
    private static int
    volatilidadePeriodos = 0;
    
    
    //MÉTODOS ESPECIAIS

    public static void setVolatilidaden(boolean temIndicador, int periodosAnalise, String indOpe, double indVal1, double indVal2){
        ConfigIndicadores.temVolatilidade = temIndicador;
        ConfigIndicadores.volatilidadePeriodos = periodosAnalise;
        ConfigIndicadores.volatilidadeOpe = indOpe;
        ConfigIndicadores.volatilidadeVal1 = indVal1;
        ConfigIndicadores.volatilidadeVal2 = indVal2;
    }
    
    public static void setGen(boolean temIndicador, String indOpe, double indVal1, double indVal2){
        ConfigIndicadores.temGen = temIndicador;
        ConfigIndicadores.genOpe = indOpe;
        ConfigIndicadores.genVal1 = indVal1;
        ConfigIndicadores.genVal2 = indVal2;
    }

    public static void setFechAbe(boolean temIndicador, String indOpe, double indVal1, double indVal2){
        ConfigIndicadores.temFechAbe = temIndicador;
        ConfigIndicadores.fechAbeOpe = indOpe;
        ConfigIndicadores.fechAbeVal1 = indVal1;
        ConfigIndicadores.fechAbeVal2 = indVal2;
    }
    
    public static void setDirInd(boolean temDirInd, String indOpe, double indVal1, double indVal2){
        ConfigIndicadores.temDirInd = temDirInd;
        ConfigIndicadores.dirOpe = indOpe;
        ConfigIndicadores.dirVal1 = indVal1;
        ConfigIndicadores.dirVal2 = indVal2;
    }
    
    public static void setAbeFech(boolean temAbeFech, String indOpe, double indVal1, double indVal2){
        ConfigIndicadores.temAbeFech = temAbeFech;
        ConfigIndicadores.abeFechOpe = indOpe;
        ConfigIndicadores.abeFechVal1 = indVal1;
        ConfigIndicadores.abeFechVal2 = indVal2;
    }
    
    public static void setAbeInd(boolean temAbeInd, String indOpe, double indVal1, double indVal2){
        ConfigIndicadores.temAbeInd = temAbeInd;
        ConfigIndicadores.abeIndOpe = indOpe;
        ConfigIndicadores.abeAbeIndVal1 = indVal1;
        ConfigIndicadores.abeAbeIndVal2 = indVal2;
    }

    public static boolean isTemGen() {
        return temGen;
    }

    public static void setTemGen(boolean temGen) {
        ConfigIndicadores.temGen = temGen;
    }

    public static boolean isTemDirInd() {
        return temDirInd;
    }

    public static void setTemDirInd(boolean temDirInd) {
        ConfigIndicadores.temDirInd = temDirInd;
    }

    public static boolean isTemAbeFech() {
        return temAbeFech;
    }

    public static void setTemAbeFech(boolean temAbeFech) {
        ConfigIndicadores.temAbeFech = temAbeFech;
    }

    public static boolean isTemFechAbe() {
        return temFechAbe;
    }

    public static void setTemFechAbe(boolean temFechAbe) {
        ConfigIndicadores.temFechAbe = temFechAbe;
    }

    public static boolean isTemVolatilidade() {
        return temVolatilidade;
    }

    public static void setTemVolatilidade(boolean temVolatilidade) {
        ConfigIndicadores.temVolatilidade = temVolatilidade;
    }

    public static String getGenOpe() {
        return genOpe;
    }

    public static void setGenOpe(String genOpe) {
        ConfigIndicadores.genOpe = genOpe;
    }

    public static String getDirOpe() {
        return dirOpe;
    }

    public static void setDirOpe(String dirOpe) {
        ConfigIndicadores.dirOpe = dirOpe;
    }

    public static String getAbeFechOpe() {
        return abeFechOpe;
    }

    public static void setAbeFechOpe(String abeFechOpe) {
        ConfigIndicadores.abeFechOpe = abeFechOpe;
    }

    public static String getFechAbeOpe() {
        return fechAbeOpe;
    }

    public static void setFechAbeOpe(String fechAbeOpe) {
        ConfigIndicadores.fechAbeOpe = fechAbeOpe;
    }

    public static String getVolatilidadeOpe() {
        return volatilidadeOpe;
    }

    public static void setVolatilidadeOpe(String volatilidadeOpe) {
        ConfigIndicadores.volatilidadeOpe = volatilidadeOpe;
    }

    public static double getGenVal1() {
        return genVal1;
    }

    public static void setGenVal1(double genVal1) {
        ConfigIndicadores.genVal1 = genVal1;
    }

    public static double getGenVal2() {
        return genVal2;
    }

    public static void setGenVal2(double genVal2) {
        ConfigIndicadores.genVal2 = genVal2;
    }

    public static double getDirVal1() {
        return dirVal1;
    }

    public static void setDirVal1(double dirVal1) {
        ConfigIndicadores.dirVal1 = dirVal1;
    }

    public static double getDirVal2() {
        return dirVal2;
    }

    public static void setDirVal2(double dirVal2) {
        ConfigIndicadores.dirVal2 = dirVal2;
    }

    public static double getFechAbeVal1() {
        return fechAbeVal1;
    }

    public static void setFechAbeVal1(double fechAbeVal1) {
        ConfigIndicadores.fechAbeVal1 = fechAbeVal1;
    }

    public static double getFechAbeVal2() {
        return fechAbeVal2;
    }

    public static void setFechAbeVal2(double fechAbeVal2) {
        ConfigIndicadores.fechAbeVal2 = fechAbeVal2;
    }

    public static double getAbeFechVal1() {
        return abeFechVal1;
    }

    public static void setAbeFechVal1(double abeFechVal1) {
        ConfigIndicadores.abeFechVal1 = abeFechVal1;
    }

    public static double getAbeFechVal2() {
        return abeFechVal2;
    }

    public static void setAbeFechVal2(double abeFechVal2) {
        ConfigIndicadores.abeFechVal2 = abeFechVal2;
    }

    public static double getVolatilidadeVal1() {
        return volatilidadeVal1;
    }

    public static void setVolatilidadeVal1(double volatilidadeVal1) {
        ConfigIndicadores.volatilidadeVal1 = volatilidadeVal1;
    }

    public static double getVolatilidadeVal2() {
        return volatilidadeVal2;
    }

    public static void setVolatilidadeVal2(double volatilidadeVal2) {
        ConfigIndicadores.volatilidadeVal2 = volatilidadeVal2;
    }

    public static int getVolatilidadePeriodos() {
        return volatilidadePeriodos;
    }

    public static void setVolatilidadePeriodos(int volatilidadePeriodos) {
        ConfigIndicadores.volatilidadePeriodos = volatilidadePeriodos;
    }

    public static boolean isTemAbeInd() {
        return temAbeInd;
    }

    public static String getAbeIndOpe() {
        return abeIndOpe;
    }

    public static double getAbeAbeIndVal1() {
        return abeAbeIndVal1;
    }

    public static double getAbeAbeIndVal2() {
        return abeAbeIndVal2;
    }
    
    
    /**
     * Utilizado para enviar uma STRING com a configuração utilizada
     * @param numeroIndicador é o numero index do indicador desejado
     * @return String
     */
    public static String printIndicadorDiario(int numeroIndicador){
        boolean temIndicador;
        String operacao, nomeIndicador;
        double valor1, valor2;
        
        switch (numeroIndicador){
            case 1:
                nomeIndicador = "Indicador[D-1]";
                temIndicador = temGen;
                operacao = genOpe;
                valor1 = genVal1; valor2 = genVal2;
                break;
                
            case 2:
                nomeIndicador = "Abe[D-1] --> Fech[D-1]";
                temIndicador = temFechAbe;
                operacao = fechAbeOpe;
                valor1 = fechAbeVal1; valor2 = fechAbeVal2;
                break;
                
            case 3:
                nomeIndicador = "Indicador[D-2] --> Indicador[D-1]";
                temIndicador = temDirInd;
                operacao = dirOpe;
                valor1 = dirVal1; valor2 = dirVal2;
                break;
                
            case 4:
                nomeIndicador = "Fech[D-1] --> Abe[D-0]";
                temIndicador = temAbeFech;
                operacao = abeFechOpe;
                valor1 = abeFechVal1; valor2 = abeFechVal2;
                break;
            
            default:
                nomeIndicador = "Indicador[D-1] --> Abe[D-0]";
                temIndicador = temAbeInd;
                operacao = abeIndOpe;
                valor1 = abeAbeIndVal1; valor2 = abeAbeIndVal2;
                break;
        }
        
        if(!temIndicador)
            return nomeIndicador + ": Não informado.";
        
        if(operacao.equals("contido"))
            return  nomeIndicador + " [" + valor1 + " ; " + valor2 + "]";
        
        return nomeIndicador + " " + operacao + " " + valor1;
    }
    
}

