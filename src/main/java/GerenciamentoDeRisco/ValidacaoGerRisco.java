/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package GerenciamentoDeRisco;

import Ordens.OrdemGerRisco;
import simulacao.Candle;
import Resumos.ResumoDia;

/**
 *
 * @author Felipe Mattos
 */
public interface ValidacaoGerRisco {
    
    OrdemGerRisco atualizaGerRisco(Candle candle, ResumoDia rDia);
    
}
