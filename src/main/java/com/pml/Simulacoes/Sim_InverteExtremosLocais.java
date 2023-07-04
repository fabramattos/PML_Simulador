/**
 *  DAY TRADE
 *      • Parâmetros: Mov, Pos, Delta, Offset, Gain
 *      • Lógica da montagem da pos:
 *          o Entrada OCO
 *          o Se acionar stop (loss = gain) inverte posição e adiciona contratos
 *          o A partir do primeiro Stop, os extremos passam a se tornar pontos de inversão e
 *            aumento de contratos ao serem superados em Delta pontos
 *          o (A quantidade de contratos é calculada visando não ser necessário um 
 *            GAIN maior que o desejado inicialmente para sair pelo Ger.Risco)
 *      • Lógica da saída da pos:
 *          o Day Trade
 *          o Pelo Gain inicial ou pelo gerenciamento de risco
 *          o Caso tenha acionado a inversão da posição, tenta saída pelo gerenciamento de risco
 *          o Tenta compensar resultados anteriores
 *          o Reposiciona no dia seguinte de acordo com a abertura (Padrão = reposicionar compra/venda à mercado de acordo com a pos do dia
 *            anterior. Caso abra acima dos extremos do dia anterior, entra a favor do movimento) 
 **/

package com.pml.Simulacoes;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemOCO;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import com.pml.Resumos.ResumoDia;

/**
* Entra com ordem OCO. Caso atinja stop, sai da posição, invertendo + 1
* Fica invertendo sempre que r em Delta o max e min da serie.
* Se saida pelo fechamento, dia seguinte entra de acordo com a abertura
* @throws OutOfMemoryError 
*/
public class Sim_InverteExtremosLocais extends Simulacao {
    
    public Sim_InverteExtremosLocais(boolean aplicado) {
        super(aplicado);
    }
    
    @Override
    public void simula(){
        // VARIAVEIS
        this.diaAtual = super.diaAtual;

        boolean
        gravouDia = false,
        entrouStop = false,
        temGerRiscoSaldo_Copia = ConfigOrdens.isTemGerRisco_Saldo(),
        temGerRiscoPrej_Copia = ConfigOrdens.isTemGerRisco_PrejPerm();
        
        //INICIA NOVO DIA
        ResumoDia rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual));
        
        //CONFIGURA ESTRATEGIAS
        OrdemOCO ord = new OrdemOCO();
        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
        rDia.adicionaOrdemNaLista(ord);
        
        ConfigOrdens.setTemGerRisco_Saldo(false);
        ConfigOrdens.setTemGerRisco_PrejPerm(false);
        
        boolean diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
        for(int i = minIni; i<= minFin; i++){
            //PODE OPERAR SEGUNDO INDICADORES E SEGUNDO GER. DE RISCO (NAO HOUVE SAÍDA DAS ORDENS)
            if(diaOperando && !rDia.isGerRisco() && controleTempo.verificaHorarioInicial(Candle.getListaCandleMinuto().get(i))){
                
                controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                
                //SÓ SERÁ TRUE CASO A REENTRADA BASEADA NO STOP TENHA SIDO REALIZADA
                if(entrouStop){
                    //VERIFICA SUPERAÇÃO DA MAX
                    if(rDia.getPos() < 0
                    && Candle.getListaCandleMinuto().get(i).getMaxima() >= (rDia.getMaxLocal() + ConfigOrdens.getDelta())){
                        OrdemSimples ordSimples = new OrdemSimples();
                        ordSimples.setLadoOrdem(LadoOrdem.COMPRA);
                        ordSimples.setNome("inversão compra");
                        ordSimples.atualizaQtde_SaldoDesej(rDia, ConfigOrdens.getGerRisco_SaldoDesej(), ConfigOrdens.getGain());
                        ordSimples.configuraLinhasEntradaESaidas(rDia.getMaxLocal() + ConfigOrdens.getDelta());
                        rDia.adicionaOrdemNaLista(ordSimples);
                        controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                    }

                    //VERIFICAO SUPERAÇÃO DA MIN
                    if(rDia.getPos() > 0
                    && (Candle.getListaCandleMinuto().get(i).getMinima() <= (rDia.getMinLocal() - ConfigOrdens.getDelta()))){
                        OrdemSimples ordSimples = new OrdemSimples();
                        ordSimples.setLadoOrdem(LadoOrdem.VENDA);
                        ordSimples.setNome("inversão venda");
                        ordSimples.atualizaQtde_SaldoDesej(rDia, ConfigOrdens.getGerRisco_SaldoDesej(), ConfigOrdens.getGain());
                        ordSimples.configuraLinhasEntradaESaidas(rDia.getMinLocal() - ConfigOrdens.getDelta());
                        rDia.adicionaOrdemNaLista(ordSimples);
                        controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                    }
                }
               
                //STOP ACIONADO, PRIMEIRA INVERSÃO (BASEADA NO STOP)
                if(!entrouStop 
                && !rDia.getListaOrdensDia().isEmpty() 
                && rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).isStopExecutado()){
                    entrouStop = true;
                    
                    ConfigOrdens.setTemGerRisco_Saldo(temGerRiscoSaldo_Copia);
                    ConfigOrdens.setTemGerRisco_PrejPerm(temGerRiscoPrej_Copia);
                    
                    LadoOrdem mov = rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).getLadoOrdem();
                    int qtde = rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).getQtde();
                    double linhaStop = rDia.getListaOrdensDia().get(rDia.getListaOrdensDia().size()-1).getLinhaStop();

                    OrdemSimples ordSimples = new OrdemSimples();
                    ordSimples.setTemOffset(false);
                    ordSimples.setQtde(qtde + 1);
                    ordSimples.configuraLinhasEntradaESaidas(linhaStop);
                    if(mov == LadoOrdem.COMPRA){
                        ordSimples.setLadoOrdem(LadoOrdem.VENDA);
                        ordSimples.setNome("inversão venda");
                    }else{
                        ordSimples.setLadoOrdem(LadoOrdem.COMPRA);
                        ordSimples.setNome("inversão compra");
                    }
                    rDia.adicionaOrdemNaLista(ordSimples);
                    controladorOrdens.testaListaOrdens(Candle.getListaCandleMinuto().get(i), rDia);
                }
                
                rDia.atualizaDia(Candle.getListaCandleMinuto().get(i));
                Candle.getListaCandleMinuto().get(i).registraResultados(rDia, false);
            }// diasOperando

            // VERIFICA SAIDAS FORÇADAS: TEMPO LIMITE, ULTIMO DADO, FECHAMENTO
            if(i == Candle.getListaCandleMinuto().size()-1
            || controleTempo.verificaHorarioFinal(Candle.getListaCandleMinuto().get(i))
            || controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                int posAnterior = rDia.getPos();
                
                if(!gravouDia){
                    gravouDia = true;

                    gerRisco.encerraDia(Candle.getListaCandleMinuto().get(i), false, rDia);
                }

                // ULTIMO DADO
                if(i == Candle.getListaCandleMinuto().size()-1)
                    break;

                //FECHAMENTO DO DIA
                if(controleTempo.verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i))){
                    //NÃO EXISTE DADOS SEGUINTE PARA SIMULAÇÃO
                    if (diaAtual == Candle.getListaCandleDiario().size())
                        break;
                    
                    diaAtual++;
                    double extremoMax = rDia.getMaxima();
                    double extremoMin = rDia.getMinima();
                    
                    //PREPARANDO NOVO DIA
                    if(rDia.isGerRisco()){
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, false);
                        ord = new OrdemOCO();
                        ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        rDia.adicionaOrdemNaLista(ord);
                        
                        ConfigOrdens.setTemGerRisco_Saldo(false);
                        ConfigOrdens.setTemGerRisco_PrejPerm(false);
                        entrouStop = false;
                    }else{
                        rDia = new ResumoDia(Candle.getListaCandleDiario().get(diaAtual), rDia, true);
                        rDia.setMaxLocal(extremoMax);
                        rDia.setMinLocal(extremoMin);
                        
                        OrdemSimples ordSimples = new OrdemSimples();
                        ordSimples.setTemOffset(false);
                        ordSimples.setQtde(Math.abs(posAnterior));
                        ordSimples.configuraLinhasEntradaESaidas(rDia.getAbertura());
                        ordSimples.atualizaQtde_SaldoDesej(rDia, ConfigOrdens.getGerRisco_SaldoDesej(), ConfigOrdens.getGain());
                        if(posAnterior > 0)
                            ordSimples.setLadoOrdem(LadoOrdem.COMPRA);
                        if(posAnterior < 0)
                            ordSimples.setLadoOrdem(LadoOrdem.VENDA);
                        
                        //ALTERAÇÃO DE ACORDO COM ABERTURA ALEM DOS EXTREMOS
                        if(rDia.getAbertura() >= rDia.getMaxLocal() + ConfigOrdens.getDelta())
                            ordSimples.setLadoOrdem(LadoOrdem.COMPRA);
                        if(rDia.getAbertura() <= rDia.getMinLocal() - ConfigOrdens.getDelta())
                            ordSimples.setLadoOrdem(LadoOrdem.VENDA);
                        
                        rDia.adicionaOrdemNaLista(ordSimples);
                        entrouStop = true;
                    }
                    gravouDia = false;
                    
                    diaOperando = verificadorIndicadores.verificaIndicadores(diaAtual, rDia);
                }
            }
        } //FIM SIMULAÇÃO
    }
}