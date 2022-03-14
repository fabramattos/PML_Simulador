package com.pml.Resumos;

import com.pml.ArquivosTemporarios.ArquivoTemp;
import com.pml.InterfaceGrafica.IG;

/**
 * @author Felipe Mattos
 **/
public class ResumoSimulacao extends Resumos{
    public ResumoSimulacao() {
        super();
    }

    /**
     *
     * @param diaAtual
     * @param diaAnterior
     * @return
     */
    @Override
    public boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior){
        return false;
    }
    
    @Override
    public Resumos criaInstancia() {
        return new ResumoSimulacao();
    }
    
    @Override
    public String toString(){
        return "Resumo Simulação: " + super.toString(); 
    }

    /**
     * RELATÓRIOS COMPLETOS (COMPLETO E DIARIO COMPLETO) NÃO SÃO ARMAZENADOS EM LISTAS DEVIDO AO SEU TAMANHO, MAS SÃO GRAVADOS
     * NO EXCEL CONFORME SÃO GERADOS. GRAVADOS NA MEMÓRIA, NÃO DIRETAMENTE NO ARQUIVO.
     * @param resumo 
     */
    @Override
    public void gravaResumo(Resumos resumo) {
        Relatorios.gravaResumoCompleto(resumo);
    }
    
}