package com.pml.Resumos;

import com.pml.Controladores.ControleTempo;
import com.pml.InterfaceGrafica.IG;

public class ResumoMes extends Resumos{
    
    public ResumoMes(){
        super();
    }

    /**
     * 
     * @param diaAtual
     * @param diaAnterior
     * @return True se passou um mes
     */
    @Override
    public boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior){
        return new ControleTempo().verificaPassagemMes(diaAtual.getData(), diaAnterior.getData());
    }
    
    @Override
    public void gravaResumo(Resumos resumo){
        if(!IG.isTemRelMensal())
            return;
        Relatorios.gravaResumoMensal(resumo);
    }
    
    @Override
    public Resumos criaInstancia() {
        return new ResumoMes();
    }

    @Override
    public void geraResumo() {
        if(!IG.isTemRelMensal())
            return;
        super.geraResumo(); //To change body of generated methods, choose Tools | Templates.
    }
    
    
    
    @Override
    public String toString(){
        return "Resumo Mes: " + super.toString(); 
    }
}