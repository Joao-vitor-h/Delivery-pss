package com.projeto.cupom;

import com.projeto.model.Pedido;

import java.util.ArrayList;
import java.util.List;

/**
 * @author João Vitor Henrique
 */

public class FormasDeValidacaoCupomPedidoService {

    private List<IFormaDeValidacao> formasDeValidacao;

    public FormasDeValidacaoCupomPedidoService(String codigo, Pedido pedido) {
        if (codigo == null) {
            throw new RuntimeException("O código está nulo.");
        }

        if (pedido == null) {
            throw new RuntimeException("O pedido está nulo.");
        }

        formasDeValidacao = new ArrayList<>();
        formasDeValidacao.add(new FormaDeValidacaoCodigo(codigo));
        formasDeValidacao.add(new FormaDeValidacaoValidadeCupom(codigo));
        formasDeValidacao.add(new FormaDeValidacaoPedidoCupomAplicado(codigo, pedido));
    }

    public boolean validarCupom() {
        List<Boolean> validacao = new ArrayList<>();

        for (IFormaDeValidacao forma : formasDeValidacao) {
            validacao.add(forma.seAplica());
        }

        // Atende a regra 4.5
        for (Boolean resultado : validacao) {
            if (resultado == false) {
                return false;
            }
        }

        return true;
    }
}
