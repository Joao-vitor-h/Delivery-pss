package com.projeto.cupom;

import com.projeto.CupomDescontoPedidoRepository;
import com.projeto.model.CupomDescontoPedido;

import java.util.Optional;

public class FormaDeValidacaoCodigo implements IFormaDeValidacao {

    private String codigo;

    public FormaDeValidacaoCodigo(String codigo) {

        if (codigo == null) {
            throw new RuntimeException("O codigo está nulo.");
        }

        this.codigo = codigo;
    }

    @Override
    public boolean seAplica() {
        CupomDescontoPedidoRepository cupons = new CupomDescontoPedidoRepository();

        Optional<CupomDescontoPedido> optCupom = cupons.buscarCupom(codigo);

        return optCupom.isPresent();
    }
}
