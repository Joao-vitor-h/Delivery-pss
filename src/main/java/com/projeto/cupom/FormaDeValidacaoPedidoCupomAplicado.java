package com.projeto.cupom;

import com.projeto.CupomDescontoPedidoRepository;
import com.projeto.model.CupomDescontoPedido;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDeValidacaoPedidoCupomAplicado implements IFormaDeValidacao {

    private String codigo;
    private Pedido pedido;

    public FormaDeValidacaoPedidoCupomAplicado(String codigo, Pedido pedido) {

        if (codigo == null) {
            throw new RuntimeException("O código está nulo");
        }
        if (pedido == null) {
            throw new RuntimeException("O pedido está nulo.");
        }

        this.pedido = pedido;
        this.codigo = codigo;

    }

    @Override
    public boolean seAplica() {
        if (pedido.existeCupomAplicado()) {
            return true;
        }
        else {
            CupomDescontoPedidoRepository cupons = new CupomDescontoPedidoRepository();
            CupomDescontoPedido novoCupom = cupons.buscarCupom(codigo).get();

            return novoCupom.getPercentual() > pedido.getPercentualCupom();
        }
    }
}
