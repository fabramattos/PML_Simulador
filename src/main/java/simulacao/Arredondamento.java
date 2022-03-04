/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simulacao;

import Ordens.Ordem;

/**
 *
 * @author Felipe Mattos
 */
public class Arredondamento {
    
    
    /**
     * Arredonda o valor passado como argumento, baseado no tipo do ativo selecionado
     * @param valor valor a ser arredondado
     * @return o valor ja arredondado
     */
    public double arredondaCimaMultiplo(double valor, Ordem ordem) {
        switch (ordem.getAtivo()) {
            default:
                break;
            case DOL:
                valor = Math.ceil(valor / 0.5F) * 0.5F;
                break;
            case IND:
                valor = Math.ceil(valor / 5) * 5;
                break;
            case ACAO:
                valor = Math.ceil(valor / 0.01F) * 0.01F;
                break;
        }
        return valor;
    }

    /**
     * Arredonda o valor passado como argumento, baseado no tipo do ativo selecionado
     * @param valor valor a ser arredondado
     * @return o valor ja arredondado
     */
    public double arredondaBaixoMultiplo(double valor, Ordem ordem) {
        switch (ordem.getAtivo()) {
            default:
                break;
            case DOL:
                valor = Math.floor(valor / 0.5F) * 0.5F;
                break;
            case IND:
                valor = Math.floor(valor / 5) * 5;
                break;
            case ACAO:
                valor = Math.floor(valor / 0.01F) * 0.01F;
                break;
        }
        return valor;
    }
    
}
