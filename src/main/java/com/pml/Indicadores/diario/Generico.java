package com.pml.Indicadores.diario;

import com.pml.Configuracoes.ConfigIndicadores;
import com.pml.infra.Candle;

/**
 *
 * @author Felipe Mattos
 */
public class Generico implements AcoesIndicadores{
    
    /**
     *
     * @param dia Dia atual sendo simulado
     * @return TRUE se pode operar no dia
     */
    @Override
    public boolean verificaIndicador(int dia) throws IndexOutOfBoundsException{
        if (!ConfigIndicadores.isTemGen())
            return true;
    
        switch (ConfigIndicadores.getGenOpe()) {
            case "<":
                return Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra() < ConfigIndicadores.getGenVal1();
            case ">":
                return Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra() > ConfigIndicadores.getGenVal1();
            case "=":
                return Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra() == ConfigIndicadores.getGenVal1();
            default:
                double valorMaior = Double.max(ConfigIndicadores.getGenVal1(), ConfigIndicadores.getGenVal2());
                double valorMenor = Double.min(ConfigIndicadores.getGenVal1(), ConfigIndicadores.getGenVal2());
                return valorMaior >= Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra() && (Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra() >= valorMenor);
        }
    }
    
}
