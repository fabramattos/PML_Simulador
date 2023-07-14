package com.pml.Controladores;

import com.pml.Configuracoes.ConfigBase;
import com.pml.InterfaceGrafica.IG;
import java.time.LocalDateTime;
import com.pml.infra.Candle;
import com.pml.Resumos.Relatorios;
import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

public class ControleTempo {
    private static boolean podeOperar = false;
    private long startTime;
    private List<Long> listaTempos;
    private int contadorTempoNecessario;
    
    
    /**
     * Inicia timmer.
     */
    public ControleTempo(){
        this.listaTempos = new ArrayList();
        this.contadorTempoNecessario = 0;
    }

    
    /**
     * Verifica se pode operar (se TRUE após iniciado novo dia, pode operar independente
     * das configurações de horario inicial
     * @return 
     */
    public static boolean isPodeOperar() {
        return podeOperar;
    }

    /**
     * determina se pode operar (se FALSE --> Verifica o horario inicial,
     * se TRUE --> permite operar sem verificar horario inicial)
     * @param podeOperar 
     */
    public static void setPodeOperar(boolean podeOperar) {
        ControleTempo.podeOperar = podeOperar;
    }
    
    /**
     * Reinicia o Timmer
     */
    public void disparaTimmer(){
        startTime = System.nanoTime();
    }
    
    
    /**
     * retorna um vetor com o tempo calculado pelo timmer, sendo: [dias, horas, min, seg],
     * sendo os limites: [inf, 23, 59, 59] 
     * @return 
     */
    public int[] getTempoDecorrido(){
        long tempoDecorrido = System.nanoTime() - startTime;
        
        long segundos, minutos, hora, dia;
        int seg, min, h, d;
    	
    	segundos = (tempoDecorrido/1000000000);
    	minutos = segundos/60;
    	hora = minutos / 60;
    	dia = hora / 24;
    	
    	seg = (int) segundos % 60;
    	min = (int) minutos % 60;
    	h = (int) hora % 24;
    	d = (int) dia;
        
        int resultado[] = {d,h,min,seg};
        return resultado;
        
    }
    
    /**
     * retorna uma String com o tempo decorrido
     * sendo os limites: [inf, 23, 59, 59] 
     * @return 
     */
    public String getTempoDecorridoString(){
        int[] tempoDecorrido = getTempoDecorrido();
        
        String resultado = "";
        
        if(tempoDecorrido[0] > 0)
            resultado += String.valueOf(tempoDecorrido[0]) + " dias, ";
        
        resultado += String.valueOf(tempoDecorrido[1]) + "h"
                    + String.valueOf(tempoDecorrido[2]) + "m"
                    + String.valueOf(tempoDecorrido[3]) + "s";
        
        return resultado;
        
    }

    /**
     * @param candle
     * @return TRUE se horario Inicial foi atingido.
     * TRUE permite operar
     */
    public boolean verificaHorarioInicial(Candle candle) {
        if (podeOperar)
            return true;
        
        if (!ConfigBase.isTemHorarioIni()) {
            podeOperar = true;
            return true;
        }
        
        if (candle.getData().getHour() > ConfigBase.getHoraInicial()) {
            podeOperar = true;
            return true;
        }
        
        if(candle.getData().getHour() == ConfigBase.getHoraInicial() && candle.getData().getMinute() >= ConfigBase.getMinutoInicial())
            podeOperar = true;
        return podeOperar;
    }

    
    public int buscaDiaInicial(int minIni) {
        int i = Candle.getListaCandleDiario().indexOf(Candle.getListaCandleMinuto().get(minIni));
        if (i == -1){
            IG.textoAdd("ERRO:\n");
            IG.textoAdd("Não sincronizou minuto e dia inicial \n");
        }
        return i;
    }

    /**
     * Verifica o horario do candle instanciado e compara com a configuração da
     * interface gráfica
     * @param candle
     * @return TRUE se horario limite foi atingido.
     * FALSE permite operar
     */
    public boolean verificaHorarioFinal(Candle candle) {
        if (!ConfigBase.isTemHorarioFin()) {
            return false;
        }
        if (candle.getData().getHour() < ConfigBase.getHoraFinal()) {
            return false;
        }
        return candle.getData().getHour() > ConfigBase.getHoraFinal() 
        || (candle.getData().getHour() == ConfigBase.getHoraFinal() && candle.getData().getMinute() >= ConfigBase.getMinutoFinal());
    }
    
    /**
     * Verifica o horario do candle instanciado e compara com a configuração da
     * interface gráfica
     * @param candle
     * @return FALSE se horario limite foi atingido.
     * TRUE permite operar
     */
    public boolean verificaHorarioLimEntrada(Candle candle) {
        if (!ConfigBase.isTemHorarioLimEntrada()) {
            return true;
        }
        
        if (candle.getData().getHour() < ConfigBase.getLimEntradaHora()) {
            return true;
        }
        
        if (candle.getData().getHour() > ConfigBase.getLimEntradaHora())
            return false;
        
        return candle.getData().getMinute() <= ConfigBase.getLimEntradaMinuto();
    }

    /**
     * Encontra o minuto inicial (primeiro dado na planilha de candles) para iniciar as simulações, baseado
     * no dia/mes/ano informado
     * @param data
     * @param dia
     * @param mes
     * @param ano
     * @return a posição na lista de candles onde inicia a simulação
     */
    public int buscaMinutoInicial(boolean data, int dia, int mes, int ano) {
        if (!data)
            return 0;

        LocalDateTime dataBuscada = LocalDateTime.of(ano, mes, dia, 0, 0);

        for (int i = 0; i < Candle.getListaCandleMinuto().size(); i++) {
            if (Candle.getListaCandleMinuto().get(i).getData().compareTo(dataBuscada) >=0 )
                return i;
        }
        return 0;
    }
    
    /**
     * @param diadD1
     * @param diaD0
     * @return  TRUE se ultimo candle do dia
     */
    public boolean verificaSeEhUltimoCandleDoDia(Candle proximo, Candle atual) {
        if(proximo == null)
            return true;
        
        return (proximo.getData().getYear() > atual.getData().getYear()) || (proximo.getData().getDayOfYear() > atual.getData().getDayOfYear());
    }

     /**
     * @param diadD1
     * @param diaD0
     * @return  TRUE se passou mes
     */
    public boolean verificaPassagemMes(LocalDateTime diadD1, LocalDateTime diaD0) {
        return (diadD1.getYear() > diaD0.getYear()) || (diadD1.getMonthValue()> diaD0.getMonthValue());
    }
    
     /**
     * @param diadD1
     * @param diaD0
     * @return  TRUE se passou ano
     */
    public boolean verificaPassagemAno(LocalDateTime diadD1, LocalDateTime diaD0) {
        return diadD1.getYear() > diaD0.getYear();
    }

    /**
     * Encontra o minuto final (ultimo dado correspondente na planilha de candles)
     * para iniciar as simulações, baseado no dia/mes/ano informado
     * @param offsetDiaFinal
     * @param data
     * @param dia
     * @param mes
     * @param ano
     * @return
     */
    public int buscaMinutoFinal(boolean data, int dia, int mes, int ano) {
        int posfinal = Candle.getListaCandleMinuto().size() - 1;
        
        if (!data) 
             return posfinal;
        
        LocalDateTime dataBuscada = LocalDateTime.of(ano, mes, dia, 0, 0);
            
        for (int i = posfinal; i > 0; i--) {
            if (Candle.getListaCandleMinuto().get(i).getData().compareTo(dataBuscada) <=0 )
                return i;
        }
        return posfinal;
    }
    

    /**
     * verifica se o minuto do candle atual é multiplo do valor informado
     * @param multiplo
     * @return TRUE pode se formou o candle desejado
     */
    public boolean verificaMinutoMultiplo(Candle candle, int multiplo) {
        //PRIMEIRO CANDLE DA LISTA
        if (multiplo == 1)
            return true;
        
        // OS CANDLES COMEÇAM EM 0 MIN, LOGO, COMO EXEMPLO, NOS DADOS DO CANDLE DE 9 MINUTOS TEMOS CONCLUIDO O CANDLE DE 10 MIN
        int minuto = candle.getData().getMinute();
        minuto++;
        
        return (float) minuto % (float) multiplo == 0;
    }
    
    public boolean verificaFimDasOperacoesNoDia(int i) {
        return (i == Candle.getListaCandleMinuto().size()-1
            || verificaHorarioFinal(Candle.getListaCandleMinuto().get(i)) 
            || verificaSeEhUltimoCandleDoDia(Candle.getListaCandleMinuto().get(i+1), Candle.getListaCandleMinuto().get(i)));
    }
    

    public boolean verificaFimDasOperacoesNoDia(Candle candleMinAtual, Candle candleMinProximo) {
        if (candleMinProximo == null)
            return true;
        
        return verificaHorarioFinal(candleMinAtual) || verificaSeEhUltimoCandleDoDia(candleMinProximo, candleMinAtual);
    }
    
    
    /**
     * Baseado no timer disparado, calcula o tempo médio esperado para que todo
     * o processamento termine. Para a média, utiliza 10% do total de execuções esperadas,
     * sempre com as ultimas medições.
     * A cada chamada do metodo, incrementa o contador de execucoes e calcula o tempo restante.
     * 
     * Ex. totalDeExecucoes = 1001, média = 0.1 * 1001 = 100,1 = 100 ultimos resultados.
     * 
     * 
     * @param totalDeExecucoes necessarias para terminar por completo o processamento
     * @return o tempo médio esperado para o fim do processamento
     */
    public long calculaTempoNecessarioMedio(int totalDeExecucoes){
        this.contadorTempoNecessario++;
        this.listaTempos.add(0, (System.nanoTime() - this.startTime));
        
        int qtdeParaMedia = Math.max(1, (int) (totalDeExecucoes * 0.1));
        if(this.listaTempos.size() > qtdeParaMedia)
            this.listaTempos.remove(qtdeParaMedia);

        long mediaTempos = 0;
        for(Long tempo : this.listaTempos){
            mediaTempos+= tempo;
        }
        mediaTempos = mediaTempos/qtdeParaMedia;

        return (totalDeExecucoes - this.contadorTempoNecessario) * mediaTempos;
    }
}
