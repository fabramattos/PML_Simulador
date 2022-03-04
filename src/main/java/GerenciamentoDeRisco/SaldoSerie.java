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
public class SaldoSerie implements ValidacaoGerRisco {
    
    @Override
    public OrdemGerRisco atualizaGerRisco(Candle candle, ResumoDia rDia) {
        if(ConfigOrdens.isTemGerRisco_PtsCont())
            return null;
        
        if(!ConfigOrdens.isTemGerRisco_Saldo() && !ConfigOrdens.isTemGerRisco_PrejPerm())
            return null;

        //PREPARA ESTRATEGIA
        double saldoDesejado = Math.max(ConfigOrdens.getGerRisco_SaldoDesej_Original(), ConfigOrdens.getGerRisco_SaldoDesej() - rDia.getSaldoSerie());
        double gain = saldoDesejado/ Math.abs(rDia.getPos());
        double prejPerm = ConfigOrdens.getGerRisco_PrejPerm() + rDia.getSaldoSerie();
        double loss = prejPerm / Math.abs(rDia.getPos());
        
        return new OrdemGerRisco(rDia, gain, loss);
    }
}
