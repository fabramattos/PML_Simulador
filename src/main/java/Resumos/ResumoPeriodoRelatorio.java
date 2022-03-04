/**
 * Esta Classe registra os dados gerados pela simulação. Faz um resumo agrupado
 * de acordo com as simulações previamentes realizadas
 **/
package Resumos;

import Ordens.Ordem;
import InterfaceGrafica.IG;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import simulacao.Clone;

/**
 *
 * @author Felipe Mattos
 */
public class ResumoPeriodoRelatorio extends Resumos implements Comparator<ResumoPeriodoRelatorio>, Serializable {
    
    /**
     * relatorioAnalise: Lista com os dados para o primeiro período.
     * relatorioAplicado: Lista com os dados para o segundo período. 
     */
    public static List<ResumoPeriodoRelatorio> relatorioPer1, relatorioPer2;

    /**
     * relatorioCompletoPer Lista de Lista. Lista externa contem as estrategias,
     * lista interna contem as analises de período para a estrategia.
     */
    public static List<List<ResumoPeriodoRelatorio>> relatorioEstrategiasPer1, relatorioEstrategiasPer2;
    
    /**
     * lista que será atualizada. Recebe as listas com as datas para um unico dia. Reseta
     * para cada novo dia
     */
    private List<Ordem> listaEstrategia;
    
    public static List<ResumoPeriodoRelatorio> relatorioPeriodoOtimizado = new ArrayList<>();
    
    protected LocalDateTime dataFin, dataAplicada;
    protected int diasTotais;
    protected double variancia, desvPadrao, relacao;
    
    public static String sort;

    public ResumoPeriodoRelatorio() {
        super();
    }
    
    public ResumoPeriodoRelatorio(Resumos rDia) {
        super();
        super.abertura = rDia.getAbertura();
        super.data = rDia.getData();
        super.delta = rDia.getDelta();
        super.limOposto = rDia.getLimOposto();
        super.offset = rDia.getOffset();
        super.gain = rDia.getGain();
        super.loss = rDia.getLoss();
        super.gerRisco_Saldo = rDia.getGerRisco_Saldo();
        super.gerRisco_PtsCont = rDia.getGerRisco_PtsCont();
        super.gerRisco_PrejPerm = rDia.getGerRisco_PrejPerm();
        super.minima = Double.POSITIVE_INFINITY;
        super.maxima = Double.NEGATIVE_INFINITY;
    }
    
    public ResumoPeriodoRelatorio(Resumos rDia, ResumoPeriodoRelatorio p){
        super();
        super.data = rDia.getData();
        super.qtdeMax = rDia.getQtdeMax();
        super.saldoAcumulado = rDia.getSaldoAcumulado();
        super.saldoContrato = rDia.getSaldoContrato();
        super.entradas = rDia.getEntradas();
        super.saidas = rDia.getSaidas();
        super.stops = rDia.getStops();
        super.delta = p.getDelta();
        super.offset = p.getOffset();
        super.gain = p.getGain();
        super.loss = p.getLoss();
        super.gerRisco_Saldo = p.getGerRisco_Saldo();
        super.gerRisco_PtsCont = p.getGerRisco_PtsCont();
        super.gerRisco_PrejPerm = p.getGerRisco_PrejPerm();
        super.simultaneo = p.getSimultaneo();
        this.diasTotais = p.getDiasTotais();
    }
    
    /**
     * TERÁ QUE MOVER TODOS OS METODOS DE NEW ARRAY PARA A CLASSE RELATORIO, JUNTO
     * COM OS RELATORIOS
     */
    // Métodos Especiais
    public void limpaRelatorios(){
        relatorioPer1 = new ArrayList<>();
        relatorioPer2 = new ArrayList<>();
        relatorioEstrategiasPer1 = new ArrayList<>();
        relatorioEstrategiasPer2 = new ArrayList<>();
        relatorioPeriodoOtimizado = new ArrayList<>();
    }


    public LocalDateTime getDataFin() {
        return dataFin;
    }

    public void setDataFin(LocalDateTime dataFin) {
        this.dataFin = dataFin;
    }

    public LocalDateTime getDataAplicada() {
        return dataAplicada;
    }

    public void setDataAplicada(LocalDateTime dataAplicada) {
        this.dataAplicada = dataAplicada;
    }

    public int getDiasTotais() {
        return diasTotais;
    }

    public void setDiasTotais(int diasTotais) {
        this.diasTotais = diasTotais;
    }

    public double getVariancia() {
        return variancia;
    }

    public void setVariancia(double variancia) {
        this.variancia = variancia;
    }

    public double getDesvPadrao() {
        return desvPadrao;
    }

    public void setDesvPadrao(double desvPadrao) {
        this.desvPadrao = desvPadrao;
    }

    public double getRelacao() {
        return relacao;
    }

    public void setRelacao(double relacao) {
        this.relacao = relacao;
    }
    
    
    
    
     /**
     * Salva ResumoPeriodoRelatorio na lista desejada
     * @param rPer
     * @param lista 
     */
    protected void salvaPeriodo(ResumoPeriodoRelatorio rPer, List lista) {
//        ResumoPeriodoRelatorio periodo = new ResumoPeriodoRelatorio(rPer);
        ResumoPeriodoRelatorio periodo = (ResumoPeriodoRelatorio) Clone.deepClone(rPer);
        lista.add(periodo);
    }

    @Override
    public String toString() {
        return super.toString() 
                + " | Data final: " + dataFin
                + " | Delta = "   + delta
                + " | Offet = "   + offset
                + " | LimOp = "   + limOposto
                + " | Gain = "    + gain
                + " | Stop = "    + loss;
    }
    
    public void geraResumo(int diasPer1, int diasPer2){
        IG.textoAdd("Analisando períodos\n");
        relatorioEstrategiasPer1 = new ArrayList();
        relatorioEstrategiasPer2 = new ArrayList();
        
        List<List<Resumos>> listaProcessos = Relatorios.getRelatorioCompletoDiario();
        int i = 0;
        IG.progressoCompletoAtualiza(listaProcessos.size(), i);
        for(List<Resumos> listaDia : listaProcessos){
            relatorioPer1 = new ArrayList();
            relatorioPer2 = new ArrayList();
            boolean analisado = false;
            ResumoPeriodoRelatorio rPer = new ResumoPeriodoRelatorio();
            int diasUteis = 0;
            int diasOperados = 0;
            //INICIA CONTAGEM A.P.Ó.S TER FEITO O PRIMEIRO REGISTRO, RESETA AO GRAVAR LISTA1
            boolean gravado = false;
            int contador = 0;
            //CONTADOR PARA CADA VEZ QUE O PONTEIRO ANDOU 1X (DIASUTEIS TOTAIS = PER1+PER2)
            for(int j = listaDia.size()-1; j>0; j--){
                diasUteis++;
                if(gravado)
                    contador++;
                
                //FIM DA LISTA DE DADOS DIARIOS
                if(j == 2){
                    super.data = listaDia.get(j).getData();
                    if(diasPer2 == 0){
                        salvaPeriodo(rPer, relatorioPer1);
                        break;
                    }
                    if(analisado)
                        salvaPeriodo(rPer, relatorioPer1);
                    else
                        salvaPeriodo(rPer, relatorioPer2);
                    break;
                }
                /*
                if(!VerificadorIndicadores.verificaIndicadores(ord.get(j), ord.get(j-1), ord.get(j-2)))
                    continue;
                */
                if (diasOperados == 0){
                    rPer = new ResumoPeriodoRelatorio(listaDia.get(j));
                    this.dataFin = listaDia.get(j).getData();
                    gravado = true;
                }
                
                super.maxima = Double.max(super.maxima, listaDia.get(j).getMaxima());
                super.minima = Double.min(super.minLocal, listaDia.get(j).getMinima());
                super.fechamento = listaDia.get(j).getFechamento();
                super.qtdeMax = Integer.max(super.qtdeMax, listaDia.get(j).getQtdeMax());
                super.qtdeNegociada += listaDia.get(j).getQtdeNegociada();
                super.entradas += listaDia.get(j).getEntradas();
                super.saidas += listaDia.get(j).getSaidas();
                super.stops += listaDia.get(j).getStops();
                super.simultaneo += listaDia.get(j).getSimultaneo();
                super.saldoAcumulado += listaDia.get(j).getSaldo();
                super.saldoMax = Double.max(super.saldoMax, super.saldoAcumulado);
                super.prejAcum = Double.min(this.prejAcum + listaDia.get(j).getSaldo(), 0);
                super.prejAcumMax = Double.min(super.prejAcumMax, super.prejAcum);
                super.saldoContrato = super.saldoAcumulado/super.qtdeNegociada;
                if (listaDia.get(j).getSaldo() < 0)
                    super.diasNeg++;
                else
                    super.diasPos++;

                if(super.prejAcumMax == 0)
                    super.saldoPrejMax = super.saldoMax;
                else
                    super.saldoPrejMax = super.saldoMax/Math.abs(super.prejAcumMax);
                
                if(super.saldoAbertoNeg_Max == 0)
                    super.saldoDrawDown = super.saldoMax;
                else
                    super.saldoDrawDown = super.saldoMax/Math.abs(super.saldoAbertoNeg_Max);

                diasOperados++;
                this.diasTotais = diasUteis;

                //PARA ANALISE DE APENAS UM PERIODO
                if(diasPer2 == 0
                && !analisado && diasOperados == diasPer1){
                    super.data = listaDia.get(j).getData();
                    salvaPeriodo(rPer, relatorioPer1);
                    analisado = false; 
                    j = j + contador;
                    diasOperados = 0;
                    diasUteis = 0;
                    gravado = false;
                    contador = 0;
                    continue;
                }
                
                //ANALISOU PERIODO 1
                if(diasPer2 != 0
                && analisado && diasOperados == diasPer1){
                    super.data = listaDia.get(j).getData();
                    salvaPeriodo(rPer, relatorioPer1);
                    analisado = false;
                    j = j + contador;
                    diasOperados = 0;
                    diasUteis = 0;
                    gravado = false;
                    contador = 0;
                }
                
                //ANALISOU PERÍODO 2
                if(diasPer2 != 0
                && !analisado && diasOperados == diasPer2){
                    super.data = listaDia.get(j).getData();
                    salvaPeriodo(rPer, relatorioPer2);
                    analisado = true; 
                    diasOperados = 0;
                    diasUteis = 0;
                }
            }//FIM DIAS DA ESTRATEGIA
            relatorioEstrategiasPer1.add(relatorioPer1);
            //LIMPEZA DA LISTA CASO NÃO DESEJE PERIODO 2
            if(diasPer2 == 0)
                relatorioEstrategiasPer2.clear();
            else
                relatorioEstrategiasPer2.add(relatorioPer2);
            IG.progressoCompletoAtualiza(i++);
        }
        ordenaLista1Crescente();
        ordenaLista2Crescente();
    }
    
    
    /**
     * Ordena as "analises por período" por data, para cada estratégia simulada
     * Lista externa contem as estratégias, lista interna contem as analises por periodo
     */
    public void ordenaLista1Decrescente(){
        if(relatorioEstrategiasPer1.isEmpty())
            return;
        
        relatorioEstrategiasPer1.forEach((est) -> est.sort((per1, per2) -> per2.getData().compareTo(per1.getData())));
    }
    
    /**
     * Ordena as "analises por período" por data, para cada estratégia simulada
     * Lista externa contem as estratégias, lista interna contem as analises por periodo
     */
    private void ordenaLista1Crescente(){
        if(relatorioEstrategiasPer1.isEmpty())
            return;
         relatorioEstrategiasPer1.forEach((est) -> est.sort((per1, per2) -> per1.getData().compareTo(per2.getData())));
    }
    
    /**
     * Ordena as "analises por período" por data, para cada estratégia simulada
     * Lista externa contem as estratégias, lista interna contem as analises por periodo
     */
    public void ordenaLista2Decrescente(){
        if(relatorioEstrategiasPer2.isEmpty())
            return;
        
        relatorioEstrategiasPer2.forEach((est) -> est.sort((per1, per2) -> per2.getData().compareTo(per1.getData())));
    }
    
    /**
     * Ordena as "analises por período" por data, para cada estratégia simulada
     * Lista externa contem as estratégias, lista interna contem as analises por periodo
     */
    private void ordenaLista2Crescente(){
        if(relatorioEstrategiasPer2.isEmpty())
            return;
         relatorioEstrategiasPer2.forEach((est) -> est.sort((per1, per2) -> per1.getData().compareTo(per2.getData())));
    }
    
    
    /**
     * Encontra a estrategia equivalente na outra lista
     * @param est1 estrategia modelo a ser encontrada
     * @param i contador para sincronizar o período que está sendo feita a leitura
     * @return NULL se não encontrar nada (erro), ou retorna a estrategia e dados
     * do periodo 2
     */
    public ResumoPeriodoRelatorio encontraPar2(ResumoPeriodoRelatorio est1, int i){
        for(ResumoPeriodoRelatorio est2 : relatorioEstrategiasPer2.get(i)){
            if(est1.getDelta() == est2.getDelta()
            && est1.getOffset() == est2.getOffset()
            && est1.getLimOposto()== est2.getLimOposto()
            && est1.getGain() == est2.getGain()
            && est1.getLoss() == est2.getLoss()){
                return est2;
            }
        }
        return null;
    }
    
    public static Comparator<ResumoPeriodoRelatorio> comparatorData = new Comparator<ResumoPeriodoRelatorio>() {
	public int compare(ResumoPeriodoRelatorio o1, ResumoPeriodoRelatorio o2) {
            LocalDateTime valor1 = o1.getData();
            LocalDateTime valor2 = o2.getData();
            
	   return valor1.compareTo(valor2);
        }
    };
    
    @Override
    public int compare(ResumoPeriodoRelatorio o1, ResumoPeriodoRelatorio o2) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void gravaResumo(Resumos resumo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resumos criaInstancia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
