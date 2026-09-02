package com.projeto.model;

/**
 * @author João Vitor Henrique
 */

public class Cliente {

    private String nome;
    private String tipo;
    private double fidelidade;
    private String logradouro;
    private String bairro;
    private String cidade;

    public Cliente(String nome, String tipo, double fidelidade, String logradouro, String bairro, String cidade) {
        if (nome == null) {
            throw new RuntimeException("O nome do cliente está nulo.");
        }
        if (tipo == null) {
            throw new RuntimeException("O tipo do cliente está nulo.");
        }
        if (logradouro == null) {
            throw new RuntimeException("O logradouro do cliente está nulo.");
        }
        if (bairro == null) {
            throw new RuntimeException("O bairro do cliente está nulo.");
        }
        if (cidade == null) {
            throw new RuntimeException("A cidade do cliente está nula.");
        }

        this.nome = nome;
        this.tipo = tipo;
        this.fidelidade = fidelidade;
        this.logradouro = logradouro;
        this.bairro = bairro;
        this.cidade = cidade;
    }

    public String getNome() { return nome; }

    public String getTipo() { return tipo; }

    public String getLogradouro() { return logradouro; }

    public String getBairro() { return bairro; }

    public String getCidade() { return cidade; }

    public double getFidelidade() { return fidelidade; }

    public void setFidelidade(double fidelidade) { this.fidelidade = fidelidade; }

    @Override
    public String toString() {
        return "Nome: " + nome + "\n" +
               "Tipo: " + tipo + "\n" +
               "Fidelidade: " + fidelidade + "\n" +
               "Logradouro: " + logradouro + "\n" +
               "Bairro: " + bairro + "\n" +
               "Cidade: " + cidade + "\n";
    }
    
}
