/**
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

package com.pml.Simulacoes.AberturaCompleto;

import com.pml.Ordens.OrdemOCO;
import com.pml.Simulacoes.SimulacaoBase;

public class AberturaCompleto extends SimulacaoBase {
    
    public AberturaCompleto(){
        super(false, false);
    }
    
    private OrdemOCO ord;

    @Override
    protected void logicaDaOperacao() {
        controladorOrdens.testaListaOrdens(candleMinAtual, rDia);
    }
    
    @Override
    protected void primeiroCandleValidoDoDia() {
        ord = new OrdemOCO();
        rDia.configuraLinhasEntradaESaida_ListaOrdensDoDia(candleDiaAtual.getAbertura());
        rDia.adicionaOrdemNaLista(ord);
    }
}