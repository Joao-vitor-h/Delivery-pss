package com.projeto.formasdesconto;

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
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {

        double desconto = 0.0;

        if (seAplica(pedido)) {
            desconto = VALOR_DESCONTO;
        }

        return new CupomDescontoEntrega("Forma de Desconto por Valor do Pedido", desconto);
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