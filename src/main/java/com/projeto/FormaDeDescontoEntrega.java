package com.projeto;

import com.projeto.entrega.CalculadoraTaxaDescontoEntregaService;
import com.projeto.model.Pedido;

public class FormaDeDescontoEntrega implements IFormaDesconto {

    @Override
    public void calcularDesconto(Pedido pedido) {
        CalculadoraTaxaDescontoEntregaService calculadora = new CalculadoraTaxaDescontoEntregaService();

        calculadora.calcularDesconto(pedido);
    }
}
