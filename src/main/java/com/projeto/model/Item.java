package com.projeto.model;

/**
 * @author João Vitor Henrique
 */

public class Item {

    private String nome;
    private int quantidade;
    private double valorUnitario;
    private String tipo;

    public Item(String nome, int quantidade, double valorUnitario, String tipo) {
        if (nome == null) {
            throw new RuntimeException("O nome do item é nulo.");
        }
        if (tipo == null) {
            throw new RuntimeException("O tipo do item é nulo");
        }

        this.nome = nome;
        this.quantidade = quantidade;
        this.valorUnitario = valorUnitario;
        this.tipo = tipo;
    }

    public double getValorTotal() { return quantidade * valorUnitario; }

    public String getTipo() { return tipo; }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Quantidade: " + quantidade + "\n" +
               "Valor Unitário: " + valorUnitario + "\n" +
               "Tipo: " + tipo.toUpperCase() + "\n";
    }
}
