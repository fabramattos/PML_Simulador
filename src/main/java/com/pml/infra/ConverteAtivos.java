/**
 * Esta classe converte os valores de ticks para pontos ou centavos,
 * dependendo do ativo
 **/
package com.pml.infra;

public class ConverteAtivos {
    
    public double arredondaCimaMultiplo(int ativo, double qtde){
        float multiplo = 1;
        switch (ativo){
            case 1: //DOL
                multiplo = 1;
                break;
                
            case 2: //IND
                multiplo = 1;
                break;
                
            case 3: //AÇÃO
                multiplo = 1;
                break;
        }
        return (int)(Math.ceil(qtde/multiplo)*multiplo);
    }
    
    public float arredondaBaixoMultiplo(int multiplo, float valor){
        return (int)(Math.floor(valor/multiplo)*multiplo);
    }
    
}
