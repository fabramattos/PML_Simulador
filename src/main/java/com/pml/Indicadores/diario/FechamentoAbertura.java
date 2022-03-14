package com.pml.Indicadores.diario;

import com.pml.Configuracoes.ConfigIndicadores;
import com.pml.simulacao.Candle;

public class FechamentoAbertura implements AcoesIndicadores{
    
    /**
     * @param dia Dia atual sendo simulado
     * @return TRUE se pode operar no dia
     */
    @Override
    public boolean verificaIndicador(int dia) throws IndexOutOfBoundsException {
        if (!ConfigIndicadores.isTemFechAbe())
            return true;
        
        double dif = Candle.getListaCandleDiario().get(dia - 1).getFechamento() - Candle.getListaCandleDiario().get(dia - 1).getAbertura();
        switch (ConfigIndicadores.getFechAbeOpe()) {
            case "<":
                return dif < ConfigIndicadores.getFechAbeVal1();
            case ">":
                return dif > ConfigIndicadores.getFechAbeVal1();
            case "=":
                return dif == ConfigIndicadores.getFechAbeVal1();
            default:
                double valorMaior = Double.max(ConfigIndicadores.getFechAbeVal1(), ConfigIndicadores.getFechAbeVal2());
                double valorMenor = Double.min(ConfigIndicadores.getFechAbeVal1(), ConfigIndicadores.getFechAbeVal2());
                return valorMaior >= dif && dif >= valorMenor;
        }
    }
}
