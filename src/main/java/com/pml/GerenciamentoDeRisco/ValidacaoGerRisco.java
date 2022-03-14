/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.GerenciamentoDeRisco;

import com.pml.Ordens.OrdemGerRisco;
import com.pml.simulacao.Candle;
import com.pml.Resumos.ResumoDia;

/**
 *
 * @author Felipe Mattos
 */
public interface ValidacaoGerRisco {
    
    OrdemGerRisco atualizaGerRisco(Candle candle, ResumoDia rDia);
    
}
