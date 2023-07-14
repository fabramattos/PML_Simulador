package com.pml.Controladores;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.infra.Candle;
import com.pml.Ordens.Ordem;
import com.pml.Ordens.OrdemGerRisco;
import com.pml.Resumos.ResumoDia;

public class ControleOrdens {

    public static boolean ordenouListaDeOrdens = false;
    
    /**
     * Testa Ordem. Pode ser Simples, OCO ou Ger. Risco
     */
    public void testaOrdem(Candle candle, ResumoDia rDia, Ordem ord) {
        if(ord != null)
            ord.verificaExecucao(candle, rDia);
    }
    
    
    /**
     * Verifica o REPOSICIONAMENTO PELA ABERTURA, depois testa todas as ordens na ListaOrdensDia.
     * As ordens são ordenadas de acordo com a distancia da linha de execução (entrada / saidas oco)
     * mais próxima da a abertura do candle.
     * A cada execução, reordena as ordens (simples, OCO, Risco) para a aproxima verificação de execução.
     * @param candle
     * @param rDia 
     */
    public void testaListaOrdens(Candle candle, ResumoDia rDia) {
        new GerenciamentoDeRisco().verificaReposicionaPelaAbertura(rDia, candle);
        
        if(!verificaSeTemOrdemGerRisco(rDia))
            rDia.adicionaOrdemNaLista(new GerenciamentoDeRisco().atualizaOrdemGerRisco(candle, rDia));
        
        if(!ordenouListaDeOrdens)
            rDia.ordenaListaPelasDistanciaDaUltimaExecucao(candle);
        
        while(true){
            boolean executouAlgo = false;
            for(Ordem ordem : rDia.getListaOrdensDia()){
                if(ordem.verificaExecucao(candle, rDia)){
                    executouAlgo = true;
                    break;
                }
            }
            if(executouAlgo){
                System.out.println("========== LISTA NÃO ORDENADA =========");
                rDia.printListaOrdensParaODia();
                verificaValoresEComparaComGerRisco(candle, rDia);
                rDia.ordenaListaPelasDistanciaDaUltimaExecucao(candle);
                System.out.println("========== LISTA ORDENADA =========");
                rDia.printListaOrdensParaODia();
                System.out.println("");
            
            }else
                return;
        }
    }
    
    
    public void testaListaOrdens_InverteNaDirecaoDoMovimento(Candle candle, ResumoDia rDia) {
        new GerenciamentoDeRisco().verificaReposicionaPelaAbertura(rDia, candle);
        
        if(!verificaSeTemOrdemGerRisco(rDia))
            rDia.adicionaOrdemNaLista(new GerenciamentoDeRisco().atualizaOrdemGerRisco(candle, rDia));
        
        if(!ordenouListaDeOrdens){
            rDia.ordenaListaPelasDistanciaDaUltimaExecucao_E_AatualizaParaInversao(candle);
        }
       
        while(true){
            boolean executouAlgo = false;
            for(Ordem ordem : rDia.getListaOrdensDia()){
                if(ordem.verificaExecucao(candle, rDia)){
                    executouAlgo = true;
                    break;
                }
            }
            if(executouAlgo){
                System.out.println("========== LISTA NÃO ORDENADA =========");
                rDia.printListaOrdensParaODia();
                verificaValoresEComparaComGerRisco(candle, rDia);
                rDia.ordenaListaPelasDistanciaDaUltimaExecucao_E_AatualizaParaInversao(candle);
                System.out.println("========== LISTA ORDENADA =========");
                rDia.printListaOrdensParaODia();
                System.out.println("");
            
            }else
                return;
        }
    }
    
    
    private boolean verificaSeTemOrdemGerRisco(ResumoDia rDia) {
        for(Ordem ordem : rDia.getListaOrdensDia()){
            if(ordem.isGerenciamentoDeRisco())
                return true;
        }
        return false;
    }
    
    /**
     * se estiver com resultado acima do ger. risco, apaga a ultima ordem executada, restaura dados
     * e força o ger. risco a ser utilizado
     */
    private void verificaValoresEComparaComGerRisco(Candle candle, ResumoDia rDia){
        boolean flag = false;
        while(true){
            if(ConfigOrdens.isTemGerRisco_PtsCont() && ConfigOrdens.isTemGerRisco_Saldo())
                if(rDia.getSaldoContratoSerie() >= ConfigOrdens.getGerRisco_PtsCont() && rDia.getSaldoSerie() >= ConfigOrdens.getGerRisco_SaldoDesej()){
                    flag = true;
                    break;
                }

            if(ConfigOrdens.isTemGerRisco_PtsCont() && rDia.getSaldoContrato() >= ConfigOrdens.getGerRisco_PtsCont()){
                flag = true;
                break;
            }
            
            if(ConfigOrdens.isTemGerRisco_Saldo() && rDia.getSaldo() >= ConfigOrdens.getGerRisco_SaldoDesej())
                flag = true;
            break;
        }
         
        if(flag){
            rDia.restauraValoresDiariosAnteriores();
            OrdemGerRisco ordRisco= new GerenciamentoDeRisco().atualizaOrdemGerRisco(candle, rDia);
            ordRisco.setPodeSair(true);
            testaOrdem(candle, rDia, ordRisco);
        }
    }
    
}
