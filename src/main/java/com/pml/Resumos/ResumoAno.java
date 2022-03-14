package com.pml.Resumos;

import com.pml.Controladores.ControleTempo;
import com.pml.InterfaceGrafica.IG;

public class ResumoAno extends Resumos{

    ResumoAno() {
        super();
    }
    
    @Override
    public boolean verificaPassagemPeriodo(Resumos diaAtual, Resumos diaAnterior){
        return new ControleTempo().verificaPassagemAno(diaAtual.getData(), diaAnterior.getData());
    }
    
    @Override
    public Resumos criaInstancia() {
        return new ResumoAno();
    }

    @Override
    public void geraResumo() {
        if(!IG.isTemRelAnual())
            return;
        
        super.geraResumo(); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public void gravaResumo(Resumos resumo){
        if(!IG.isTemRelAnual())
            return;
        
        new Relatorios().gravaResumoAnual(resumo);
    }
    
    @Override
    public String toString(){
        return "Resumo Ano: " + super.toString(); 
    }
}
