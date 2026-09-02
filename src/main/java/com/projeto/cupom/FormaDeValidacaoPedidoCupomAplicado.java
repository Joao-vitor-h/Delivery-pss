package com.projeto.cupom;

import com.projeto.CupomDescontoPedidoRepository;
import com.projeto.model.CupomDescontoPedido;
import com.projeto.model.Pedido;

import java.util.Map;

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
        if (pedido.getCupomAplicado().isEmpty()) {
            return true;
        }
        else {
            Map<String, Double> cupomAplicado = pedido.getCupomAplicado();
            CupomDescontoPedidoRepository cupons = new CupomDescontoPedidoRepository();
            CupomDescontoPedido novoCupom = cupons.buscarCupom(codigo).get();

            return novoCupom.getPercentual() > cupomAplicado.get(codigo);
        }
    }
}
