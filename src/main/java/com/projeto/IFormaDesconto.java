package com.projeto;

import com.projeto.model.Pedido;

public interface IFormaDesconto {

    public abstract void calcularDesconto(Pedido pedido);
}
