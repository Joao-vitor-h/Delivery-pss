package com.projeto.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * @author João Vitor Henrique
 */

public class Pedido {
    
    private double taxaEntrega = 10.0;
    private Cliente cliente;
    private List<Item> itens;
    private List<CupomDescontoEntrega> cuponsDescontoEntrega;
    private LocalDateTime data;

    public Pedido (LocalDateTime data, Cliente cliente) {
        
        if (data == null) {
            throw new RuntimeException("A data é nula.");
        }
        if (cliente == null) {
            throw new RuntimeException("O cliente está nulo.");
        }

        this.data = data;
        this.cliente = cliente;
        this.itens = new ArrayList<>();
        this.cuponsDescontoEntrega = new ArrayList<>();
    }

    public void adicionarItem(Item item) {
        itens.add(item);
    }
    
    public double getValorPedido() {
        double soma = 0.0;

        for (Item item : itens) {
            soma += item.getValorTotal();
        }

        return soma;
    }

    public Cliente getCliente() { return cliente; }

    public List<Item> getItens() { return itens; }

    public double getTaxaEntrega() { return taxaEntrega; }

    public void aplicarDesconto(CupomDescontoEntrega desconto) {

        if (this.getDescontoConcedido() == this.taxaEntrega) {
            return;
        }

        if (desconto.getValorDesconto() == 0.0) {
            return;
        }

        double valor = desconto.getValorDesconto() + this.getDescontoConcedido();

        if (valor > this.taxaEntrega)  {

            valor = 0.0;

            while ((valor + this.getDescontoConcedido()) < taxaEntrega) {
                valor += 0.5;
            }

            CupomDescontoEntrega cupom = new CupomDescontoEntrega("Aplicação Parcial " + desconto.getNomeMetodo(), valor);

            desconto = cupom;
        }

        cuponsDescontoEntrega.add(desconto);
    }

    public double getDescontoConcedido() {
        double soma = 0.0;

        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            soma += cupom.getValorDesconto();
        }

        return soma;
    }

    public List<CupomDescontoEntrega> getCupomDescontoEntrega() { return cuponsDescontoEntrega; }

    // arrumar esse toString
    @Override
    public String toString() {

        return "Cliente: " + cliente.getNome() + "\n" +
               "Data: " + data + "\n" +
               "Itens: " + this.getItens() + "\n" +
               "Desconto: " + this.getDescontoConcedido() + "\n" +
               "Taxa de Entrega: " + (taxaEntrega - this.getDescontoConcedido()) + "\n";
    }
}
