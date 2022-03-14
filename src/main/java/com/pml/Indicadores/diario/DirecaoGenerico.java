package com.pml.Indicadores.diario;

import com.pml.Configuracoes.ConfigIndicadores;
import com.pml.simulacao.Candle;

public class DirecaoGenerico implements AcoesIndicadores{
    
     /**
     *
     * @param dia Dia atual sendo simulado
     * @return TRUE se pode operar no dia
     */
    @Override
    public boolean verificaIndicador(int dia) throws IndexOutOfBoundsException {
        if (!ConfigIndicadores.isTemDirInd())
            return true;

        double dif = Candle.getListaCandleDiario().get(dia - 2).getIndicadorExtra() - Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra();
        switch (ConfigIndicadores.getDirOpe()) {
            case "<":
                return dif < ConfigIndicadores.getDirVal1();
            case ">":
                return dif > ConfigIndicadores.getDirVal1();
            case "=":
                return dif == ConfigIndicadores.getDirVal1();
            default:
                double valorMaior = Double.max(ConfigIndicadores.getDirVal1(), ConfigIndicadores.getDirVal2());
                double valorMenor = Double.min(ConfigIndicadores.getDirVal1(), ConfigIndicadores.getDirVal2());
                return valorMaior >= dif && dif >= valorMenor;
        }
    }
}
