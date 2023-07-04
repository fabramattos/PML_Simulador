/**
 *  DAY TRADE
 *      • Parâmetros: Contra Mov, Pos Ini, Pos Max, Offset, Delta
 *      • Referência para offset: Abertura do candle selecionado (hora/minuto escolhido, ou abertura do dia)
 *      • Lógica da montagem da pos:
 *          o Realiza compras e vendas (a favor ou contra o movimento) conforme os preços oscilam a partir da abertura,
 *            respeitando o limite de Pos Max informado
 *          o Primeira ordem de compra ou venda tem offset, as demais seguem o passo informado
 *          o Ao realizar a primeira entrada (offset atingido), as ordens do outro lado são deslocadas para uma distância Delta da primeira entrada
 *          o Ex. Abertura 4000, offset = 10, delta = 5 -> Entrada em 4010 -> Desloca as ordens que estavam abaixo de 3990 para 4005
 *           (primeira entrada 4010 – delta 5 = 4005)
 *          o Não repete preços no dia
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Gerenciamento de Risco
 * */
package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemSimples;

public class Sim_MontaPos_Inverte extends Simulacao {

    public Sim_MontaPos_Inverte(boolean aplicado) {
        super(aplicado);
    }
    
    private boolean preencheuGrid = false;
    
    @Override
    public void simula() {

        while (candleMinAtual != null) {
            logicaDaOperacao();

            if (verificaFimDasOperacoesNoDia(false)) {
                if(verificaUltimoCandleDoDia(false))
                    iniciaNovoDia();
            }

            avancaParaProximoCandle();
        }
    }

    private void logicaDaOperacao() {
        if (verificadorIndicadores.verificaIndicadores(diaAtual, rDia) && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(candleMinAtual)) {
            if(!preencheuGrid)
                preencheGrid();
            testaSaidas();
        }
    }

    private void iniciaNovoDia() {
         preencheuGrid = false;
    }

    private void preencheGrid() {
        preencheuGrid = true;
        
        OrdemSimples ord = new OrdemSimples();
        if(ConfigOrdens.getLadoOrdem() == LadoOrdem.COMPRA)
            ord.setLadoOrdem(LadoOrdem.COMPRA);
        if(ConfigOrdens.getLadoOrdem() == LadoOrdem.VENDA)
            ord.setLadoOrdem(LadoOrdem.VENDA);
        ord.setLinhaCompra(candleMinAtual.getAbertura());
        rDia.adicionaOrdemNaLista(ord);
        
        for(int i = 0; i<20; i++){
            ord = new OrdemSimples();
            ord.setLadoOrdem(LadoOrdem.COMPRA);
            ord.setOffset(ConfigOrdens.getDelta() + i * ConfigOrdens.getOffset());
            ord.setLinhaCompra(candleMinAtual.getAbertura() + ord.getOffset());
            rDia.adicionaOrdemNaLista(ord);
            
            ord = new OrdemSimples();
            ord.setLadoOrdem(LadoOrdem.VENDA);
            ord.setOffset(-1*ConfigOrdens.getDelta() - i * ConfigOrdens.getOffset());
            ord.setLinhaVenda(candleMinAtual.getAbertura() + ord.getOffset());
            rDia.adicionaOrdemNaLista(ord);
        }
        
    }

    private void testaSaidas() {
        controladorOrdens.testaListaOrdens_InverteNaDirecaoDoMovimento(candleMinAtual, rDia);
    }
}
