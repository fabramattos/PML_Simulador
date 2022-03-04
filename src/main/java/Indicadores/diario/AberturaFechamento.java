package Indicadores.diario;

import Configuracoes.ConfigIndicadores;
import simulacao.Candle;

public class AberturaFechamento implements AcoesIndicadores{
    
     /**
     *
     * @param dia Dia atual sendo simulado
     * @return TRUE se pode operar no dia
     */
    @Override
    public boolean verificaIndicador(int dia) throws IndexOutOfBoundsException{
        if (!ConfigIndicadores.isTemAbeFech())
            return true;

        double dif = Candle.getListaCandleDiario().get(dia).getAbertura() - Candle.getListaCandleDiario().get(dia - 1).getFechamento();
        switch (ConfigIndicadores.getAbeFechOpe()) {
            case "<":
                return dif < ConfigIndicadores.getAbeFechVal1();
            case ">":
                return dif > ConfigIndicadores.getAbeFechVal1();
            case "=":
                return dif == ConfigIndicadores.getAbeFechVal1();
            default:
                double valorMaior = Double.max(ConfigIndicadores.getAbeFechVal1(), ConfigIndicadores.getAbeFechVal2());
                double valorMenor = Double.min(ConfigIndicadores.getAbeFechVal1(), ConfigIndicadores.getAbeFechVal2());
                return valorMaior >= dif && dif >= valorMenor;
        }
    }
}
