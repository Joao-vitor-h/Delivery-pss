package com.projeto;

import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public interface IFormaDesconto {
    public abstract void calcularDesconto(Pedido pedido);
}
