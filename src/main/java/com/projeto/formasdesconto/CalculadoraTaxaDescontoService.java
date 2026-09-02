package com.projeto.formasdesconto;

import java.util.ArrayList;
import java.util.List;

import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class CalculadoraTaxaDescontoService {

    private List<IFormaDescontoTaxaEntrega> formasDeDesconto;

    public CalculadoraTaxaDescontoService() {

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
