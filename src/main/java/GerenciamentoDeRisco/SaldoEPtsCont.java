/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerenciamentoDeRisco;

import Configuracoes.ConfigOrdens;
import Ordens.OrdemGerRisco;
import simulacao.Candle;
import Resumos.ResumoDia;

/**
 *
 * @author Felipe Mattos
 */
public class SaldoEPtsCont implements ValidacaoGerRisco {

    @Override
    public OrdemGerRisco atualizaGerRisco(Candle candle, ResumoDia rDia) {
        if(!ConfigOrdens.isTemGerRisco_Saldo() || !ConfigOrdens.isTemGerRisco_PtsCont())
            return null;
        
        //VARIAVEIS FUTURO
        int qtdeNegociadaFuturo = rDia.getQtdeNegociadaSerie() + Math.abs(rDia.getPos());
        double saldoDesejSerie_PtsCont = (ConfigOrdens.getGerRisco_PtsCont() * qtdeNegociadaFuturo) - rDia.getSaldoSerie();
        double gain1 = saldoDesejSerie_PtsCont / Math.abs(rDia.getPos());
        
        double saldoDesejSerie_SaldoDesej = Math.max(ConfigOrdens.getGerRisco_SaldoDesej_Original(), ConfigOrdens.getGerRisco_SaldoDesej() - rDia.getSaldoSerie());
        double gain2 = saldoDesejSerie_SaldoDesej / Math.abs(rDia.getPos());
        
        double prejPerm = ConfigOrdens.getGerRisco_PrejPerm() + rDia.getSaldoSerie();
        
        double gain = Double.max(gain1, gain2);
        double loss = prejPerm / Math.abs(rDia.getPos());
        
        return new OrdemGerRisco(rDia, gain, loss);
    }
    
}
