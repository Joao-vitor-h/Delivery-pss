package com.projeto.model;

/**
 * @author João Vitor Henrique
 */

public class CupomDescontoEntrega {

    private String nomeMetodo;
    private double valorDesconto;

    public CupomDescontoEntrega (String nomeMetodo, double valorDesconto) {
        
        if (nomeMetodo == null) {
            throw new RuntimeException("O nome do método de desconto é nulo.");
        }

        this.nomeMetodo = nomeMetodo;
        this.valorDesconto = valorDesconto;
    }

    public double getValorDesconto() { return valorDesconto; }

    public String getNomeMetodo() { return nomeMetodo; }

    @Override
    public String toString() {
        return "Nome do Método: " + nomeMetodo + "\n" +
               "Valor do Desconto: " + valorDesconto + "\n";
    }
}
