package com.projeto.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.projeto.APITaxaDescontoMock;

/**
 * @author João Vitor Henrique
 */

public class Pedido {
    
    private double taxaEntrega;
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
        this.taxaEntrega = APITaxaDescontoMock.getTaxaDescontoEntrega();
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

    public void aplicarDesconto(Optional<CupomDescontoEntrega> optDesconto) {

        if (optDesconto.isEmpty()) {
            throw new IllegalArgumentException("Cupom inválido.");
        }

        if (this.taxaEntrega == 0.0) {
            return;
        }

        CupomDescontoEntrega cupom = optDesconto.get();

        double valor = cupom.getValorDesconto();

        if (valor > this.taxaEntrega)  {

            valor = 0.0;
            // Aplicação parcial do desconto. PROBLEMA!
            while (valor < this.taxaEntrega) {
                valor += 0.5;
            }

            CupomDescontoEntrega novoCupom = new CupomDescontoEntrega("Aplicação Parcial " + cupom.getNomeMetodo(), valor);

            optDesconto = Optional.of(novoCupom);
        }

        this.taxaEntrega -= optDesconto.get().getValorDesconto();

        cuponsDescontoEntrega.add(optDesconto.get());
    }

    // Método para calcular o desconto da taxa de entrega.
    public double getValorDescontoTaxaEntrega() {
        double desconto = 0.0;

        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            desconto += cupom.getValorDesconto();
        }

        return desconto;
    }

    // Método referente ao desconto no pedido.
    public double getDescontoConcedido() {
        return 0.0;
    }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega() { return cuponsDescontoEntrega; }

    @Override
    public String toString() {

        return "-Cliente: " + cliente.getNome() + "\n" +
               "-Data: " + data + "\n" +
               "-Desconto da Taxa de Entrega: R$" + this.getValorDescontoTaxaEntrega() + "\n" +
               "-Taxa de Entrega: R$" + taxaEntrega + "\n" +
               "\n-VALOR TOTAL: R$" + this.calcularTotalNoPedido() + "\n";
    }

    // Método novo
    public double calcularTotalNoPedido() {
        return this.getValorPedido() + this.taxaEntrega;
    }
}
