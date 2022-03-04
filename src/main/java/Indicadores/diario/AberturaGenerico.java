package Indicadores.diario;

import Configuracoes.ConfigIndicadores;
import simulacao.Candle;

public class AberturaGenerico implements AcoesIndicadores{
    
    /**
     *
     * @param dia Dia atual sendo simulado
     * @return TRUE se pode operar no dia
     */
    @Override
    public boolean verificaIndicador(int dia) throws IndexOutOfBoundsException {
        if (!ConfigIndicadores.isTemAbeInd())
            return true;
        
        double dif = Candle.getListaCandleDiario().get(dia).getAbertura() - Candle.getListaCandleDiario().get(dia - 1).getIndicadorExtra();
        switch (ConfigIndicadores.getAbeIndOpe()) {
            case "<":
                return dif < ConfigIndicadores.getAbeAbeIndVal1();
            case ">":
                return dif > ConfigIndicadores.getAbeAbeIndVal1();
            case "=":
                return dif == ConfigIndicadores.getAbeAbeIndVal1();
            default:
                double valorMaior = Double.max(ConfigIndicadores.getAbeAbeIndVal1(), ConfigIndicadores.getAbeAbeIndVal2());
                double valorMenor = Double.min(ConfigIndicadores.getAbeAbeIndVal1(), ConfigIndicadores.getAbeAbeIndVal2());
                return valorMaior >= dif && dif >= valorMenor;
        }
    }
    
}
