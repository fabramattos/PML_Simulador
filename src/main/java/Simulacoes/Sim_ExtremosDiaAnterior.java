/**
 *  DAY TRADE
*       • Parâmetros: Qtde, G, L
*       • Lógica da montagem da pos:
*           o Executa ordem ao superar Máxima ou Mínima do dia anterior
*           o Pode realizar a favor ou contra movimento
*       • Lógica da saída da pos:
*           o Day Trade
*           o De acordo com os parâmetros de cada ordem
*       • Pode montar uma lista de ordens e simular em conjunto 
 **/

package Simulacoes;

import Configuracoes.ConfigOrdens;
import Ordens.LadoOrdem;
import Ordens.Ordem;
import Ordens.OrdemOCO;
import simulacao.Candle;
import Resumos.ResumoDia;

public class Sim_ExtremosDiaAnterior extends Simulacao {
    
    public Sim_ExtremosDiaAnterior(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        int
        diaAtual = diaInicial;
        
        boolean
        diaGravado = false,
        superouExtremo = false;

        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        // CONFIGURA ESTRATEGIA
        OrdemOCO ord = new OrdemOCO();
        rDia.adicionaOrdemNaLista(ord);

        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                rDia.configuraLinhasEntradaESaida_ListaOrdensDoDia(Candle.getListaCandleMinuto().get(i).getAbertura());
                // ABERTURA JA ACIMA DOS EXTREMOS
                if(!superouExtremo && rDia.getAbertura() >= Candle.getListaCandleDiario().get(diaAtual-1).getMaxima() + ConfigOrdens.getDelta()){
                    for(Ordem item : rDia.getListaOrdensDia()){
                        item.setOffset(0);
                        if(!item.isTemContMov())
                            item.setLadoOrdem(LadoOrdem.COMPRA);
                        else
                            item.setLadoOrdem(LadoOrdem.VENDA);
                    }
                    superouExtremo = true;
                }       
                // ABERTURA JA ABAIXO DOS EXTREMOS
                if(!superouExtremo && rDia.getAbertura() <= Candle.getListaCandleDiario().get(diaAtual-1).getMinima() - ConfigOrdens.getDelta()){
                    for(Ordem item : rDia.getListaOrdensDia()){
                        item.setOffset(0);
                        if(!item.isTemContMov())
                            item.setLadoOrdem(LadoOrdem.VENDA);
                        else
                            item.setLadoOrdem(LadoOrdem.COMPRA);
                    }
                    superouExtremo = true;
                }       

                // ENTRADA NA MAX
                if(!superouExtremo && rDia.getMaxLocal() >= Candle.getListaCandleDiario().get(diaAtual-1).getMaxima() + ConfigOrdens.getDelta() ){
                    for(Ordem item : rDia.getListaOrdensDia()){
                        item.setOffset(Candle.getListaCandleDiario().get(diaAtual-1).getMaxima() + ConfigOrdens.getDelta() - rDia.getAbertura());
                        if(!item.isTemContMov())
                            item.setLadoOrdem(LadoOrdem.COMPRA);
                        else
                            item.setLadoOrdem(LadoOrdem.VENDA);
                    }
                    superouExtremo = true;
                }       
                
                // ENTRADA NA MIN
                if(!superouExtremo && rDia.getMinLocal() >= Candle.getListaCandleDiario().get(diaAtual-1).getMinima() - ConfigOrdens.getDelta()){
                    for(Ordem item : rDia.getListaOrdensDia()){
                        item.setOffset(Candle.getListaCandleDiario().get(diaAtual-1).getMinima() - ConfigOrdens.getDelta() - rDia.getAbertura());
                        if(!item.isTemContMov())
                            item.setLadoOrdem(LadoOrdem.VENDA);
                        else
                            item.setLadoOrdem(LadoOrdem.COMPRA);
                    };
                    superouExtremo = true;
                }       
                
                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
            }
            
            Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
            
            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i)) 
            || controleTempo.verificaPassagemDia(Candle.getListaCandleMinuto().get(i+1).getData(), Candle.getListaCandleMinuto().get(i).getData())){
                if(!diaGravado){
                    diaGravado = true;
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
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                        
                        ord = new OrdemOCO();
                        rDia.adicionaOrdemNaLista(ord);
                        
                        diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                        
                        diaGravado = false;
                        superouExtremo = false;
                    }   
                }
            }
        } //FIM SIMULAÇÃO
    }
}