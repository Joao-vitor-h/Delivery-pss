package com.projeto.formasdesconto;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    
    private String bairroCliente;
    private Map<String, Double> bairros;

    public FormaDescontoTaxaPorBairro() {

        bairros = new HashMap<>();
        bairros.put("CENTRO", 2.0);
        bairros.put("BELA VISTA", 3.0);
        bairros.put("CIDADE MARAVILHOSA", 1.5);
    }

    @Override
    public Optional<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {
        bairroCliente = pedido.getCliente().getBairro().toUpperCase();

        return Optional.of(new CupomDescontoEntrega(
            "Forma de Desconto por Bairro - " + bairroCliente, 
            bairros.get(bairroCliente)
        ));
    }

    @Override
    public boolean seAplica(Pedido pedido) {
        bairroCliente = pedido.getCliente().getBairro().toUpperCase();

        return bairros.containsKey(bairroCliente);
    }
}
