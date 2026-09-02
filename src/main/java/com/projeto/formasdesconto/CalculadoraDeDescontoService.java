package com.projeto.formasdesconto;

import java.util.ArrayList;
import java.util.List;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class CalculadoraDeDescontoService {
    
    private IFormaDescontoTaxaEntrega metodosDeDesconto;

    public List<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {

        List<CupomDescontoEntrega> cupons = new ArrayList<>();

        // Calculando desconto por bairro.
        metodosDeDesconto = new FormaDescontoTaxaPorBairro();
        cupons.add(metodosDeDesconto.calcularDesconto(pedido));

        // Calculando desconto por tipo cliente.
        metodosDeDesconto = new FormaDescontoTaxaPorTipoCliente();
        cupons.add(metodosDeDesconto.calcularDesconto(pedido));

        // Calculando desconto por tipo item.
        metodosDeDesconto = new FormaDescontoTipoItem();
        cupons.add(metodosDeDesconto.calcularDesconto(pedido));

        // Calculando desconto por valor do pedido.
        metodosDeDesconto = new FormaDescontoValorPedido(200);
        cupons.add(metodosDeDesconto.calcularDesconto(pedido));

        return cupons;
    }
}
