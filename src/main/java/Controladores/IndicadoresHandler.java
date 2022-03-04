package Controladores;

import java.util.List;
import Resumos.ResumoDia;
import Indicadores.diario.AcoesIndicadores;

/**
 *
 * @author Felipe Mattos
 */
public class IndicadoresHandler{
    
    private static List<AcoesIndicadores> listaValidacoesDiario;

    public IndicadoresHandler(List<AcoesIndicadores> listaValidacoesDiario, List<AcoesIndicadores> listaValidacoesMinuto) {
        IndicadoresHandler.listaValidacoesDiario = listaValidacoesDiario;
    }
    
    /**
     *
     * @param dia atual sendo simulado
     * @param rDia
     * @return TRUE se pode operar no dia
     */
    public boolean verificaIndicadores(int dia, ResumoDia rDia) {
        rDia.setPodeOperarNoDia(true);
        IndicadoresHandler.listaValidacoesDiario.forEach(v -> {
            
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
