package com.pml.Controladores;
import com.pml.Configuracoes.ConfigOrdens;
import com.pml.GerenciamentoDeRisco.ValidacaoGerRisco;
import com.pml.Ordens.LadoOrdem;
import com.pml.Ordens.OrdemGerRisco;
import com.pml.simulacao.Candle;
import com.pml.Ordens.OrdemSimples;
import java.util.List;
import com.pml.Resumos.Relatorios;
import com.pml.Resumos.ResumoDia;

public class GerenciamentoDeRisco {

    private static List<ValidacaoGerRisco> listaValidacoes;

    public GerenciamentoDeRisco(List<ValidacaoGerRisco> listaValidacoes) {
        GerenciamentoDeRisco.listaValidacoes = listaValidacoes;
    }

    public GerenciamentoDeRisco() {
    }
    
    /**
     * Testa a ordem Gerenciamento de Risco
     * @return TRUE se executou a ordem
     */
    public boolean testaSaidas(Candle candle, ResumoDia rDia) {
        if(rDia.getPos() == 0)
            return false;
        
        ControleOrdens controlador = new ControleOrdens();
        GerenciamentoDeRisco.listaValidacoes.forEach(v -> {
            OrdemGerRisco ord = v.atualizaGerRisco(candle, rDia);
            controlador.testaOrdem(candle, rDia, ord);
        });
        
        return rDia.isGerRisco();
    }
    
    /**
     * 
     * @param candle
     * @param rDia
     * @return retorna uma Ordem Ger. Risco baseado na configuração selecionada e no estado atual do dia.
     */
    public OrdemGerRisco atualizaOrdemGerRisco(Candle candle, ResumoDia rDia){
        for(ValidacaoGerRisco validacao : listaValidacoes){
            OrdemGerRisco ord = validacao.atualizaGerRisco(candle, rDia);
            if(ord != null)
                return ord;
        }
        return null;
    }
    
    
    /**
     * Verifica se é swingtrade, se tem posição aberta, encerra a lista posição do dia.
     * Encerra a ordens se estiver aberta.
     * Salva o dia no Relatorio Diario
     * Salva a ultima posição em aberto SE o dia teve alguma ordem executada
     * @param candle candle atual sendo lido
     * @param swingTrade true se for swingTrade, o que evita fechar a posição no dia
     * @param rDia
     * @return TRUE se realiza saida pelo fechamento. Falso se era swingTrade ou pos = 0
     * @throws OutOfMemoryError
     */
    public boolean encerraDia(Candle candle, boolean swingTrade, ResumoDia rDia){
        if(rDia.isGravado())
            return false;
        
        if (rDia.isOrdemExecutada())
            ResumoDia.setUltimaPos(rDia.getPos());
       
        boolean tag = false;
        
        if (!rDia.isOrdemExecutada()) {
            OrdemSimples ord = new OrdemSimples();
            ord.setData(candle.getData());
            ord.setNome("");
            ord.setQtde(0);
            rDia.setOrdemExecutada(true);
            Relatorios.gravaOrdemExecutada(ord);
        }
        
        //INICIA VERIFICAÇÃO DE  SAIDA NO FECHAMENTO
        boolean statusGerRisco = rDia.isGerRisco();
        if (!swingTrade && rDia.getPos() != 0) {
            OrdemSimples ord = new OrdemSimples();
            ord.setTemLimOp(false);
            ord.setTemOffset(true);
            ord.setData(candle.getData());
            ord.setDataEntrada(candle.getData());
            if (rDia.getPos() > 0) {
                ord.setLadoOrdem(LadoOrdem.VENDA);
                ord.setNome("Venda Fech");
            } else {
                ord.setLadoOrdem(LadoOrdem.COMPRA);
                ord.setNome("Compra Fech");
            }
            ord.setQtde(Math.abs(rDia.getPos()));
            ord.setOffset(candle.getFechamento() - rDia.getAbertura());
            ord.configuraLinhasEntradaESaidas(rDia.getAbertura());
            rDia.setGerRisco(false);
            new ControleOrdens().testaOrdem(candle, rDia, ord);
            rDia.setGerRisco(statusGerRisco);
            
            rDia.setSaidas(rDia.getSaidas() - ord.getQtde());
            rDia.setPosValMed(0);
            rDia.setPosValTotal(0);
            
            candle.atualizaCandleResultados(rDia);
            tag = true;
        }
        if (rDia.getQtdeNegociada() != 0) {
            rDia.setSaldoContrato(rDia.getSaldo()/rDia.getQtdeNegociada());
        }
        if (rDia.getQtdeNegociadaSerie() != 0) {
            rDia.setSaldoContratoSerie(rDia.getSaldoSerie()/rDia.getQtdeNegociadaSerie());
        }
        
        Relatorios.gravaDia(rDia);
        return tag;
    }

    public void verificaReposicionaPelaAbertura(ResumoDia rDia, Candle candle) {
        if(!rDia.isPodeReposicionarPelaAbertura())
            return;
        
        //ENTROU UMA VEZ NO METODO, NAO PODE ENTRAR NOVAMENTE:
        rDia.setPodeReposicionarPelaAbertura(false);
        
        if(!ConfigOrdens.isReposicionaPelaAbertura())
            return;
        
        if(ResumoDia.getUltimaPos() == 0)
            return;
        
        OrdemSimples reposiciona = new OrdemSimples();
        reposiciona.setTemOffset(false);
        reposiciona.setOffset(0);
        if(ResumoDia.getUltimaPos() > 0){
            reposiciona.setLadoOrdem(LadoOrdem.COMPRA);
            reposiciona.setNome("Reposiciona: Compra");
        }else{
            reposiciona.setLadoOrdem(LadoOrdem.VENDA);
            reposiciona.setNome("Reposiciona: Venda");
        }
        reposiciona.setQtde(Math.abs(ResumoDia.getUltimaPos()));
        reposiciona.configuraLinhasEntradaESaidas(candle.getAbertura());
        new ControleOrdens().testaOrdem(candle, rDia, reposiciona);
    }
    
}
