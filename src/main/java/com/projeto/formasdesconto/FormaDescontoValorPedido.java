package com.projeto.formasdesconto;

import java.util.Optional;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDescontoValorPedido implements IFormaDescontoTaxaEntrega{

    private double limiteValorPedido;
    private double VALOR_DESCONTO = 5.0;

    public FormaDescontoValorPedido(double limiteValorPedido) {
        this.limiteValorPedido = limiteValorPedido;
    }

    @Override
    public Optional<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {
        return Optional.of(new CupomDescontoEntrega(
            "Forma de Desconto por Valor do Pedido - acima de R$" + limiteValorPedido, 
            VALOR_DESCONTO)
        );
    }

    @Override
    public boolean seAplica(Pedido pedido) {

        if (pedido.getValorPedido() > limiteValorPedido) {
            return true;
        }
        else {
            return false;
        }
    }
}