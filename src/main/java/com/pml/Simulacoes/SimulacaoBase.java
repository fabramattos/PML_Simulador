package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigBase;
import com.pml.Controladores.ControleOrdens;
import com.pml.Controladores.ControleTempo;
import com.pml.Controladores.GerenciamentoDeRisco;
import com.pml.Controladores.IndicadoresHandler;
import com.pml.GerenciamentoDeRisco.PtsContrato;
import com.pml.GerenciamentoDeRisco.SaldoEPtsCont;
import com.pml.GerenciamentoDeRisco.SaldoSerie;
import com.pml.GerenciamentoDeRisco.ValidacaoGerRisco;
import com.pml.infra.Candle;
import com.pml.InterfaceGrafica.IG;
import java.util.List;
import java.util.ArrayList;
import com.pml.Indicadores.diario.AcoesIndicadores;
import com.pml.Indicadores.diario.Generico;
import com.pml.Resumos.ResumoDia;
import javax.swing.SwingWorker;

/**
 *
 * @author Felipe Mattos
 */
public abstract class SimulacaoBase {

    //ATRIBUTOS
    protected int minAtual;
    private int minIni, minFin;
    protected int diaAtual;
    private static int totalDeMinutosDetalhado;
    private boolean montaPos, swingTrade, iniciouOperacaoDia;
    protected Candle candleMinAtual, candleMinProximo, candleMinFinal, candleDiaAtual;
    protected ResumoDia rDia;
    protected ControleTempo controleTempo;
    protected IndicadoresHandler verificadorIndicadores;
    protected ControleOrdens controladorOrdens;
    protected GerenciamentoDeRisco gerRisco;
    private List<AcoesIndicadores> listaIndicadores = new ArrayList<>();
    private List<ValidacaoGerRisco> listaGerRisco = new ArrayList<>();

    
    protected SimulacaoBase(boolean swingTrade, boolean montaPos) {
        this.montaPos = montaPos;
        this.swingTrade = swingTrade;
        this.iniciouOperacaoDia = false;
        controleTempo = new ControleTempo();
        verificadorIndicadores = new IndicadoresHandler(listaIndicadores);
        controladorOrdens = new ControleOrdens();
        gerRisco = new GerenciamentoDeRisco(listaGerRisco);
        buscaPrimeiroEUltimoCandle_Minuto();
        configuraIndicadores();
        configuraGerRisco();
    }

    public void simula(SwingWorker worker) {
        reposicionaCandles();
        while (candleMinAtual != null) {
            if (worker.isCancelled())
                break;
            
            if (verificaSePodeOperar_GerRisco() && verificaSePodeOperar_HorarioInicial() && verificaSePodeOperar_HorarioFinal() && verificaSePodeOperar_Indicador()){
                configuraVariaveis(); //executa uma única vez ao iniciar operacao no dia
                logicaDaOperacao();
            }

            verificaFimDasOperacoesNoDia();
            avancaParaProximoCandle();
        }
    }

    private boolean verificaSePodeOperar_Indicador() {
        return verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
    }

    private boolean verificaSePodeOperar_HorarioInicial() {
        return controleTempo.verificaHorarioInicial(candleMinAtual);
    }
    
    private boolean verificaSePodeOperar_HorarioFinal() {
        return !controleTempo.verificaHorarioFinal(candleMinAtual);
    }


    private boolean verificaSePodeOperar_GerRisco() {
        return !rDia.isGerRisco();
    }

    /**
     * Lógica a ser executada a cada candle autorizado
     */
    protected abstract void logicaDaOperacao();

    /**
     * Esse metódo é executado uma unica vez, no primeiro candle onde a simulação está autorizada a executar
     */
    protected abstract void primeiroCandleValidoDoDia();

    private void reposicionaCandles(){
        minAtual = minIni;  
        diaAtual = controleTempo.buscaDiaInicial(minAtual);
        
        candleMinAtual = Candle.getListaCandleMinuto().get(minAtual);
        candleMinProximo = Candle.getListaCandleMinuto().get(minAtual + 1);
        candleMinFinal = Candle.getListaCandleMinuto().get(minFin);

        candleDiaAtual = Candle.getListaCandleDiario().get(diaAtual);
        
        rDia = new ResumoDia(candleDiaAtual);
    }
    
    private void buscaPrimeiroEUltimoCandle_Minuto() {
        this.minIni = controleTempo.buscaMinutoInicial(
                ConfigBase.isTemDataIni(),
                ConfigBase.getDiaIni(),
                ConfigBase.getMesIni(),
                ConfigBase.getAnoIni()
        );
        this.minFin = controleTempo.buscaMinutoFinal(
                ConfigBase.isTemDataFin(),
                ConfigBase.getDiaFin(),
                ConfigBase.getMesFin(),
                ConfigBase.getAnoFin()
        );

        resetaProgressBar();
    }

    private void resetaProgressBar(){
        totalDeMinutosDetalhado = minFin - minIni;
        IG.progressoSimulacaoAtualiza(totalDeMinutosDetalhado, 0);
        IG.progressoCompletoAtualiza(totalDeMinutosDetalhado, 0);
    }
    
    public static int getTotalDeMinutos() {
        return totalDeMinutosDetalhado;
    }

    private void configuraIndicadores() {
        listaIndicadores.clear();
        listaIndicadores.add(new Generico());
    }

    private void configuraGerRisco() {
        listaGerRisco.clear();
        listaGerRisco.add(new SaldoEPtsCont());
        listaGerRisco.add(new PtsContrato());
        listaGerRisco.add(new SaldoSerie());
    }

    protected void avancaParaProximoCandle() {
        rDia.atualizaDia(candleMinAtual);
        candleMinAtual.registraResultados(rDia, false);
        avancaProximoCandle();
    }

    private boolean verificaSeEhUltimoCandleDoDia() {
        return controleTempo.verificaSeEhUltimoCandleDoDia(candleMinProximo, candleMinAtual);
    }

    private boolean verificaFimDasOperacoesNoDia() {
        if (controleTempo.verificaFimDasOperacoesNoDia(candleMinAtual, candleMinProximo)) {
            gerRisco.encerraDia(candleMinAtual, swingTrade, rDia);
            return true;
        }
        return false;
    }

    private void avancaProximoCandle_Diario(){
         try {
                candleDiaAtual = Candle.getListaCandleDiario().get(++diaAtual);
                rDia = new ResumoDia(candleDiaAtual, rDia, montaPos);
            } catch (IndexOutOfBoundsException e) {
                candleDiaAtual = null;
            }
    }
    
    private void avancaProximoCandle() {
        if (verificaSeEhUltimoCandleDoDia()) {
            avancaProximoCandle_Minuto();
            avancaProximoCandle_Diario();
            iniciouOperacaoDia = false;
            return;
        }
        
        avancaProximoCandle_Minuto();
    }

    private void avancaProximoCandle_Minuto() {
        try {
            candleMinAtual = candleMinProximo;
            candleMinProximo = Candle.getListaCandleMinuto().get(++minAtual);
            if (candleMinProximo.getData().isAfter(candleMinFinal.getData())) {
                candleMinProximo = null;
            }
        } catch (IndexOutOfBoundsException e) {
            candleMinProximo = null;
            System.out.println("FIM DA LISTA");
        }
    }

    private void configuraVariaveis() {
        if (iniciouOperacaoDia)
            return;
        
        primeiroCandleValidoDoDia();
        iniciouOperacaoDia = true;
    }
    
    public String getDatasSelecionadas(){
        String frase = "";
        frase += "Data Inicial encontrada: " + Candle.getListaCandleMinuto().get(minIni).printData() + "\n";
        frase += "Data Final encontrada: " + Candle.getListaCandleMinuto().get(minFin).printData() + "\n" ;
    
        return frase;
     }
    
    
}
