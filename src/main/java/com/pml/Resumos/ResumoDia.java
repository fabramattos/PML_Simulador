package com.pml.Resumos;

import com.pml.Ordens.Ordem;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Controladores.ControleOrdens;
import com.pml.Controladores.ControleTempo;
import com.pml.Controladores.GerenciamentoDeRisco;
import com.pml.InterfaceGrafica.IG;
import com.pml.Ordens.OrdemGerRisco;
import com.pml.Resumos.ResumoDia.SortDistancia;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import com.pml.simulacao.Candle;
import com.pml.simulacao.Clone;

public class ResumoDia extends Resumos{
    
    private static int ultimaPos;
    private double ultimoValorExecutado;
    private boolean podeOperar_indicadores;
    
    
    /**
     * Lista gerada para o dia sendo simulado.
     */
    private static List<Ordem> listaOrdensDia = new ArrayList();
    
    /**
     * Instancia um novo dia (o PRIMEIRO dia da simulação)
     * Reseta a lista de ordens para o dia 'OrdemSimples.listaOrdensDia.clear()'
     * @param candleDiario lista de candle diario
     */
    public ResumoDia(Candle candleDiario) {
        super();
        super.data = candleDiario.getData();
        super.abertura = candleDiario.getAbertura();
        super.maxima = candleDiario.getMaxima();
        super.minima = candleDiario.getMinima();
        super.fechamento = candleDiario.getFechamento();
        super.indicadorExtra = candleDiario.getIndicadorExtra();
        super.media = (super.maxima + super.minima)/2;
        super.podeReposicionarPelaAbertura = false;
        this.ultimoValorExecutado = candleDiario.getAbertura();
        listaOrdensDia.clear();
        ControleOrdens.ordenouListaDeOrdens = false;
        IG.progressoSimulacaoReseta();
        criaListaOrdensDia();
        ControleTempo.setPodeOperar(false);
        Candle.resetaCandleIntermediárioTemp();
    }
    
    /**
     * Instancia novo dia e passa os valores do dia anterior
     * Reseta a lista de ordens para o dia 'OrdemSimples.listaOrdensDia.clear()'
     * @param proximoCandleDiario lista com os candles diarios
     * @param rDiaEncerrando resumo de como foi o dia anterior
     * @param montaPos se FALSE, reinicia aberturaSerie, valMedio, SaldoSerie, etc
     */
    public ResumoDia(Candle proximoCandleDiario, ResumoDia rDiaEncerrando, boolean montaPos) {
        super();
        super.data = proximoCandleDiario.getData();
        super.abertura = proximoCandleDiario.getAbertura();
        super.maxima = proximoCandleDiario.getMaxima();
        super.minima = proximoCandleDiario.getMinima();
        super.fechamento = proximoCandleDiario.getFechamento();
        super.indicadorExtra = proximoCandleDiario.getIndicadorExtra();
        super.media = (super.maxima + super.minima)/2;

        super.pos = rDiaEncerrando.pos;
        super.posMax = rDiaEncerrando.posMax;
        super.posValTotal+= rDiaEncerrando.posValTotal;
        super.posValMed = rDiaEncerrando.posValMed;
        super.saldoAcumulado = rDiaEncerrando.saldoAcumulado;
        super.prejAcum = rDiaEncerrando.prejAcum;
        ControleTempo.setPodeOperar(false);
        
        if(montaPos){
            if(!rDiaEncerrando.gerRisco){
                ControleTempo.setPodeOperar(true);
                super.atualizouReferencia = rDiaEncerrando.atualizouReferencia;
                super.aberturaSerie = rDiaEncerrando.aberturaSerie;
            }else{
                this.ultimoValorExecutado = proximoCandleDiario.getAbertura();
                listaOrdensDia.clear();
                ControleOrdens.ordenouListaDeOrdens = false;
            }
        }else{
            this.ultimoValorExecutado = proximoCandleDiario.getAbertura();
            listaOrdensDia.clear();
            ControleOrdens.ordenouListaDeOrdens = false;
            criaListaOrdensDia();
        }
        
        atualizaSaldoDesejado(rDiaEncerrando);
        Candle.resetaCandleIntermediárioTemp();
    }

    
    public void adicionaOrdemNaLista(Ordem ord){
        Ordem estClone = (Ordem) Clone.deepClone(ord);
        if(ord != null)
            listaOrdensDia.add(estClone);
    }
    
    /**
     * gerenciamento de risco tem prioridade na execução, logo, tem que ser adicionado em primeiro lugar
     * e o SORT vai deixa-lo sem alteração caso as distancias sejam as mesmas.
     * @param ord 
     */
    public void adicionaOrdemNaLista_PrimeiraPosicao(Ordem ord){
        Ordem estClone = (Ordem) Clone.deepClone(ord);
        if(ord != null)
            listaOrdensDia.add(0, estClone);
    }
    
    public double getUltimoValorExecutado(){
        return ultimoValorExecutado;
    }
    
    /**
     * Executa a compra e altera os resultados do dia
     * @param qtde
     * @param valor 
     */
    public void executaCompra(int qtde, double valor){
        double valorRealizado = 0;
        int posAnterior = pos;
        
        pos+= qtde;
        qtdeMax = Integer.max(qtdeMax, qtde);
        // MONTANDO A POS
        if(posAnterior >= 0){
            entradas+= qtde;
            posValTotal+= valor*qtde;
        //DESMONTANDO A POS
        }else{
            valorRealizado = posValMed - valor;
            //POS NÃO INVERTEU
            if (pos<=0){
                saidas+= qtde;
                qtdeNegociada+= qtde;
                qtdeNegociadaSerie+= qtde;
                valorRealizado*= qtde;
                posValTotal -= posValMed * qtde;
            // INVERTENDO POS
            }else{
                entradas+= Math.abs(pos);
                saidas+= Math.abs(posAnterior);
                qtdeNegociada+= Math.abs(posAnterior);
                qtdeNegociadaSerie+= Math.abs(posAnterior);
                valorRealizado*= Math.abs(posAnterior);
                posValTotal = valor*Math.abs(pos);
            }
        }  
        atualizaValoresDiarios(valorRealizado);
        ultimoValorExecutado = valor;
    }
    
    
    /**
     * Executa venda e altera os resultados do dia
     * @param qtde
     * @param valor
     */
    public void executaVenda(int qtde, double valor){
        double valorRealizado = 0;
        int posAnterior = pos;
        pos-= qtde;
        qtdeMax = Integer.max(qtdeMax, qtde);

        // AUMENTANDO A POS
        if(posAnterior<=0){
            entradas+=qtde;
            posValTotal+= valor*qtde;
        //DIMINUINDO A POS
        }else{
            valorRealizado = valor - posValMed;
            //POS NAO INVERTEU
            if (pos>=0){
                saidas+= qtde;
                qtdeNegociada+= qtde;
                qtdeNegociadaSerie+= qtde;
                valorRealizado*= qtde;
                posValTotal-= posValMed*qtde;
            // INVERTENDO POS
            }else{
                entradas+= Math.abs(pos);
                saidas+= Math.abs(posAnterior);
                qtdeNegociada+= Math.abs(posAnterior);
                qtdeNegociadaSerie+= Math.abs(posAnterior);
                valorRealizado*= Math.abs(posAnterior);
                posValTotal = valor*Math.abs(pos);
            }
        }
        atualizaValoresDiarios(valorRealizado);
        ultimoValorExecutado = valor;
    }
    
    /**
     * Saída Stop para ordem (OCO).Depende dos atributos comprado, vendido
     * @param qtde
     * @param valor
     */
    public void executaCompraStop(int qtde, double valor){
        stops+= qtde;
        executaCompra(qtde, valor);
    }
    
    /**
     * Saída Stop para ordem (OCO).Depende dos atributos comprado, vendido
     * @param qtde
     * @param valor
     */
    public void executaVendaStop(int qtde, double valor){
        stops+= qtde;
        executaVenda(qtde, valor);
    }
    
    /**
     * Saída Stop para ordem (OCO).Depende dos atributos comprado, vendido
     * @param qtde
     * @param valor
     */
    public void executaCompraTrStop(int qtde, double valor){
        tStops+= qtde;
        executaCompra(qtde, valor);
    }
    
    /**
     * Saída Stop para ordem (OCO).Depende dos atributos comprado, vendido
     * @param qtde
     * @param valor
     */
    public void executaVendaTrStop(int qtde, double valor){
        tStops+= qtde;
        executaVenda(qtde, valor);
        
    }
    
    /**
     * Verifica se a referencia para a série e para as operações do dia foi atualizada
     * Ex. Abertura do dia, abertura após um horario inicial
     * @param candle Candle atual a ser utilizado.
     * @param rDia 
     * @return  TRUE se o candle utilizado for o responsável pela atualização.
     */
    public boolean verificaReferencia(Candle candle, ResumoDia rDia){
        if(rDia.isAtualizouReferencia())
            return false;
        
        if(new ControleTempo().verificaHorarioInicial(candle)){
            rDia.setAtualizouReferencia(true);
            rDia.setAberturaSerie(candle.getAbertura());
            return true;
        }
        return false;
    }
    
    
    /**
     * Para simulações onde os extremos precisam ser gerados após um certo intervalo
     * de tempo, simulando operações em candle de 5min, 10min, etc.
     * @param candle o candle atual sendo lido
     * @param multiplo intervalo usado como base para gravar os extremos
     * @return 
     */
    public boolean atualizaExtremosLocais(Candle candle, int multiplo){
        if (multiplo > 1 
        && !new ControleTempo().verificaMinutoMultiplo(candle, multiplo - 1))
            return false;
            
        super.maxLocal = Double.max(maxLocal, candle.getMaxima());
        super.minLocal = Double.min(minLocal, candle.getMinima());
        return true;
    }
    
    /**
     * Atualiza os extremos locais.
     * Atualiza variável SaldoAbertoNeg.
     * @param candle candle do minuto a ser utilizado
     */
    public void atualizaDia(Candle candle){
        super.maxLocal = Double.max(maxLocal, candle.getMaxima());
        super.minLocal = Double.min(minLocal, candle.getMinima());
        
        if(pos>0)
            saldoAbertoNeg = Double.min(saldoAbertoNeg, (candle.getMinima() - posValMed) * Math.abs(pos));
        if(pos<0)
            saldoAbertoNeg = Double.min(saldoAbertoNeg, (posValMed - candle.getMaxima()) * Math.abs(pos));
    }

    /**
     * retorna a ultima posição do dia anterior (pos antes de sair pelo fechamento)
     * @return 
     */
    public static int getUltimaPos() {
        return ultimaPos;
    }

    public static void setUltimaPos(int ultimaPos) {
        ResumoDia.ultimaPos = ultimaPos;
    }

    public boolean isPodeOperarNoDia() {
        return podeOperar_indicadores;
    }

    public void setPodeOperarNoDia(boolean podeOperarNoDia) {
        this.podeOperar_indicadores = podeOperarNoDia;
    }

    
    /**
     * Gera uma lista de estrategias para o dia, baseado na interface com as
     * estrategias informadas.
     * Caso seja simulação aplicada, gera as estrategias para o dia baseado
     * nas estrategias sugeridas por data.
     */
    private void criaListaOrdensDia(){
        listaOrdensDia.clear();
        
        //SE FOR SIMULAÇÃO APLICADA, USANDO AS ORDENS SUGERIDAS
        if(!Relatorios.getListaOrdensSugeridas().isEmpty()){
            //ESTRATEGIASUGERIDA PRECISA ESTAR ORDENADA
//            System.out.println("Tamanho lista: " + listaOrdensSugeridas.size() + " | Proxima est");
            int i = 0;
            for(List<Ordem> dia : Relatorios.getListaOrdensSugeridas()){
                if(dia.equals(this.data)){
                    for(Ordem ordemPrevista : dia){
                        Ordem ord = (Ordem) Clone.deepClone(ordemPrevista);
                        listaOrdensDia.add(ord);
                    }
                    return;
                }
            }
        }

        for(Ordem item: ConfigOrdens.getListaOrdensFixas()){
            Ordem ord = (Ordem) Clone.deepClone(item);
            listaOrdensDia.add(ord);
        }
    }
    
    /**
     * 
     * @param referencia 
     */
    public void configuraLinhasEntradaESaida_ListaOrdensDoDia(double referencia){
        listaOrdensDia.forEach(ord -> {
            if(!ord.isAtualizouReferencia())
                ord.configuraLinhasEntradaESaidas(referencia);
            });
    }

    public List<Ordem> getListaOrdensDia() {
        return Collections.unmodifiableList(listaOrdensDia);
    }
    
    /**
     * NÃO É NECESSÁRIO VERIFICAR PASSAGEM DE DIA PELO RELATORIODIARIO, POIS O RELATÓRIO JA ESTÁ FEITO.
     * SERIA REDUNDANTE GERA-LO NOVAMENTE, BASTA GRAVAR NO EXCEL.
     * @param diaAtual
     * @param diaAnterior
     * @return 
     */
    @Override
    public boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public String toString(){
        return "Resumo Dia: " + super.toString(); 
    }

    @Override
    public void gravaResumo(Resumos resumo) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Resumos criaInstancia() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    /**
     * Verifica se irá compensar saldos de dias anteriores ou buscará o valor fixo
     * @param rDiaEncerrando
     */
    private void atualizaSaldoDesejado(ResumoDia rDiaEncerrando) {
        if(!ConfigOrdens.isTemGerRisco_CompensaSaldoSimples() && !ConfigOrdens.isTemGerRisco_CompensaSaldoCumulativo()){
            ConfigOrdens.setGerRisco_SaldoDesej(ConfigOrdens.getGerRisco_SaldoDesej_Original());
            super.saldoSerie = 0;
            super.qtdeNegociadaSerie = 0;
            super.saldoContratoSerie = 0;
            return;
        }

        //REPASSANDO A SÉRIE
        super.saldoSerie = rDiaEncerrando.saldoSerie;
        super.qtdeNegociadaSerie = rDiaEncerrando.qtdeNegociadaSerie;
        super.saldoContratoSerie = rDiaEncerrando.saldoContratoSerie;

        // SE NÃO EXECUTOU ORDENS NO DIA, NÃO TEM QUE O ALTERAR/GRAVAR
        if(!rDiaEncerrando.isOrdemExecutada())
            return;
        
        //CALCULANDO
        double saldoDesej = ConfigOrdens.getGerRisco_SaldoDesej_Original();
        
        //SE TEM COMPENSA SALDO CUMULATIVO
        if(ConfigOrdens.isTemGerRisco_CompensaSaldoCumulativo()){
            saldoDesej = ConfigOrdens.getGerRisco_SaldoDesej_Original() + (ConfigOrdens.getGerRisco_SaldoDesej() - rDiaEncerrando.getSaldoSerie());
            saldoDesej = Math.max(saldoDesej, ConfigOrdens.getGerRisco_SaldoDesej_Original());
        }
        
        super.gerRisco_Saldo = saldoDesej;
        ConfigOrdens.setGerRisco_SaldoDesej(saldoDesej);
    }

    
    /**
     * remove todas ordens da lista de ordens do dia
     */
    public void apagaListaOrdensDia() {
        listaOrdensDia.clear();
    }

    public void adicionaOrdemNaLista(List<Ordem> listaTemp) {
        listaOrdensDia.addAll(listaTemp);
    }
    
    private void atualizaOrdemGerRiscoNaListaDoDia(Candle candle) {
        if(!ConfigOrdens.isTemGerRisco_Saldo() && !ConfigOrdens.isTemGerRisco_PtsCont() && !ConfigOrdens.isTemGerRisco_PrejPerm())
            return;

        boolean status = false;
        for(Ordem item : listaOrdensDia){
            if(item.isGerenciamentoDeRisco()){
                status = item.isPodeSair();
                break;
            }
        }

        listaOrdensDia.removeIf(ordem -> ordem.isGerenciamentoDeRisco());
        OrdemGerRisco ordRisco = new GerenciamentoDeRisco().atualizaOrdemGerRisco(candle, this);
        ordRisco.setPodeSair(status);
        adicionaOrdemNaLista_PrimeiraPosicao(ordRisco);
    }

    public void ordenaListaPelasDistanciaDaAberturaDoCandle(Candle candle) {
        atualizaOrdemGerRiscoNaListaDoDia(candle);
        listaOrdensDia.removeIf(ordem -> ordem.isEncerrada());
        listaOrdensDia.forEach(ordem -> ordem.setDistLinhaExecucao(candle, this));
        Collections.sort(listaOrdensDia, new SortDistancia());
        ControleOrdens.ordenouListaDeOrdens = true;
    }

    /**
     * atualiza saldo, saldoSerie, saldoMin, saldoAcum, PrejAcum, posValMed, posValTotal, posMax
     * @param valorRealizado 
     */
    private void atualizaValoresDiarios(double valorRealizado) {
        saldo+= valorRealizado;
        saldoSerie+= valorRealizado;
        saldoMin = Double.min(saldoMin, saldo);
        saldoAcumulado+= valorRealizado;
        prejAcum = Double.min(prejAcum + valorRealizado, 0);
        ordemExecutada = true;
        
        if(pos == 0){
            posValMed = 0;
            posValTotal = 0;
        }else
            posValMed = posValTotal/Math.abs((double)pos);
        
        if(Math.abs(posMax) < Math.abs(pos))
            posMax = pos;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 43 * hash + Objects.hashCode(this.data);
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
        final Resumos other = (Resumos) obj;
        if (!Objects.equals(this.data.getYear(), other.data.getYear())) {
            return false;
        }
        if (!Objects.equals(this.data.getDayOfYear(), other.data.getDayOfYear())) {
            return false;
        }
        return true;
    }

    /**
     * zera saldoSerie e saldoContratoSerie. gerRisco = true;
     */
    public void encerraSerie() {
        this.saldoSerie = 0;
        this.saldoContratoSerie = 0;
        this.gerRisco = true;
    }

    /**
     * zera saldoSerie e saldoContratoSerie. gerRisco = false;
     */
    public void iniciaSerie() {
        this.saldoSerie = 0;
        this.saldoContratoSerie = 0;
        this.gerRisco = false;
    }

    /**
     * Marca dia como acionado o ger.Risco
     * Encerra a série (saldoSerie = 0 e saldoContratoSerie = 0)
     * Apaga a lista de ordens para o dia;
     * @param b 
     */
    public void setGerRisco(boolean b) {
        this.gerRisco = b;
        if(b){
            encerraSerie();
            apagaListaOrdensDia();
        }
    }
    
    public class SortDistancia implements Comparator<Ordem> {

        @Override
        public int compare(Ordem ord1, Ordem ord2) {
           int result = Double.compare(ord1.getDistAberturaCandle(), ord2.getDistAberturaCandle());

           if(ord1.isGerenciamentoDeRisco() && result == 0)
               return -1;

           if(ord2.isGerenciamentoDeRisco() && result == 0)
               return 1;

           return result;
        }

    }
    
    public void printListaOrdensParaODia(){
        int i = 1;
        for(Ordem ord : listaOrdensDia){
            System.out.println("pos: " + i++ + " | ord: " + ord);
        }
    }
    
}

