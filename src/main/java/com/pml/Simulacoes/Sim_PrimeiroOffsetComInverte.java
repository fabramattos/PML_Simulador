/**
 *  DAY TRADE
 *      • Parâmetros: Qtde, QtdeMax, Offset, Extremo, Loss
 *      • Lógica da montagem da pos:
 *          o Alvo = Sempre o Extremo desejado em relação à abertura do dia
 *          o Compra ou Venda inicial de acordo com o Offset com ordens OCO
 *          o Caso acione o Stop, entra a favor do movimento no valor do Stop, com novo alvo (visando o outro extremo)
 *          o Continua entrando a favor do movimento a cada stop seguindo o mesmo raciocínio
 *          o Caso o alvo visado não seja o suficiente para conseguir Pts/Contrato especificado,
 *            aumenta a quantidade de contratos para atingir esse mínimo desejado
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Pelo alvo 
 **/
package com.pml.Simulacoes;

import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.simulacao.Candle;
import com.pml.Resumos.ResumoDia;

public class Sim_PrimeiroOffsetComInverte extends Simulacao {

    public Sim_PrimeiroOffsetComInverte(boolean aplicado) {
        super(aplicado);
    }
    
    private int diaAtual;
    private boolean diaOperando, entrouPrimeiraVez, primeiraEntradaAcima;
    private OrdemOCO ord;
    private ResumoDia rDia;
    
    
    
    @Override
    public void simula(){
        diaAtual = super.diaAtual;
        ord = new OrdemOCO();
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        
        entrouPrimeiraVez = false;
        primeiraEntradaAcima = false;
        
        
        for(int i = minIni; i<= minFin; i++){
            logicaDaOperacao(i);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(controleTempo.verificaFimDasOperacoesNoDia(i)){
                gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
               if(controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size())
                        iniciandoNovoDia(i);
                }
            }
        } //FIM SIMULAÇÃO
    }
    
    private void logicaDaOperacao(int i) {
        if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
            if(!rDia.isAtualizouReferencia()){
                rDia.setAberturaSerie(Candle.getListaCandleMinuto().get(i).getAbertura());
                rDia.setAtualizouReferencia(true);
            }
             
            verificaEntradaAcimaDaReferencia(i);
            verificaEntradaAbaixoDaReferencia(i);
            verificaSeEntrouPrimeiraVez();
            configuraOrdemAposStop();
            testaSaidas(i);
            
        }// diasOperando
        rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
        Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
    }
    
    private void iniciandoNovoDia(int i) {
        diaAtual++;
                
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
        ord = new OrdemOCO();
        
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
    }

    private void verificaSeEntrouPrimeiraVez() {
        if(entrouPrimeiraVez == false && ord.isIniciada()){
            entrouPrimeiraVez = true;
            primeiraEntradaAcima = ord.getLinhaReferencia() > rDia.getAberturaSerie();
        }
    }

    private void configuraOrdemAposStop() {
        if(ord.isStopExecutado()){
            ord = new OrdemOCO();
        }
    }

    private void verificaEntradaAcimaDaReferencia(int i) {
        if(!ord.isIniciada()){
            if(entrouPrimeiraVez && !primeiraEntradaAcima)
                ord.setOffset(0);
            ord.setLadoOrdem(LadoOrdem.COMPRA);
            ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());
            controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
        }
    }

    private void verificaEntradaAbaixoDaReferencia(int i) {
        if(!ord.isIniciada()){
            if(entrouPrimeiraVez && primeiraEntradaAcima)
                ord.setOffset(0);
            ord.setLadoOrdem(LadoOrdem.VENDA);
            ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());
            controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
        }
    }

    private void testaSaidas(int i) {
        controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
        gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
    }
}
