package com.projeto.formasdesconto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Item;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class FormaDescontoTipoItem implements IFormaDescontoTaxaEntrega {
    
    private Map<String, Double> descontosPorTipoItem;

    public FormaDescontoTipoItem() {

        descontosPorTipoItem = new HashMap<>();
        descontosPorTipoItem.put("ALIMENTAÇÃO", 5.0);
        descontosPorTipoItem.put("EDUCAÇÃO", 2.0);
        descontosPorTipoItem.put("LAZER", 1.5);
    }

    @Override
    public CupomDescontoEntrega calcularDesconto(Pedido pedido) {

        List<Item> itensPedido = pedido.getItens();
        double soma = 0.0;

        if (seAplica(pedido)) {
            for (Item item : itensPedido) {
                try {
                    String tipo = item.getTipo().toUpperCase();
                    soma += descontosPorTipoItem.get(tipo);
                } catch (RuntimeException e) {
                    soma += 0.0;
                }
            }
        }

        return new CupomDescontoEntrega("Forma de Desconto por Item", soma);
    }

    @Override
    public boolean seAplica(Pedido pedido) {

        boolean confirmacao = false;

        for (Item item : pedido.getItens()) {
            switch (item.getTipo().toUpperCase()) {
                case "ALIMENTAÇÃO" -> confirmacao = true;
                case "EDUCAÇÃO" -> confirmacao = true;
                case "LAZER" -> confirmacao = true;
            }
            if (confirmacao == true) {
                break;
            }
        }

        return confirmacao;
    }
}
