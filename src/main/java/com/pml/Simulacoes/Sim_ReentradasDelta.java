/**
 *  DAY TRADE
 *      • Parâmetros: Mov, Pos, PosMax, Delta, Offset
 *      • Referência para offset: Abertura do dia
 *      • Lógica da montagem da pos:
 *          o SE for início de uma nova série em um novo dia (após executado gerenciamento de risco), entra no offset escolhido
 *          o Aumenta a pos entrando novamente a cada Delta pontos
 *          o Quantidade de contratos de cada ordem segue a lógica Martingale
 *          o Não realiza novas entradas se atingido pos max
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Gerenciamento de risco
 *          o Tenta compensar resultados de dias anteriores com Martingale
 *              o Não altera o Ger.Risco Saldo Desej
 *          o Reposiciona no dia seguinte na abertura
 **/
package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

public class Sim_ReentradasDelta extends Simulacao {
    
    public Sim_ReentradasDelta(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = super.diaAtual;
        
        boolean
        gravouDia = false;
        
        double offsetMinimo = 0;
        double offsetMaximo = 0;
        
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        
        //CONFIGURA ESTRATEGIAS
        OrdemSimples ord = new OrdemSimples();
        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
        rDia.setAberturaSerie(rDia.getAbertura() + ord.getOffset());
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                
                controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                // VERIFICA G.RISCO NO CANDLE SEGUINTE, APÓS AS ENTRADAS, PARA EVITAR SAIR EM CONDIÇÕES INCERTAS
                gerRisco.testaSaidas(Candle.getListaCandleMinuto().get(i), rDia);
                
                // VERIFICA SE ESTÁ COMPRADO
                if(rDia.getPos() > 0){
                    double offset = Candle.getListaCandleMinuto().get(i).getMinima() - rDia.getAberturaSerie();
                    while(offset <= offsetMinimo - ConfigOrdens.getDelta()){
                        offsetMinimo -= ConfigOrdens.getDelta();
                        ord = new OrdemSimples();
                        ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());
                        ord.setTemOffset(true);
                        ord.setOffset(offsetMinimo);
                        ord.atualizaQtde_MartinGalle(rDia);
                        controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                    }
                }
                
                // VERIFICA SE ESTÁ VENDIDO
                if(rDia.getPos() < 0){
                    double offset = Candle.getListaCandleMinuto().get(i).getMaxima() - rDia.getAberturaSerie();
                    while(offset >= offsetMaximo + ConfigOrdens.getDelta()){
                        offsetMaximo += ConfigOrdens.getDelta();
                        ord = new OrdemSimples();
                        ord.configuraLinhasEntradaESaidas(rDia.getAberturaSerie());
                        ord.setTemOffset(true);
                        ord.setOffset(offsetMaximo);
                        ord.atualizaQtde_MartinGalle(rDia);
                        controladorOrdens.testaOrdem(Candle.getListaCandleMinuto().get(i), rDia, ord);
                    }
                }
            }
            
            rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                if(!gravouDia){
                    gravouDia = true;
                    
                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }
                
                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;
                
                //FECHAMENTO DO DIA
                if(controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                    // ULTIMO CANDLE DIA ANALISADO E AINDA TEM DIA PARA SIMULAR
                    if (diaAtual<Candle.getListaCandleDiario().size()){
                        
                        ord = new OrdemSimples();
                        
                        if(rDia.isPodeOperarNoDia()){
                            if(!rDia.isGerRisco()){
                                ord.setQtde(Math.abs(ResumoDia.getUltimaPos()));
                                if(ResumoDia.getUltimaPos() > 0)
                                    ord.setLadoOrdem(LadoOrdem.COMPRA);
                                else
                                    ord.setLadoOrdem(LadoOrdem.VENDA);
                            }
                        }
                        diaAtual++;
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                        rDia.setAberturaSerie(rDia.getAbertura() + ord.getOffset());
                        
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        
                        offsetMinimo = 0;
                        offsetMaximo = 0;
                        
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                        gravouDia = false;
                    }   
                }
            }
        } //FIM SIMULAÇÃO
    }
}
