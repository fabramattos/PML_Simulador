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
package Simulacoes;

import Configuracoes.ConfigOrdens;
import Ordens.LadoOrdem;
import Ordens.OrdemOCO;
import simulacao.Candle;
import Resumos.ResumoDia;

public class Sim_InverteStop1 extends Simulacao {

    public Sim_InverteStop1(boolean aplicado) {
        super(aplicado);
    }
    
    private int diaAtual;
    private double linhaUltimoStopCompra, linhaUltimoStopVenda, linhaUltimaEntrada, distFechAteLinhaStopAberto;
    private boolean diaOperando;
    private LadoOrdem ladoUltimaOrdem;
    private OrdemOCO ord;
    private ResumoDia rDia;
    
    
    @Override
    public void simula(){
        diaAtual = diaInicial;
        ord = new OrdemOCO();
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
                        iniciandoNovoDia(i);
                }
            }
        } //FIM SIMULAÇÃO
    }
    
    private void logicaDaOperacao(int i) {
        if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
            if(!ord.isAtualizouReferencia()){
                ord.configuraLinhasEntradaESaidas(Candle.getListaCandleMinuto().get(i).getAbertura());
                if(ladoUltimaOrdem == LadoOrdem.COMPRA){
                    linhaUltimoStopCompra = ord.getLinhaStop();
                    linhaUltimoStopVenda = ord.getLinhaCompra();
                }else{
                    linhaUltimoStopVenda = ord.getLinhaStop();
                    linhaUltimoStopCompra = ord.getLinhaVenda();
                }
            }
                
            controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
            gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
            //STOP ACIONADO, CRIA NOVA ESTRATEGIA
            if(ord.isStopExecutado()){
                ladoUltimaOrdem = ord.getLadoOrdem();
                if(ladoUltimaOrdem == LadoOrdem.COMPRA){
                    linhaUltimaEntrada = ord.getLinhaCompra();
                    linhaUltimoStopCompra = ord.getLinhaStop();
                }else{
                    linhaUltimaEntrada = ord.getLinhaVenda();
                    linhaUltimoStopVenda = ord.getLinhaStop();
                }
                
                ord = new OrdemOCO();
                ord.setTemOffset(true);
                if(ladoUltimaOrdem == LadoOrdem.COMPRA){
                    ord.setOffset(linhaUltimoStopCompra - linhaUltimaEntrada);
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                }else{
                    ord.setOffset(linhaUltimoStopVenda - linhaUltimaEntrada);
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                }

                ord.configuraLinhasEntradaESaidas(linhaUltimaEntrada);
                controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
            }
            gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
        }// diasOperando
        rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
        Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
    }
    
    private void iniciandoNovoDia(int i) {
        diaAtual++;
        distFechAteLinhaStopAberto = (Candle.getListaCandleMinuto().get(i).getFechamento() - linhaUltimoStopCompra);
                
        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);

        //REPOSICIONANDO NA ABERTURA DE FORMA MANUAL -> ORDEM ESPECIFICA OCO
        if(ResumoDia.getUltimaPos() != 0){
            ord = new OrdemOCO();
            ord.setTemOffset(false);
            if(ResumoDia.getUltimaPos() > 0){
                if(!ConfigOrdens.isTemContMov())
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                else
                    ord.setLadoOrdem(LadoOrdem.VENDA);
            }else {
                if(!ConfigOrdens.isTemContMov())
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                else
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
            }
            
            //VERIFICA INVERSÃO DE LADO
            if((rDia.getAbertura() - linhaUltimoStopCompra) * (distFechAteLinhaStopAberto) < 0){
                if(ord.getLadoOrdem() == LadoOrdem.COMPRA)
                    ord.setLadoOrdem(LadoOrdem.VENDA);
                else
                    ord.setLadoOrdem(LadoOrdem.COMPRA);
            }
            
            //APLICANDO NOMES E CONFIGURAÇÕES MANUAIS
            if(ord.getLadoOrdem() == LadoOrdem.COMPRA){
                ord.setNome("Reposiciona Abertura: Compra");
                ord.setLinhaCompra(rDia.getAbertura());
                ord.setLinhaStop(linhaUltimoStopCompra);
            }else{
                ord.setNome("Reposiciona Abertura: Venda");
                ord.setLinhaVenda(rDia.getAbertura());
                ord.setLinhaStop(linhaUltimoStopVenda);
            }
            
            ord.setReferencia(rDia.getAbertura());
            controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i+1), rDia, ord);
        }else
            ord = new OrdemOCO();
        
        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
    }
}
