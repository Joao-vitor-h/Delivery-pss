package com.projeto.model;

import java.time.LocalDateTime;

/**
 * @author João Vitor Henrique
 */

public class CupomDescontoPedido {
    private String codigo;
    private double percentual;
    private LocalDateTime inicio;
    private LocalDateTime fim;

    public CupomDescontoPedido(String codigo, double percentual, LocalDateTime inicio, LocalDateTime fim) {
        if (inicio == null) {
            throw new RuntimeException("O início do intervalo de válidade do cupom está nulo.");
        }
        if (fim == null) {
            throw new RuntimeException("O fim do intervalo de validade do cupom está nulo.");
        }

        this.codigo = codigo.toUpperCase();
        this.percentual = percentual;
        this.inicio = inicio;
        this.fim = fim;
    }

    public String getCodigo() { return codigo; }

    public double getPercentual() { return percentual; }

    public LocalDateTime getInicio() { return inicio; }

    public LocalDateTime getFim() { return fim; }

    public String getValidade() {
        return "Validade: " + getInicio() + " até " + getFim();
    }

    public void setPercentual(double percentual) {
        this.percentual = percentual;
    }

    public void setInicio(LocalDateTime inicio) {
        this.inicio = inicio;
    }

    public void setFim(LocalDateTime fim) {
        this.fim = fim;
    }
}
