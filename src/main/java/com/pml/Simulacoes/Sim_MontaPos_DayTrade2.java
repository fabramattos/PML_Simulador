/**
 *  SWINGTRADE
 *      • Parâmetros: Contra Mov, Pos Ini, Pos Max, Offset, Delta
 *      • Referência para offset: Abertura do candle selecionado (hora/minuto escolhido, ou abertura do dia)
 *      • Lógica da montagem da pos:
 *          o Realiza compras e vendas (a favor ou contra o movimento) conforme os preços oscilam
 *            a partir da abertura, respeitando o limite de Pos Max informado
 *          o Primeira ordem de compra ou venda tem offset, as demais seguem o passo informado
 *          o Ao realizar a primeira entrada (offset atingido), as ordens do outro lado são deslocadas para uma distância Delta da primeira entrada
 *              o Ex. Abertura 4000, offset = 10, delta = 5 -> Entrada em 4010 -> Desloca as ordens que estavam abaixo de 3990 para 4005
 *               (primeira entrada 4010 – delta 5 = 4005)
 *          o Não repete preços no dia
 *      • Lógica da saída da pos:
 *          o SwingTrade
 *          o Gerenciamento de Risco
 *          o Não compensa perdas anteriores
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

public class Sim_MontaPos_DayTrade2 extends Simulacao {
    
    private int diaAtual;
    private boolean diaOperando;
    private ResumoDia rDia;
    private OrdemSimples ord;
    private double offsetMax = Double.NEGATIVE_INFINITY;
    private double offsetMin = Double.POSITIVE_INFINITY;
    
    public Sim_MontaPos_DayTrade2(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        diaAtual = super.diaAtual;
        
        //CONFIGURA ESTRATEGIAS
        ord = new OrdemSimples();
        
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
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
                    iniciandoNovoDia();
                }
            }
        }// Fim da simulação
    }
    
    private void logicaDaOperacao(int i) {
        //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
        if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
            //AO ATUALIZAR A REFERENCIA DO DIA, CRIA TODA LISTA DE ORDENS
            if(!rDia.isAtualizouReferencia()){
                rDia.setAberturaSerie(Candle.getListaCandleMinuto().get(i).getAbertura());
                rDia.setAtualizouReferencia(true);
                
                offsetMax = ConfigOrdens.getOffset();
                criaOrdemAcimaAberturaSerie(offsetMax);
                
                offsetMin = -ConfigOrdens.getOffset();
                criaOrdemAbaixoAberturaSerie(offsetMin);
            }
            
            //ORDENS PARA A MÁXIMA DO DIA
            while(rDia.getMaxima() - rDia.getAberturaSerie() >= offsetMax){
                offsetMax+= ConfigOrdens.getPassoOffset();
                criaOrdemAcimaAberturaSerie(offsetMax);
            }
            
            //ORDENS PARA A MÍNIMA DO DIA
            while(rDia.getMinima() - rDia.getAberturaSerie() <= offsetMin){
                offsetMin-= ConfigOrdens.getPassoOffset();
                criaOrdemAbaixoAberturaSerie(offsetMin);
            }
            
            controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
        }
        rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
        Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
    }
    
    private void iniciandoNovoDia() {
        if (diaAtual<Candle.getListaCandleDiario().size()){
            diaAtual++;
            rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, true);

            diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        }   
    }

    private void criaOrdemAcimaAberturaSerie(double offset){
        //ORDENS ACIMA DA ABERTURA
        ord = new OrdemSimples();
        if(!ConfigOrdens.isTemContMov())
            ord.setLadoOrdem(LadoOrdem.COMPRA);
        else
            ord.setLadoOrdem(LadoOrdem.VENDA);
        ord.setOffset(offset);
        ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());

        rDia.adicionaOrdemNaLista(ord);
    }
        
    private void criaOrdemAbaixoAberturaSerie(double offset){
        ord = new OrdemSimples();
        if(!ConfigOrdens.isTemContMov())
            ord.setLadoOrdem(LadoOrdem.VENDA);
        else
            ord.setLadoOrdem(LadoOrdem.COMPRA);
        ord.setOffset(offset);
        ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());

        rDia.adicionaOrdemNaLista(ord);
    }    
    
}
