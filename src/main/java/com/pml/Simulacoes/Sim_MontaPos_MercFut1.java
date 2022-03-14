/**
 *  DAY TRADE / SWING TRADE
 *      • Parâmetros: Contra Mov, Pos Ini, Pos Max, Offset
 *      • Referência para offset: Abertura do candle selecionado (hora/minuto escolhido, ou abertura do dia)
 *      • Lógica da montagem da pos:
 *          o Realiza compras e vendas (a favor ou contra o movimento) conforme os preços oscilam a partir
 *            da abertura, respeitando o limite de Pos Max informado
 *          o Não repete preços no dia
 *          o Primeira ordem de compra e venda tem offset, as demais seguem o passo informado
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Gerenciamento de Risco
 *          o Tenta recuperar prejuízos alterando o Ger.Risco
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import java.util.ArrayList;
import java.util.List;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

public class Sim_MontaPos_MercFut1 extends Simulacao {
    
    public Sim_MontaPos_MercFut1(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;
        
        boolean
        gravouDia = false;

        double
        alvoAtual = ConfigOrdens.getGerRisco_SaldoDesej();
        
        List listaOffset = new ArrayList<>();
        
        //CONFIGURA ESTRATEGIAS
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
            //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                //VERIFICAÇÕES SE PRECISA GERAR EST INICIAIS OU SE FEZ NOVOS EXTREMOS
                if(listaOffset.isEmpty()
                || Candle.getListaCandleMinuto().get(i).getMaxima() > rDia.getMaxLocal()
                || Candle.getListaCandleMinuto().get(i).getMinima() < rDia.getMinLocal()){

                    //VERIFICANDO VALORES ACIMA DA ABERTURA
                    if(Candle.getListaCandleMinuto().get(i).getMaxima() - rDia.getAbertura() > 0){
                        double dif = Candle.getListaCandleMinuto().get(i).getMaxima() - rDia.getAbertura();
                        for(double offset = ConfigOrdens.getOffset(); offset <= dif; offset += ConfigOrdens.getPassoOffset()){
                            if(listaOffset.contains(offset))
                                continue;
                            OrdemSimples ord = new OrdemSimples();
                            if(!ord.isTemContMov())
                                ord.setLadoOrdem(LadoOrdem.COMPRA);
                            else
                                ord.setLadoOrdem(LadoOrdem.VENDA);
                            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                            ord.setOffset(offset);
                            rDia.adicionaOrdemNaLista(ord);
                            listaOffset.add(offset);
                        }
                    }

                    //VERIFICANDO VALORES ABAIXO DA ABERTURA
                    if(Candle.getListaCandleMinuto().get(i).getMinima() - rDia.getAbertura() < 0){
                        double dif = Candle.getListaCandleMinuto().get(i).getMinima() - rDia.getAbertura();
                        for(double offset = (-1*Math.abs(ConfigOrdens.getOffset())); offset >= dif; offset -= ConfigOrdens.getPassoOffset()){
                            if(listaOffset.contains(offset))
                                continue;
                            OrdemSimples ord = new OrdemSimples();
                            if(!ord.isTemContMov())
                                ord.setLadoOrdem(LadoOrdem.VENDA);
                            else
                                ord.setLadoOrdem(LadoOrdem.COMPRA);
                            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                            ord.setOffset(offset);
                            rDia.adicionaOrdemNaLista(ord);
                            listaOffset.add(offset);
                        }
                    }
                } // FIM DA VERIFICAÇÃO E GERAÇÃO DE ESTRATEGIAS OFFSET
                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
            }// diasOperando
            
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, true);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!gravouDia){
                    gravouDia = true;
                    
                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia); //NESTE CASO, DESFAZ POSIÇÃO E TENTA DIA SEGUINTE
                }
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        diaAtual++;
                        alvoAtual+= ConfigOrdens.getGerRisco_SaldoDesej() - rDia.getSaldo();
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, true);
                        gravouDia = false;
                        
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                        listaOffset.clear();
                    }   
                }
            }
        }// Fim da simulação
    }
}
