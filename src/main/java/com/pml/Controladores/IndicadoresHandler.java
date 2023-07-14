package com.pml.Controladores;

import java.util.List;
import com.pml.Resumos.ResumoDia;
import com.pml.Indicadores.diario.AcoesIndicadores;

/**
 *
 * @author Felipe Mattos
 */
public class IndicadoresHandler{
    
    private static List<AcoesIndicadores> listaValidacoesIndicadores;

    public IndicadoresHandler(List<AcoesIndicadores> listaValidacoes) {
        IndicadoresHandler.listaValidacoesIndicadores = listaValidacoes;
    }
    
    /**
     *
     * @param dia atual sendo simulado
     * @param rDia
     * @return TRUE se pode operar no dia
     */
    public boolean verificaIndicadores(int dia, ResumoDia rDia) {
        rDia.setPodeOperarNoDia(true);
        IndicadoresHandler.listaValidacoesIndicadores.forEach(v -> {
            
            try{
                if(!v.verificaIndicador(dia))
                rDia.setPodeOperarNoDia(false);
            }catch(IndexOutOfBoundsException e){
                rDia.setPodeOperarNoDia(false);
            }
        });
        return rDia.isPodeOperarNoDia();
    }
}
