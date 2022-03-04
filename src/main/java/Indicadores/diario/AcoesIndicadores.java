package Indicadores.diario;

public interface AcoesIndicadores {
    
    /**
     * 
     * @param momento representa quando a análise será feita. Pode ser int que representa um DIA
     * na lista de candle diario, ou um int que representa um MINUTO na lista de candle minuto.
     * @return 
     */
    boolean verificaIndicador(int momento) throws IndexOutOfBoundsException;
}
