package com.projeto.entrega;

import com.projeto.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Vitor Henrique
 */

public class CalculadoraTaxaDescontoEntregaService {
    private List<IFormaDescontoTaxaEntrega> formasDeDesconto;

    public CalculadoraTaxaDescontoEntregaService() {

        formasDeDesconto = new ArrayList<>();
        formasDeDesconto.add(new FormaDescontoTaxaPorBairro());
        formasDeDesconto.add(new FormaDescontoTaxaPorTipoCliente());
        formasDeDesconto.add(new FormaDescontoTipoItem());
        formasDeDesconto.add(new FormaDescontoValorPedido(200));
    }

    public void calcularDesconto(Pedido pedido) {

        for (IFormaDescontoTaxaEntrega formaDeDesconto : formasDeDesconto) {
            if(formaDeDesconto.seAplica(pedido)) {
                pedido.aplicarDesconto(
                    formaDeDesconto.calcularDesconto(pedido)
                );
            }
        }
    }
}
