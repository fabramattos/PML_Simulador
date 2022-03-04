package Controladores;

import simulacao.Candle;
import Ordens.Ordem;
import Resumos.ResumoDia;

public class ControleOrdens {

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
        
        rDia.ordenaListaPelasDistanciaDaAberturaDoCandle(candle);
        
        while(true){
            boolean executouAlgo = false;
            for(Ordem ordem : rDia.getListaOrdensDia()){
                if(ordem.verificaExecucao(candle, rDia)){
                    executouAlgo = true;
                    break;
                }
            }
            if(executouAlgo)
                rDia.ordenaListaPelasDistanciaDaAberturaDoCandle(candle);
            else
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
    
}
