package com.pml.simulacao;

import com.pml.Ordens.OrdemSimples;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.InterfaceGrafica.IG;
import java.util.Calendar;
import java.util.ArrayList;

public class GeraEstrategias extends OrdemSimples{
    
//////    
//////    /**
//////     * lista que será atualizada. Recebe as listas com as datas para um unico dia. Reseta
//////     * para cada novo dia
//////     */
//////    private static ArrayList<OrdemComum> listaEstrategia;
//////    
//////    public GeraEstrategias() {
//////    }
//////    
//////    // Métodos Especiais
//////    public void limpaRelatorios(){
//////        listaEstrategia = new ArrayList<>();
//////    }
//////    
//////    public void geraListaEstrategias(){
//////        limpaRelatorios();
//////        IG.textoAdd("Gerando estratégias sugeridas\n");
//////        ResumoPeriodoSugestao rPer = new ResumoPeriodoSugestao();
//////        
//////        //ORGANIZA CADA PERIODO DE ACORDO A LOGICA UTILIZADA: ORDENA LISTA EXTERNA POR DATA APLICADA
//////        //DEPOIS ORDENA AS ESTRATEGIAS DENTRO DOS PERIODOS, PELO SALDO
//////        System.out.println("LISTA1: PERs = " + ResumoPeriodoSugestao.relatorioEstrategiasPer1.size()
//////                            + " | ESTs: " + ResumoPeriodoSugestao.relatorioEstrategiasPer1.get(0).size());
////////        rPer.ordenaLista1Crescente();
////////        rPer.ordenaEstLista1Decrescente();
////////        System.out.println("LISTA2: PERs = " + ResumoPeriodoSugestao.relatorioEstrategiasPer2.size()
////////                            + " | ESTs: " + ResumoPeriodoSugestao.relatorioEstrategiasPer2.get(0).size());
////////        rPer.ordenaLista2Crescente();
//////        
//////
//////        Relatorio.relatorioOrdensSugeridas = new ArrayList<>();
//////        int diaAtual = 0;
//////        //INICIA VARREDURA DOS PERIODOS
//////        for(int i = 0; i<ResumoPeriodoSugestao.relatorioEstrategiasPer1.size(); i++){
//////            listaEstrategia = new ArrayList<>();
//////            ResumoPeriodoSugestao rEst2;
//////            for(ResumoPeriodoSugestao rEst1 : ResumoPeriodoSugestao.relatorioEstrategiasPer1.get(i)){
//////                if(!verificaCondicaoPer1(rEst1))
//////                    continue;
//////                
//////                //SE NÃO EXISTE PERIODO 2 PARA ANALISE:
//////                if(ResumoPeriodoSugestao.relatorioEstrategiasPer2.isEmpty())
//////                    geraListaEstSugerida(rEst1, rEst1.getDataAplicada());
//////                //SE EXISTE PERIODO 2 PARA ANALISE:
//////                else{
//////                    rEst2 =  rPer.encontraPar2(rEst1, i);
//////                    if(!verificaCondicaoPer2(rEst2))
//////                        continue;
//////
//////                    geraListaEstSugerida(rEst2, rEst2.getDataAplicada());
//////                }
//////                // JA ATINGIU A QUANTIDADE DE EST PARA O DIA. VALOR PODE MUDAR
//////                if(listaEstrategia.size() == 5){
//////                    break;
//////                }
//////            }//FIM DAS ESTRATEGIAS DO PERIODO "i"
//////            //SE PRIMEIRO PERÍODO NÃO ENCONTROU ESTRATEGIAS, UTILIZA AS MELHORES
//////            if(diaAtual == 0 && listaEstrategia.isEmpty()){
//////                for(int k = 0; k <5; k++){
//////                    geraListaEstSugerida(ResumoPeriodoSugestao.relatorioEstrategiasPer1.get(i).get(k),
//////                                        ResumoPeriodoSugestao.relatorioEstrategiasPer1.get(i).get(k).getDataAplicada());
//////                }
//////            }
//////            
//////            //SE NAO ENCONTROU ESTRATEGIAS PARA O DIA, UTILIZA AS ULTIMAS DO DIA ANTERIOR
//////            if(listaEstrategia.isEmpty()){
//////                for(OrdemSimples item : Relatorio.relatorioOrdensSugeridas.get(Relatorio.relatorioOrdensSugeridas.size()-1)){
//////                    OrdemSimples ord = (OrdemSimples)Clone.deepClone(item);
//////                    Calendar c = Calendar.getInstance();
//////                        c.setTime(ord.getData().getTime()); 
//////                        c.add(Calendar.DATE, +1);
//////                    ord.setData(c);
//////                    salvaEstrategia(ord);
//////                }
//////            }
//////            
//////            Relatorio.relatorioOrdensSugeridas.add(listaEstrategia);
//////            diaAtual++;
//////        }
//////        //ORDENA A LISTA DE ESTRATEGIA SUGERIDA PELAS DATAS
//////        Relatorio.relatorioOrdensSugeridas.sort((c1, c2) -> c1.get(0).getData().compareTo(c2.get(0).getData()));
//////////        Collections.sort(Relatorio.relatorioOrdensSugeridas, Ordem.comparatorListaData);
//////    }
//////    
//////    /**
//////     * 
//////     * @param rEst resumo da estrategia para o periodo a ser lido e estrategias extraidas
//////     * @param data data FINAL a ser passada do resumo da estrategia da LISTA 2. A data final da lista 2
//////     * contem a ULTIMA data analisada (Periodo da lista 1 VS Periodo da lista 2).
//////     */
//////    private void geraListaEstSugerida(Resumos rEst, Calendar data){
//////        OrdemSimples est = new OrdemSimples();
//////        if(rEst.getDelta() != 0)
//////            est.setTemDelta(true);
//////        else
//////            est.setTemDelta(false);
//////        
//////        if(rEst.getOffset() != 0)
//////            est.setTemOffset(true);
//////        else
//////            est.setTemOffset(false);
//////        
//////        if(rEst.getLimOposto()!= 0)
//////            est.setTemLimOp(true);
//////        else
//////            est.setTemLimOp(false);
//////        
//////        if(rEst.getGain() != 0)
//////            est.setTemAlvo(true);
//////        else
//////            est.setTemAlvo(false);
//////        
//////        if(rEst.getLoss() != 0)
//////            est.setTemStop(true);
//////        else
//////            est.setTemStop(false);
//////        est.setTemTrStop(false);
//////        
//////        est.setMov(ConfigOrdens.getMov());
//////        est.setQtde(ConfigOrdens.getQtde());
//////        est.setDelta(rEst.getDelta());
//////        est.setOffset(rEst.getOffset());
//////        est.setLimOp(rEst.getLimOposto());
//////        est.setGain(rEst.getGain());
//////        est.setLoss(rEst.getLoss());
//////        est.setData(data);
//////        salvaEstrategia(est);
//////    }
//////    
//////    /**
//////     * clona uma estrategia e adiciona na lista
//////     * @param est Ordem a ser clonada
//////     */
//////    private void salvaEstrategia(Ordem est){
//////////////        Ordem estClone = new Ordem(est);
//////        OrdemSimples estClone = (OrdemSimples) Clone.deepClone(est);
//////        listaEstrategia.add(estClone);
//////    }
//////    
//////    /**
//////     * Faz as verificações se a estrategia da LISTA1 atinge os requisitos
//////     * @return TRUE se pode operar segundo os criterios
//////     * @param est é a estratégia do período analisado, da lista 1
//////     */
//////    private boolean verificaCondicaoPer1(ResumoPeriodoSugestao est1){
//////        if(est1 == null)
//////            return false;
//////
////////////        System.out.println("Est1: Data = " + est1.getData().get(Calendar.YEAR)
////////////                            + "/" + (est1.getData().get(Calendar.MONTH) + 1)
////////////                            + "/" + est1.getData().get(Calendar.DAY_OF_MONTH)
////////////                            + " | G = " + est1.getGain()
////////////                        + " | L = " + est1.getLoss() + " | SaldoAcum = " + est1.getSaldoAcum());
//////        return true;
//////    }
//////    
//////    /**
//////     * Faz as verificações se a estrategia da LISTA2 atinge os requisitos
//////     * @return TRUE se pode operar segundo os criterios
//////     * @param est é a estratégia do período analisado, da lista 2
//////     */
//////    private boolean verificaCondicaoPer2(ResumoPeriodoSugestao est2){
//////        if(est2 == null)
//////            return false;
//////        return (est2.getSaldoAcumulado()> 0);
//////    }
}
