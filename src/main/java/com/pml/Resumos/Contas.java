/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pml.Resumos;

/**
 *
 * @author fabra
 */
public class Contas {
    private static int contasCriadas = 1;
    private int numeroConta;
    private ResumoDia rDia;
    
    public Contas() {
        numeroConta = Contas.contasCriadas++;
    }
    
    
}
