package com.projeto.formasdesconto;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public interface IFormaDescontoTaxaEntrega {

    public abstract CupomDescontoEntrega calcularDesconto(Pedido pedido);
    public abstract boolean seAplica(Pedido pedido);
}
