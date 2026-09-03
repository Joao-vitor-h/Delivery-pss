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
    private String codigoCupom;
    private double percentualCupom;

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
        this.codigoCupom = "";
        this.percentualCupom = 0.0;
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

    public double getValorDescontoTaxaEntrega() {
        double desconto = 0.0;

        for (CupomDescontoEntrega cupom : cuponsDescontoEntrega) {
            desconto += cupom.getValorDesconto();
        }

        return desconto;
    }

    public double getDescontoConcedido() throws NullPointerException { return percentualCupom; }

    public List<CupomDescontoEntrega> getCuponsDescontoEntrega() { return cuponsDescontoEntrega; }

    @Override
    public String toString() {

        return "-Cliente: " + cliente.getNome() + "\n" +
               "-Data: " + data.getDayOfMonth() + "/" + data.getMonthValue()+ "/" + data.getYear() + " " + data.getHour() + ":" + data.getMinute() + "\n" +
               "-Desconto da Taxa de Entrega: R$" + getValorDescontoTaxaEntrega() + "\n" +
               "-Código do cupom: " + (getCodigoCupom().isBlank() ? "Sem cupom aplicado" : getCodigoCupom()) + "\n" +
               "-Taxa de Desconto no Pedido: " + getPercentualCupom() * 100 + "%\n" +
               "-Taxa de Entrega: R$" + taxaEntrega + "\n" +
               "\n-VALOR TOTAL: R$" + (calcularTotalNoPedido() - (calcularTotalNoPedido() * getPercentualCupom())) + "\n";
    }

    public double calcularTotalNoPedido() {
        return this.getValorPedido() + this.taxaEntrega;
    }

    public void setCupomDescontoPedido(CupomDescontoPedido cupom) {
        codigoCupom = cupom.getCodigo();
        percentualCupom = cupom.getPercentual();
    }

    public boolean existeCupomAplicado() {
        return codigoCupom.isBlank();
    }

    public String getCodigoCupom() { return codigoCupom; }

    public double getPercentualCupom() { return  percentualCupom; }
}
