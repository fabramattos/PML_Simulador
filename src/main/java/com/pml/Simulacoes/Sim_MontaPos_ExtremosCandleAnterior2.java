/**
 *  SWING TRADE
 *      • Parâmetros: Base de Tempo para formação dos Candles, Pos, Pos Max, Delta, Offset, Contra Mov
 *      • Lógica da montagem da pos:
 *          o Registra a Máxima e Mínima no intervalo de tempo selecionado (candles de 5, 10, 15 ou 30 min)
 *          o A partir de Delta pontos dos extremos do candle anterior, ordens são colocadas com espaçamento
 *            informado no Offset, para montar e desmontar a posição
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Pelo Ger. de Risco, entrando novamente após a formação de um novo Candle.
 *          o Continua reentrando após cada saída pelo Ger. Risco, até sair pelo fechamento.
 *          o Saída pelo Fechamento, levando as ordens em aberto para o dia seguinte
 *      ▪ Se a abertura ocorre acima ou abaixo das ordens que estavam em aberto, cancela as ordens
 *        necessárias para respeitar a lógica da montagem, desmontagem (à favor ou contra tendência)
 * 
 */

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import java.util.ArrayList;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import java.util.List;
import com.pml.Resumos.ResumoDia;

public class Sim_MontaPos_ExtremosCandleAnterior2 extends Simulacao {
    
    public Sim_MontaPos_ExtremosCandleAnterior2(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = super.diaAtual,
        posAnterior = 0,
        contadorCandle = 0;
        
        boolean
        gravouDia = false,
        podeCriarOrdems = false,
        serieIniciada = false;
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        //PREPARA CANDLES PARA ANALISE DE EXTREMOS
        double delta = ConfigOrdens.getDelta();
        double passo = ConfigOrdens.getOffset();
        double offset = 0;
        double fechamentoAnterior = -1;
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
//                System.out.println(Candle.getListaCandleMinuto().get(i));
                
                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);

                if(!serieIniciada){
                    for(Ordem item : rDia.getListaOrdensDia()){
                        if(item.isIniciada()){
                            serieIniciada = true;
                            podeCriarOrdems = false;
                        }
                    }
                }
                if(rDia.isGerRisco()){
                    rDia.apagaListaOrdensDia();
                    rDia.iniciaSerie();
                    serieIniciada = false;
                    contadorCandle = -1;
                    
                }
            }
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, true);
            if(Candle.getListaCandleMinuto().get(i).criaCandleIntermediario(ConfigOrdens.getAguardaFormacaoCandle())){
                contadorCandle++;
                if(!serieIniciada && contadorCandle > 0)
                    podeCriarOrdems = true;
            }
            if(podeCriarOrdems){
                rDia.apagaListaOrdensDia();
                //ACIMA DA MAX
                offset = 0;
                for(int j = 0; j <= ConfigOrdens.getPosMaxPerm()*10; j++){
                    OrdemSimples ord = new OrdemSimples();
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    ord.setOffset(Candle.getMaxCandleYMinAnterior() + delta + offset - rDia.getAbertura());
                    if(!ConfigOrdens.isTemContMov())
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                    else
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                    rDia.adicionaOrdemNaLista(ord);
                    offset+= passo;
                }

                //ABAIXO DA MIN
                offset = 0;
                for(int j = 0; j <= ConfigOrdens.getPosMaxPerm()*10; j++){
                    OrdemSimples ord = new OrdemSimples();
                    ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                    ord.setOffset(Candle.getMinCandleYMinAnterior() - delta - offset - rDia.getAbertura());
                    if(!ConfigOrdens.isTemContMov())
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                    else
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                    rDia.adicionaOrdemNaLista(ord);
                    offset+= passo;
                }
                podeCriarOrdems = false;
            }
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                if(!gravouDia){
                    gravouDia = true;
                    podeCriarOrdems = false;
                    diaOperando = false;
                    posAnterior = rDia.getPos();
                    fechamentoAnterior = Candle.getListaCandleMinuto().get(i).getFechamento();
                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), true, rDia);
                }
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        diaAtual++;
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, true);
                        serieIniciada = false;
                        if(posAnterior != 0){
                            serieIniciada = true;
                            List<Ordem> listaTemp = new ArrayList<>();
                            //VARRE LISTA E COPIA AS ORDENS PERMITIDAS PARA O DIA SEGUINTE
                            for(Ordem item: rDia.getListaOrdensDia()){
                                if(!item.isIniciada() && !item.isEncerrada()){
                                    if(!item.isTemContMov()){
                                        if(item.getLadoOrdem() == LadoOrdem.COMPRA){
                                            if(rDia.getAbertura() < item.getLinhaReferencia() + item.getOffset())
                                                listaTemp.add(item);
                                        }
                                        if(item.getLadoOrdem() == LadoOrdem.VENDA){
                                            if(rDia.getAbertura() > item.getLinhaReferencia() + item.getOffset())
                                                listaTemp.add(item);
                                        }
                                    }else{
                                        if(item.getLadoOrdem() == LadoOrdem.VENDA){
                                            if(rDia.getAbertura() < item.getLinhaReferencia() + item.getOffset())
                                                listaTemp.add(item);
                                        }
                                        if(item.getLadoOrdem() == LadoOrdem.COMPRA){
                                            if(rDia.getAbertura() > item.getLinhaReferencia() + item.getOffset())
                                                listaTemp.add(item);
                                        }
                                    }
                                }
                            }
                            rDia.apagaListaOrdensDia();
                            rDia.adicionaOrdemNaLista(listaTemp);
                        }
                    }
                    gravouDia = false;
                    contadorCandle = 0;
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        }// Fim da simulação
    }
}
