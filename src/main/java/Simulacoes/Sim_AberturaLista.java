/**
 *  DAY TRADE
 *  Abertura Completo:
 *      • Parâmetros: Mov, ContMov, Qtde, E, LimOp, G, L
 *      • Referência para offset: Abertura do dia
 *      • Lógica da montagem da pos:
 *          o Uma única ordem é criada
 *          o Entra no offset escolhido
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Cada ordem de acordo com os seus parâmetros
 *      • Pode executar relatório completo variando os parâmetros da ordem oco e o Ger.Risco.
 *          o Não cria lista de ordens
 * 
 *  Abertura Lista:
 *      • Parâmetros: Mov, ContMov, Qtde, E, LimOp, G, L
 *      • Referência para offset: Abertura do dia
 *      • Lógica da montagem da pos:
 *          o Lista fixa de ordens para operação todos os dias
 *          o Entra no offset escolhido
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Cada ordem de acordo com os seus parâmetros
 *      • Pode executar relatório completo variando apenas o Ger. Risco
 *          o Não altera variáveis das ordens, apenas o Ger.Risco
 * 
 **/

package Simulacoes;

import simulacao.Candle;
import Resumos.ResumoDia;

public class Sim_AberturaLista extends Simulacao {
    
    private int diaAtual;
    private boolean diaOperando;
    private ResumoDia rDia;
    
    public Sim_AberturaLista(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        diaAtual = diaInicial;
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);

        for(int i = minIni; i<= minFin; i++){
            logicaDaOperacao(i);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(controleTempo.verificaFimDasOperacoes(i)){
                gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size())
                        iniciandoNovoDia();
                }
            }
        } //FIM SIMULAÇÃO
    }
    
    private void logicaDaOperacao(int i) {
        if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
            rDia.configuraLinhasEntradaESaida_ListaOrdensDoDia(Candle.getListaCandleMinuto().get(i).getAbertura());
            controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
        }
        Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
    }
    
    private void iniciandoNovoDia() {
        diaAtual++;
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
    }
}
