package com.projeto.formasdesconto;

import java.util.HashMap;
import java.util.Map;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDescontoTaxaPorTipoCliente implements IFormaDescontoTaxaEntrega {

    private Map<String, Double> descontosPorTipoCliente;
    private String tipoCliente;

    public FormaDescontoTaxaPorTipoCliente() {

        descontosPorTipoCliente = new HashMap<>();
        descontosPorTipoCliente.put("OURO", 3.0);
        descontosPorTipoCliente.put("PRATA", 2.0);
        descontosPorTipoCliente.put("BRONZE", 1.0);
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {

        tipoCliente = pedido.getCliente().getTipo().toUpperCase();
        double desconto = 0.0;

        if (seAplica(pedido)) {
            desconto = descontosPorTipoCliente.get(tipoCliente);
        }

        return new CupomDescontoEntrega("Forma de Desconto por Tipo de Cliente", desconto);
    }

    @Override
    public boolean seAplica(Pedido pedido) {

        tipoCliente = pedido.getCliente().getTipo().toUpperCase();
        boolean confirmacao;

        switch (tipoCliente) {
            case "OURO" -> confirmacao = true;
            case "PRATA" -> confirmacao = true;
            case "BRONZE" -> confirmacao = true;
            default -> confirmacao = false;
        }

        return confirmacao;
    }
}
