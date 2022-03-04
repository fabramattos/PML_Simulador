/**
 *  DAY TRADE
 *      • Parâmetros: Qtde 1, Qtde 2, Offset, Gain, Loss
 *      • Lógica da montagem da pos:
 *          o Entra com ordem OCO, com Qtde 1, com referência na abertura
 *          o Caso acione o Stop, inverte a posição com ordem OCO, Qtde 2, sem alvo
 *      ▪ Pode inverter diversas vezes de acordo com o Loss informado para as OCO
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o pelo GAIN da primeira OCO
 *          o pelo Ger. Risco a partir da inversão.
 *          o Não compensa perdas de dias anteriores 
 **/

package Simulacoes;

import Configuracoes.ConfigOrdens;
import Ordens.LadoOrdem;
import Ordens.OrdemOCO;
import simulacao.Candle;
import Resumos.ResumoDia;

/**
* Monitora os X minutos iniciais de acordo com a interface grafica inicial.
* ao r extremo, entra a favor do mov.
* se sair stop, inverte no stop e busca ptsCont
*/
public class Sim_InverteStop2 extends Simulacao {
    
    public Sim_InverteStop2(boolean aplicado) {
        super(aplicado);
    }

    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;
        
        boolean
        gravouDia = false,
        
        gerRiscoSaldoCopia = ConfigOrdens.isTemGerRisco_Saldo(),
        gerRiscoPtsContCopia = ConfigOrdens.isTemGerRisco_PtsCont(),
        gerRiscoPrejCopia = ConfigOrdens.isTemGerRisco_PrejPerm();
        
        ConfigOrdens.setTemGerRisco_Saldo(false);
        ConfigOrdens.setTemGerRisco_PtsCont(false);
        ConfigOrdens.setTemGerRisco_PrejPerm(false);

        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        //CONFIGURA ESTRATEGIAS
        OrdemOCO ord = new OrdemOCO();
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                ord.configuraLinhasEntradaESaidas(Candle.getListaCandleMinuto().get(i).getAbertura());
                controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                
                //STOP ACIONADO, INVERTE POSIÇÃO
                if(ord.isStopExecutado() || ord.isTrStopExecutado()){
                    LadoOrdem mov = ord.getLadoOrdem();
                    double linhaStop = ord.getLinhaStop();
                    double linhaRef = ord.getLinhaReferencia();
                    if(ord.isTrStopExecutado())
                        linhaStop = ord.getLinhaTrStop();
                    
                    ord = new OrdemOCO();
                    ord.setTemAlvo(false);
                    ord.setTemOffset(true);
                    ord.setQtde(ConfigOrdens.getPosMaxPerm());
                    ord.setOffset(linhaStop - rDia.getAbertura());
                    if(mov == LadoOrdem.COMPRA){
                        ord.setLadoOrdem(LadoOrdem.VENDA);
                        ord.setNome("inversão venda");
                    }else{
                        ord.setLadoOrdem(LadoOrdem.COMPRA);
                        ord.setNome("inversão compra");
                    }
                    
                    ord.configuraLinhasEntradaESaidas(linhaRef);
                    controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                    
                    //RESTAURA GER.RISCO
                    ConfigOrdens.setTemGerRisco_Saldo(gerRiscoSaldoCopia);
                    ConfigOrdens.setTemGerRisco_PtsCont(gerRiscoPtsContCopia);
                    ConfigOrdens.setTemGerRisco_PrejPerm(gerRiscoPrejCopia);
                }
            }
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);

            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!gravouDia){
                    gravouDia = true;

                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }

                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1){
                    ConfigOrdens.setTemGerRisco_Saldo(gerRiscoSaldoCopia);
                    ConfigOrdens.setTemGerRisco_PtsCont(gerRiscoPtsContCopia);
                    ConfigOrdens.setTemGerRisco_PrejPerm(gerRiscoPrejCopia);
                    break;
                }
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                    //NÃO EXISTE DADOS SEGUINTE PARA SIMULAÇÃO
                    if (diaAtual == Candle.getListaCandleDiario().size())
                        break;

                    diaAtual++;
                    rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                    
                    ord = new OrdemOCO();

                    ConfigOrdens.setTemGerRisco_Saldo(false);
                    ConfigOrdens.setTemGerRisco_PtsCont(false);
                    ConfigOrdens.setTemGerRisco_PrejPerm(false);
                    
                    gravouDia = false;
                    
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        } //FIM SIMULAÇÃO
    }
}