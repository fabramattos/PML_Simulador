package Simulacoes;

import Configuracoes.ConfigBase;
import simulacao.Candle;
import Controladores.*;
import GerenciamentoDeRisco.*;
import Indicadores.diario.*;
import InterfaceGrafica.IG;
import java.util.List;
import java.util.ArrayList;
import Indicadores.diario.AcoesIndicadores;


/**
 *
 * @author Felipe Mattos
 */
public abstract class Simulacao {

    //ATRIBUTOS
    protected int minIni, minFin, diaInicial;
    private static int totalDeMinutosDetalhado;
    private boolean aplicado;
    protected Candle candle;
    protected ControleTempo controleTempo;
    protected IndicadoresHandler verificadorIndicadores;
    protected ControleOrdens controladorOrdens;
    protected GerenciamentoDeRisco gerRisco;
    protected List<AcoesIndicadores> listaIndicadoresDiario = new ArrayList();
    protected List<AcoesIndicadores> listaIndicadoresMinuto = new ArrayList();
    protected List<ValidacaoGerRisco> listaGerRisco = new ArrayList();
    
    Simulacao(boolean aplicado){
        configuraIndicadores();
        configuraGerRisco();
        candle = new Candle();
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
        totalDeMinutosDetalhado = minFin - minIni;
        IG.progressoSimulacaoAtualiza(totalDeMinutosDetalhado, 0);
        IG.progressoCompletoAtualiza(totalDeMinutosDetalhado, 0);
        this.diaInicial = controleTempo.buscaDiaInicial(this.minIni);
        System.out.println("Minuto Inicial: " + Candle.getListaCandleMinuto().get(this.minIni).printData());
        System.out.println("Dia Inicial: " + Candle.getListaCandleDiario().get(this.diaInicial).printData() + "\n");
        System.out.println("offset diaInicial: " + this.diaInicial);
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
}
