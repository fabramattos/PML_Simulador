/**
 *  DAY TRADE
 *      • Parâmetros: Base de Tempo para formação dos Candles, Pos, Pos Max, Delta, Offset, Contra Mov
 *      • Lógica da montagem da pos:
 *          o Registra a Máxima e Mínima no intervalo de tempo selecionado (candles de 5, 10, 15 ou 30 min)
 *          o Monta e desmonta a posição com ordens espaçadas entre si pelo Offset, a partir de Delta pontos
 *            dos extremos do candle anterior, respeitando a Pos Max.
 *      • Lógica da saída da pos:
 *          o Pelo Gerenciamento de Risco, entrando novamente após a formação de um novo Candle.
 *          o Continua reentrando após cada saída pelo Ger. Risco, até sair pelo fechamento.
 *          o Saída pelo Fechamento, reposicionando no dia seguinte pela Abertura
 *              o A distância das ordens que estavam em aberto no dia anterior para o fechamento são
 *                transferidas para o dia que se inicia, em relação a abertura.
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.Ordem;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

public class Sim_MontaPos_ExtremosCandleAnterior1 extends Simulacao {
    
    public Sim_MontaPos_ExtremosCandleAnterior1(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial,
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
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!gravouDia){
                    gravouDia = true;
                    podeCriarOrdems = false;
                    diaOperando = false;
                    posAnterior = rDia.getPos();
                    fechamentoAnterior = Candle.getListaCandleMinuto().get(i).getFechamento();
                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        diaAtual++;
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, true);
                        podeCriarOrdems = false;
                        serieIniciada = false;
                        if(posAnterior != 0){
                            // ENCONTRANDO AS DIFERENÇAS ENTRE O FECHAMENTO E AS PRIMEIRAS ORDENS ABERTAS
                            double entradaOffsetAcima = Double.POSITIVE_INFINITY;
                            double entradaOffsetAbaixo = Double.NEGATIVE_INFINITY;
                            double referencia = 0;
                            for(Ordem item : rDia.getListaOrdensDia()){
                                if(item.isIniciada() || item.isEncerrada()){
                                    //DEBUG
//                                    System.out.println(item);
                                    continue;
                                }
                                if(!ConfigOrdens.isTemContMov()){
                                    if(item.getLadoOrdem() == LadoOrdem.COMPRA)
                                        entradaOffsetAcima = Double.min(entradaOffsetAcima, item.getOffset());
                                    if(item.getLadoOrdem() == LadoOrdem.VENDA)
                                        entradaOffsetAbaixo = Double.max(entradaOffsetAbaixo, item.getOffset());
                                }else{
                                    if(item.getLadoOrdem() == LadoOrdem.VENDA)
                                        entradaOffsetAcima = Double.min(entradaOffsetAcima, item.getOffset());
                                    if(item.getLadoOrdem() == LadoOrdem.COMPRA)
                                        entradaOffsetAbaixo = Double.max(entradaOffsetAbaixo, item.getOffset());
                                }
                                if(referencia == 0)
                                    referencia = item.getLinhaReferencia();
                            }
//                            System.out.println("===== dados no fechamento do dia: " + Candle.getListaCandleMinuto().get(i).printData() + " =====");
//                            System.out.println("Referencia (abertura) = " + referencia + " | Offset acima = " + entradaOffsetAcima + " | Offset abaixo = " + entradaOffsetAbaixo);
                            entradaOffsetAcima = referencia + entradaOffsetAcima;
                            entradaOffsetAbaixo = referencia + entradaOffsetAbaixo;
                            
//                            System.out.println("Referencia (abertura) = " + referencia + " | prox acima = " + entradaOffsetAcima + " | prox abaixo = " + entradaOffsetAbaixo);
                            
                            rDia.apagaListaOrdensDia();
                            OrdemSimples ord = new OrdemSimples();
                            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                            ord.setTemOffset(false);
                            ord.setOffset(0);
                            ord.setQtde(Math.abs(posAnterior));
                            if(posAnterior > 0)
                                ord.setLadoOrdem(LadoOrdem.COMPRA);
                            else
                                ord.setLadoOrdem(LadoOrdem.VENDA);
                            rDia.adicionaOrdemNaLista(ord);
                            
                            // PREPARANDO ORDENS PARA O DIA SEGUINTE, TRANSFERINDO A DISTANCIA DA PROXIMA ENTRADA PARA O FECHAMENTO, PARA
                            // A ABERTURA E A PRIMEIRA ENTRADA ACIMA
                            entradaOffsetAcima = Math.abs(entradaOffsetAcima - fechamentoAnterior);
                           
//                            System.out.println("Delta Acima para o dia seguinte: " + entradaOffsetAcima);
                            
                            for(int j = 0; j <= ConfigOrdens.getPosMaxPerm()*10; j++){
                                ord = new OrdemSimples();
                                ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                                ord.setOffset(entradaOffsetAcima);
                                if(!ConfigOrdens.isTemContMov())
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                                rDia.adicionaOrdemNaLista(ord);
                                entradaOffsetAcima+= passo;
                            }

                            // PREPARANDO ORDENS PARA O DIA SEGUINTE, TRANSFERINDO A DISTANCIA DA PROXIMA ENTRADA PARA O FECHAMENTO, PARA
                            // A ABERTURA E A PRIMEIRA ENTRADA ABAIXO
                            entradaOffsetAbaixo = -1*Math.abs(entradaOffsetAbaixo - fechamentoAnterior);
                            
//                            System.out.println("Delta Abaixo para o dia seguinte: " + entradaOffsetAbaixo);
                            
                            for(int j = 0; j <= ConfigOrdens.getPosMaxPerm()*10; j++){
                                ord = new OrdemSimples();
                                ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                                ord.setOffset(entradaOffsetAbaixo);
                                if(!ConfigOrdens.isTemContMov())
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                rDia.adicionaOrdemNaLista(ord);
                                entradaOffsetAbaixo-= passo;
                            }
                        }
                        gravouDia = false;
                        contadorCandle = 0;
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                        diaAtual++;
                    }   
                }
            }
        }// Fim da simulação
    }
}
