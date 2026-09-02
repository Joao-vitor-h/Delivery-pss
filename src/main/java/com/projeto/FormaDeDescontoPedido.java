package com.projeto;

import com.projeto.cupom.FormasDeValidacaoCupomPedidoService;
import com.projeto.model.CupomDescontoPedido;
import com.projeto.model.Pedido;

public class FormaDeDescontoPedido implements IFormaDesconto {

    private CupomDescontoPedido cupom;

    public FormaDeDescontoPedido(CupomDescontoPedido cupom) {

        if (cupom == null) {
            throw new RuntimeException("O código está nulo na forma de desconto.");
        }

        this.cupom = cupom;
    }

    @Override
    public void calcularDesconto(Pedido pedido) {
        FormasDeValidacaoCupomPedidoService validacao = new FormasDeValidacaoCupomPedidoService(cupom.getCodigo(), pedido);

        if (validacao.validarCupom()) {
            pedido.setCupomDescontoPedido(cupom);
        }
    }
}
