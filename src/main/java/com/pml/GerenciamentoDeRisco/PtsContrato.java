/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.GerenciamentoDeRisco;

import com.pml.Configuracoes.ConfigOrdens;
import com.pml.Ordens.OrdemGerRisco;
import com.pml.infra.Candle;
import com.pml.Resumos.ResumoDia;

/**
 *
 * @author Felipe Mattos
 */
public class PtsContrato implements ValidacaoGerRisco {

    @Override
    public OrdemGerRisco atualizaGerRisco(Candle candle, ResumoDia rDia) {
        if(!ConfigOrdens.isTemGerRisco_PtsCont())
            return null;

        //VARIAVEIS FUTURO
        int qtdeNegociadaFuturo = rDia.getQtdeNegociadaSerie() + Math.abs(rDia.getPos());
        double saldoDesejSerie = (ConfigOrdens.getGerRisco_PtsCont() * qtdeNegociadaFuturo) - rDia.getSaldoSerie();
        double prejPerm = ConfigOrdens.getGerRisco_PrejPerm() + rDia.getSaldoSerie();
        
        double gain = saldoDesejSerie / Math.abs(rDia.getPos());
        double loss = prejPerm / Math.abs(rDia.getPos());
        
        return new OrdemGerRisco(rDia, gain, loss);
    }
    
}
