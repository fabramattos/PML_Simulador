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
import com.pml.Indicadores.diario.AberturaFechamento;
import com.pml.Indicadores.diario.AberturaGenerico;
import com.pml.simulacao.Candle;
import com.pml.InterfaceGrafica.IG;
import java.util.List;
import java.util.ArrayList;
import com.pml.Indicadores.diario.AcoesIndicadores;
import com.pml.Indicadores.diario.DirecaoGenerico;
import com.pml.Indicadores.diario.FechamentoAbertura;
import com.pml.Indicadores.diario.Generico;
import com.pml.Resumos.ResumoDia;


/**
 *
 * @author Felipe Mattos
 */
public abstract class Simulacao {

    //ATRIBUTOS
    protected int minIni, minFin;  //TODO remover esses campos
    protected int diaAtual;
    private static int totalDeMinutosDetalhado;
    private boolean aplicado;
    protected Candle candleMinAtual, candleMinProximo, candleMinFinal, candleDiaAtual;
    protected ResumoDia rDia;
    protected ControleTempo controleTempo;
    protected IndicadoresHandler verificadorIndicadores;
    protected ControleOrdens controladorOrdens;
    protected GerenciamentoDeRisco gerRisco;
    private List<AcoesIndicadores> listaIndicadoresDiario = new ArrayList<>();
    private List<AcoesIndicadores> listaIndicadoresMinuto = new ArrayList<>();
    private List<ValidacaoGerRisco> listaGerRisco = new ArrayList<>();
    
    Simulacao(boolean aplicado){
        configuraIndicadores();
        configuraGerRisco();
        controleTempo = new ControleTempo();
        verificadorIndicadores = new IndicadoresHandler(listaIndicadoresDiario, listaIndicadoresMinuto);
        controladorOrdens = new ControleOrdens();
        gerRisco = new GerenciamentoDeRisco(listaGerRisco);
        configura(aplicado);
    }

    public abstract void simula();

    public void configura(boolean aplicado){
        this.aplicado = aplicado;
        if(this.aplicado){
            this.minIni = controleTempo.buscaMinutoInicial();
            this.minFin = controleTempo.buscaMinutoFinal(false, 0,0,0);
        } else{
            this.minIni = controleTempo.buscaMinutoInicial(ConfigBase.isTemDataIni(),
                                            ConfigBase.getDiaIni(), ConfigBase.getMesIni(), ConfigBase.getAnoIni());
            this.minFin = controleTempo.buscaMinutoFinal(ConfigBase.isTemDataFin(),
                                            ConfigBase.getDiaFin(), ConfigBase.getMesFin(), ConfigBase.getAnoFin());
        }
        
        diaAtual = controleTempo.buscaDiaInicial(minIni);
        candleMinAtual = Candle.getListaCandleMinuto().get(minIni);
        candleMinProximo = Candle.getListaCandleMinuto().get(minIni+1);
        candleMinFinal = Candle.getListaCandleMinuto().get(minFin);
        
        candleDiaAtual = Candle.getListaCandleDiario().get(diaAtual);
        
        rDia = new ResumoDia(candleDiaAtual);
        totalDeMinutosDetalhado = minFin - minIni;
        IG.progressoSimulacaoAtualiza(totalDeMinutosDetalhado, 0);
        IG.progressoCompletoAtualiza(totalDeMinutosDetalhado, 0);
    }

    public static int getTotalDeMinutos() {
        return totalDeMinutosDetalhado;
    }
    
    private void configuraIndicadores(){
        listaIndicadoresDiario.clear();
        listaIndicadoresDiario.add(new AberturaFechamento());
        listaIndicadoresDiario.add(new AberturaGenerico());
        listaIndicadoresDiario.add(new DirecaoGenerico());
        listaIndicadoresDiario.add(new FechamentoAbertura());
        listaIndicadoresDiario.add(new Generico());
    }

    private void configuraGerRisco() {
        listaGerRisco.clear();
        listaGerRisco.add(new SaldoEPtsCont());
        listaGerRisco.add(new PtsContrato());
        listaGerRisco.add(new SaldoSerie());
    }
    
    protected void avancaParaProximoCandle(){
        rDia.atualizaDia(candleMinAtual);
        candleMinAtual.registraResultados(rDia, false);    
        candleMinAtual = candleMinProximo;
        atualizaProximoCandle();
    }
    
    protected boolean verificaUltimoCandleDoDia(boolean estrategiaMontaPos){
        if(controleTempo.verificaSeEhUltimoCandleDoDia(candleMinProximo, candleMinAtual)){
            
            try{
                candleDiaAtual = Candle.getListaCandleDiario().get(++diaAtual);
                rDia = new ResumoDia(candleDiaAtual, rDia, estrategiaMontaPos);
                return true;
            }catch(IndexOutOfBoundsException e){
                candleDiaAtual = null;
                return true;
            }
        }
        return false;
    }
    
    protected boolean verificaFimDasOperacoesNoDia(Boolean swingTrade){
         if (controleTempo.verificaFimDasOperacoesNoDia(candleMinAtual, candleMinProximo)) {
                gerRisco.encerraDia(candleMinAtual, swingTrade, rDia);
                return true;
         }
         return false;
    }
    
    private void atualizaProximoCandle() {
        try{
            candleMinProximo = Candle.getListaCandleMinuto().get(++minIni);
            if (candleMinProximo.getData().isAfter(candleMinFinal.getData()))
                candleMinProximo = null;
        }catch(IndexOutOfBoundsException e){
            candleMinProximo = null;
            System.out.println("FIM DA LISTA");
        }
        
    }
}
