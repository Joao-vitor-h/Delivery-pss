package com.projeto.formasdesconto;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDescontoTaxaPorBairro implements IFormaDescontoTaxaEntrega {
    
    private String bairroCliente;

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {

        double desconto = 0.0;
        bairroCliente = pedido.getCliente().getBairro().toUpperCase();
        
        if (seAplica(pedido)) {
            switch (bairroCliente) {
                case "CENTRO" -> desconto = 2.0;
                case "BELA VISTA" -> desconto = 3.0;
                case "CIDADE MARAVILHOSA" -> desconto = 1.5;
            }
        }

        return new CupomDescontoEntrega("Forma de Desconto por Bairro", desconto);
    }

    @Override
    public boolean seAplica(Pedido pedido) {

        boolean confirmacao;
        bairroCliente = pedido.getCliente().getBairro().toUpperCase();

        switch (bairroCliente) {
            case "CENTRO" -> confirmacao = true;
            case "BELA VISTA" -> confirmacao = true;
            case "CIDADE MARAVILHOSA" -> confirmacao = true;
            default -> confirmacao = false;
        }

        return confirmacao;
    }
}
