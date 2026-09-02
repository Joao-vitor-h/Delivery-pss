package com.projeto.formasdesconto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

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
    public Optional<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {

        tipoCliente = pedido.getCliente().getTipo().toUpperCase();

        return Optional.of(new CupomDescontoEntrega(
            "Forma de Desconto por Tipo de Cliente - " + tipoCliente, 
            descontosPorTipoCliente.get(tipoCliente)
        ));
    }

    @Override
    public boolean seAplica(Pedido pedido) {

        tipoCliente = pedido.getCliente().getTipo().toUpperCase();
       
        return descontosPorTipoCliente.containsKey(tipoCliente);
    }
}
