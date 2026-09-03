package com.projeto;

import com.projeto.cupom.FormasDeValidacaoCupomPedidoService;
import com.projeto.model.CupomDescontoPedido;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDeDescontoPedido implements IFormaDesconto {

    private CupomDescontoPedido cupom;

    public void aplicarCupom(CupomDescontoPedido cupom) {
        if (cupom == null) {
            throw new RuntimeException("O cupom está nulo");
        }
        this.cupom = cupom;
    }

    // Resolver a questão sobre o cupom só durar na aplicação de um pedido.
    @Override
    public void calcularDesconto(Pedido pedido) {
        try {
            FormasDeValidacaoCupomPedidoService validacao = new FormasDeValidacaoCupomPedidoService(cupom.getCodigo(), pedido);

            if (validacao.validarCupom()) {
                pedido.setCupomDescontoPedido(cupom);
            }
        } catch (NullPointerException e) {
            System.out.println();
        }

        cupom = null;
    }
}
