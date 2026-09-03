package com.projeto;

import com.projeto.model.CupomDescontoPedido;
import com.projeto.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Vitor Henrique
 */

public class CalculadoraDeDescontoService {

    private List<IFormaDesconto> formasDeDesconto;

    public CalculadoraDeDescontoService() {
        formasDeDesconto = new ArrayList<>();
        formasDeDesconto.add(new FormaDeDescontoEntrega());
        formasDeDesconto.add(new FormaDeDescontoPedido());
    }

    public void aplicarCupom(CupomDescontoPedido cupom) {
        if (cupom == null) {
            throw new RuntimeException("O cupom está nulo");
        }

        FormaDeDescontoPedido desconto = (FormaDeDescontoPedido) formasDeDesconto.get(1);
        desconto.aplicarCupom(cupom);
    }

    public void calcularDesconto(Pedido pedido) {

        if (pedido == null) {
            throw new RuntimeException("O pedido está nulo.");
        }

        for (IFormaDesconto forma : formasDeDesconto) {
            forma.calcularDesconto(pedido);
        }
    }
}
