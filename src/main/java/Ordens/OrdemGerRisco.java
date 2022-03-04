/**
 * Representa uma ordem gerada pelo Gerenciamento de Risco.
 * Não possui Gain nem Loss. Os valores passados por esses parametros são para
 * calcular a linha de saída (compra ou venda)
 */
package Ordens;

import Configuracoes.ConfigOrdens;
import simulacao.Arredondamento;
import simulacao.Candle;
import Resumos.ResumoDia;

public class OrdemGerRisco extends OrdemOCO{

    private boolean saldoMin;


    /**
     * Instancia uma nova ordem baseada no gereciamento de risco
     * Valores de Saida e Stop serão arredondados de acordo com o Ativo selecionado
     * @param rDia
     * @param gain Calculado no gerenciamento de risco, baseado na Pos, no seu valor
     * e em quantos Pts/Cont ou em quantos PtsSaldo se deseja. 
     * @param loss Idem gain, porém calculado para o PrejPerm 
     */
   
    public OrdemGerRisco(ResumoDia rDia, double gain, double loss) {
        super.gerenciamentoDeRisco = true;
        super.temTrStop = false;
        super.temAlvo = ConfigOrdens.isTemGerRisco_Saldo() || ConfigOrdens.isTemGerRisco_PtsCont();
        super.gain = gain;
        super.temStop = ConfigOrdens.isTemGerRisco_PrejPerm();
        super.loss = loss;
        super.temOffset = false;
        super.offset = 0;
        super.saldoDesej = ConfigOrdens.getGerRisco_SaldoDesej();
        super.ptsContDesej = ConfigOrdens.getGerRisco_PtsCont();
        super.prejPerm = ConfigOrdens.getGerRisco_PrejPerm();
        super.qtde = Math.abs(rDia.getPos());
        
        super.podeSair = false;
        super.iniciada = true;

        this.saldoMin = false;
        
        //VERIFICA SE DEVE ZERAR PELO PONTO DE COBERTURA
        if(ConfigOrdens.isTemGerRisco_SaldoMinimo()){
            //JA ESTAVA USANDO O SALDO MINIMO NO DIA QUE FECHOU OU PRECISA USAR A PARTIR DOS RESULTADOS
            if(rDia.getSaldoSerie() < -Math.abs(ConfigOrdens.getGerRisco_ZeraSerie())){
                super.saldoDesej = 0;
                super.gain = 0;
                this.saldoMin = true;
            }
        }
        
        // Pos aberta -> comprado
        if (rDia.getPos() > 0) {
            super.ladoOrdem = LadoOrdem.COMPRA;
            super.comprado = true;
            super.linhaCompra = rDia.getPosValMed();
            if(super.temAlvo)
                super.linhaVenda = new Arredondamento().arredondaCimaMultiplo(rDia.getPosValMed() + super.gain, this);
            if (super.temStop)
                super.linhaStop = new Arredondamento().arredondaBaixoMultiplo(rDia.getPosValMed() - super.loss, this);
        }
         
        // Pos aberta -> vendido
        if (rDia.getPos() < 0) {
            super.ladoOrdem = LadoOrdem.VENDA;
            super.vendido = true;
            super.linhaVenda = rDia.getPosValMed();
            
            if(super.temAlvo)
                super.linhaCompra = new Arredondamento().arredondaBaixoMultiplo(rDia.getPosValMed() - super.gain, this);
            if (super.temStop)
                super.linhaStop = new Arredondamento().arredondaCimaMultiplo(rDia.getPosValMed() + super.loss, this);
        }
        
        rDia.setGerRisco_Saldo(this.saldoDesej);
        rDia.setGerRisco_PrejPerm(this.prejPerm);
    }

    @Override
    protected boolean testaGain(Candle candle, ResumoDia rDia) {
        if(!super.testaGain(candle, rDia))
            return false;
        
        if(super.ladoOrdem == LadoOrdem.COMPRA)
            super.nome = "G.R.: Venda Gain";

        if(super.ladoOrdem == LadoOrdem.VENDA)
            super.nome = "G.R.: Compra Gain";

        super.data = candle.getData();
        super.dataExecucao = candle.getData();

        if(saldoMin)
            super.nome+= " Mínimo";
        
        rDia.setGerRisco(true);
        
        return true;
    }

    @Override
    protected boolean testaStop(Candle candle, ResumoDia rDia) {
        if(!super.testaStop(candle, rDia))
            return false;
        
        if(super.ladoOrdem == LadoOrdem.COMPRA)
            super.nome = "G.R.: Venda Stop";

        if(super.ladoOrdem == LadoOrdem.VENDA)
            super.nome = "G.R.: Compra Stop";
        
        super.data = candle.getData();
        super.dataExecucao = candle.getData();
        
        rDia.setGerRisco(true);
        
        return true;
    }

    @Override
    boolean verificaSePodeSair(Candle candle, ResumoDia rDia) {
        //verificacoes da movimentacao dos precos (valor de entrada -> valor de saida)
        //precisa verificar todas as possibilidades contra movimento (preco ter atingido
        //o valor de saida antes de ter feito entrada. Fechamento garante que tenha passado novamente
        //no mesmo candle
        
        if(rDia.isGerRisco() || rDia.getPos() == 0)
            return false;
        
        if(super.podeSair)
            return true;
        
        switch (super.ladoOrdem){
            case COMPRA:
                if(super.temAlvo)
                    if(candle.getAbertura() > rDia.getUltimoValorExecutado())
                        if(candle.getFechamento() <= super.linhaVenda)
                            return false;
                            
                if(super.temStop)
                    if(candle.getAbertura() < rDia.getUltimoValorExecutado())
                        if(candle.getFechamento() >= super.linhaStop)
                            return false;
                
                if(super.temTrStop && super.trIniciado)
                    if(candle.getAbertura() < rDia.getUltimoValorExecutado())
                        if(candle.getFechamento() >= super.linhaTrStop)
                            return false;
                break;
                
            case VENDA:
                if(super.temAlvo)
                    if(candle.getAbertura() < rDia.getUltimoValorExecutado())
                        if(candle.getFechamento() >= super.linhaCompra)
                            return false;
                if(super.temStop)
                     if(candle.getAbertura() > rDia.getUltimoValorExecutado())
                        if(candle.getFechamento() <= super.linhaStop)
                            return false;
                if(super.temTrStop && super.trIniciado)
                    if(candle.getAbertura() > rDia.getUltimoValorExecutado())
                        if(candle.getFechamento() <= super.linhaTrStop)
                            return false;
                break;
        }
        super.podeSair = true;
        return true;
    }
    
    public void setPodeSair(boolean podeSair){
        super.podeSair = podeSair;
    }
    
    public void setSaldoMin(boolean b){
        this.saldoMin = b;
    }
    
    
    @Override
    public String toString(){
        return super.toString() + " | SaldoDesej = "    + super.saldoDesej
                                + " | PtsDesejCont = "  + super.ptsContDesej
                                + " | PrejPerm = "      + super.prejPerm;
    }
}
