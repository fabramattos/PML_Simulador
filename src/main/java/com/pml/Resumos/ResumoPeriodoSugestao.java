/**
 * Esta Classe registra os dados gerados pela simulação. Faz um resumo agrupado
 * de acordo com as simulações previamentes realizadas
 **/
package com.pml.Resumos;

import com.pml.InterfaceGrafica.IG;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Felipe Mattos
 */
public class ResumoPeriodoSugestao extends ResumoPeriodoRelatorio implements Serializable {
    
    public ResumoPeriodoSugestao() {
    }
    
    public ResumoPeriodoSugestao(Resumos rDia, ResumoPeriodoSugestao p){
        super.data = rDia.getData();
        super.qtdeMax = rDia.getQtdeMax();
        super.saldoAcumulado = rDia.getSaldoAcumulado();
        super.saldoContrato = rDia.getSaldoContrato();
        super.entradas = rDia.getEntradas();
        super.saidas = rDia.getSaidas();
        super.stops = rDia.getStops();
        super.delta = p.getDelta();
        super.offset = p.getOffset();
        super.gain = p.getGain();
        super.loss = p.getLoss();
        super.simultaneo = p.getSimultaneo();
        super.diasTotais = p.getDiasTotais();
    }
    
    /** Método para gerar lista<lista<ResumoPeriodoSugerido> com os dados relevantes
     * para plotar no excel futuramente.
     * Considera apenas dias operados (monitora
     * indicadores).
     * @param diasPer1 periodo de dias a ser analisado para tomada de decisão
     * @param diasPer2  periodo de dias a ser mantido executando estrategia
     */
    @Override
    public void geraResumo(int diasPer1, int diasPer2){
        IG.textoAdd("Analisando períodos\n");
        relatorioPer1 = new ArrayList();
        relatorioPer2 = new ArrayList();
        relatorioEstrategiasPer1 = new ArrayList();
        relatorioEstrategiasPer2 = new ArrayList();
        
        IG.progressoCompletoIndeterminado(true);
        //COMEÇA VARREDURA DE ESTRATEGIAS
        List<List<Resumos>> listaProcessos = Relatorios.getRelatorioCompletoDiario();
        int j = listaProcessos.get(0).size()-1;
        for(int est = 0; est < listaProcessos.size(); est++){
            ResumoPeriodoSugestao rEst = new ResumoPeriodoSugestao();
            boolean analisado = false;
            //CONTADOR PARA NUMERO DE DIAS A PARTIR DO INICIO PARA OPERAR PELA PRIMEIRA VEZ
            boolean gravado = false;
            int contador = 0,
                diasOperados = 0,
                diasUteis = 0;
            //COMEÇA VARREDURA DE PERIODOS
            for(int diaAtual = j; diaAtual>2; diaAtual--){
                diasUteis++;
                if(!gravado)
                    contador++;
                //ULTIMO DIA DE ANALISE, MAS NÃO GRAVOU AINDA POIS FALTAM DIAS PARA PERDIA1/2
                if(diaAtual <= 3){
                    super.data = listaProcessos.get(est).get(diaAtual).getData();
                    if(diasPer2 == 0){
                        salvaPeriodo(rEst, relatorioPer1);
                        break;
                    }
                    if(analisado)
                        salvaPeriodo(rEst, relatorioPer1);
                    else
                        salvaPeriodo(rEst, relatorioPer2);
                    break;
                }
             /*   
                if(!verificaIndicadores.verificaIndicadores(listaProcessos.get(est).get(diaAtual),
                listaProcessos.get(est).get(diaAtual-1),
                listaProcessos.get(est).get(diaAtual-2)))
                    continue;
               */ 
                if(diasOperados == 0){
                    gravado = true;
                    rEst = new ResumoPeriodoSugestao();
                    super.delta = listaProcessos.get(est).get(0).getDelta();
                    super.offset = listaProcessos.get(est).get(0).getOffset();
                    super.limOposto = listaProcessos.get(est).get(0).getLimOposto();
                    super.gain = listaProcessos.get(est).get(0).getGain();
                    super.loss = listaProcessos.get(est).get(0).getLoss();
                    super.gerRisco_Saldo = listaProcessos.get(est).get(0).getGerRisco_Saldo();
                    super.gerRisco_PtsCont = listaProcessos.get(est).get(0).getGerRisco_PtsCont();
                    super.gerRisco_PrejPerm = listaProcessos.get(est).get(0).getGerRisco_PrejPerm();
                    super.dataFin = listaProcessos.get(est).get(diaAtual).getData();
                    if(diaAtual == listaProcessos.get(est).size()-1){
                        LocalDateTime c;
                        c = listaProcessos.get(est).get(diaAtual).getData(); 
                        c.plusDays(1);
                        super.dataAplicada = c;
//                        rEst.setDataFin(listaProcessos.get(est).get(diaAtual).getData());
                    }else
                        super.dataAplicada = listaProcessos.get(est).get(diaAtual+1).getData();
                }
                
                super.maxima = Double.max(super.maxima, listaProcessos.get(est).get(diaAtual).getMaxima());
                super.minima = Double.min(super.minima, listaProcessos.get(est).get(diaAtual).getMinima());
                super.fechamento = listaProcessos.get(est).get(diaAtual).getFechamento();
                super.qtdeMax = Integer.max(super.qtdeMax, listaProcessos.get(est).get(diaAtual).getQtdeMax());
                super.qtdeNegociada += listaProcessos.get(est).get(diaAtual).getQtdeNegociada();
                super.entradas += listaProcessos.get(est).get(diaAtual).getEntradas();
                super.saidas += listaProcessos.get(est).get(diaAtual).getSaidas();
                super.stops += listaProcessos.get(est).get(diaAtual).getStops();
                super.simultaneo += listaProcessos.get(est).get(diaAtual).getSimultaneo();
                super.saldoAcumulado += listaProcessos.get(est).get(diaAtual).getSaldo();
                super.saldoMax = Double.max(super.saldoMax, super.saldoAcumulado);
                super.prejAcum = Double.min(super.prejAcum + listaProcessos.get(est).get(diaAtual).getSaldo(),0);
                super.prejAcumMax = Double.min(super.prejAcumMax, super.prejAcum);
                super.saldoContrato = super.saldoAcumulado/super.qtdeNegociada;
                if (listaProcessos.get(est).get(diaAtual).getSaldo() < 0)
                    super.diasNeg++;
                else
                    super.diasPos++;

                if(super.prejAcumMax == 0)
                    super.relacao = super.saldoMax;
                else
                    super.relacao = super.saldoMax/Math.abs(super.prejAcumMax);
                
                diasOperados++;
                super.diasTotais = diasUteis;

                //PARA ANALISE DE APENAS UM PERIODO
                if(diasPer2 == 0
                && !analisado && diasOperados == diasPer1){
                    super.data = listaProcessos.get(est).get(diaAtual).getData();
                    salvaPeriodo(rEst, relatorioPer1);
                    analisado = false; 
                    diasOperados = 0;
                    diasUteis = 0;
                    break; //NESTE PONTO TEM QUE SAIR DO LOOP FOR E PEGAR PROXIMA ESTRATEGIA PARA O MESMO PERIODO
                }
                
                //ANALISOU PERIODO 1
                if(diasPer2 != 0
                && analisado && diasOperados == diasPer1){
                    rEst.setData(listaProcessos.get(est).get(diaAtual).getData());
                    salvaPeriodo(rEst, relatorioPer1);
                    analisado = false;
                    diasOperados = 0;
                    diasUteis = 0;
                    break; //NESTE PONTO TEM QUE SAIR DO LOOP FOR E PEGAR PROXIMA ESTRATEGIA PARA O MESMO PERIODO
                }

                //ANALISOU PERÍODO 2
                if(diasPer2 != 0
                && !analisado && diasOperados == diasPer2){
                    rEst.setData(listaProcessos.get(est).get(diaAtual).getData());
                    salvaPeriodo(rEst, relatorioPer2);
                    analisado = true; 
                    diasOperados = 0;
                    diasUteis = 0;
                }
            }
            
            //VERIFICAÇÃO SE TERMINARAM AS ESTRATEGIAS
            if(est == listaProcessos.size()-1){
                //SIGNIFICA QUE TERMINOU A LISTA DE ESTRATEGIAS. LOGO, "J" (REFERENCIA PARA
                //INICIO DA ANALISE DE PERIODO), DEVE ANDAR UMA UNIDADE PARA ESQUERDA + CORRESPONDENTE ATE 
                //PRIMEIRO REGISTRO, E INICIAR NOVA ANALISE
                j = j - contador;
                gravado = false;
                contador = 0;
                est = -1;
                relatorioEstrategiasPer1.add(relatorioPer1);
                relatorioPer1 = new ArrayList();
                if(diasPer2 == 0){
                    relatorioEstrategiasPer2.clear();
                }else{
                    relatorioEstrategiasPer2.add(relatorioPer2);
                    relatorioPer2 = new ArrayList();
                }
//                System.out.println("J = " + j);
                if(j<=(diasPer1 + diasPer2)){
//                    System.out.println("break");
                    break;    
                }
            }
        }
    }
    
    /**
     * Encontra a estrategia equivalente na outra lista
     * @param est1 estrategia modelo a ser encontrada
     * @param i contador para sincronizar o período que está sendo feita a leitura
     * @return NULL se não encontrar nada (erro), ou retorna a estrategia e dados
     * do periodo 2
     */
    
    /*
    public ResumoPeriodoSugestao encontraPar2(ResumoPeriodoSugestao est1, int i){
        relatorioEstrategiasPer2.
        
        for(ResumoPeriodoSugestao est2 : relatorioEstrategiasPer2.get(i)){
            if
            if(est1.getDelta() == est2.getDelta()
            && est1.getOffset() == est2.getOffset()
            && est1.getLimOp() == est2.getLimOp()
            && est1.getGain() == est2.getGain()
            && est1.getLoss() == est2.getLoss()){
//                System.out.println("encontrou");
                return est2;
            }
        }
//        System.out.println("erro. não encontrou estrategia par");
        return null;
    }

*/
    @Override
    public boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}