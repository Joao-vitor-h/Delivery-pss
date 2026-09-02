package com.projeto.formasdesconto;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

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
    public Optional<CupomDescontoEntrega> calcularDesconto(Pedido pedido) {

        List<Item> itensPedido = pedido.getItens();
        double soma = 0.0;
        String categoria;
        
        for (Item item : itensPedido) {
            categoria = item.getTipo().toUpperCase();

            if (descontosPorTipoItem.containsKey(categoria)) {
                soma += descontosPorTipoItem.get(categoria);
            }
        }

        return Optional.of(new CupomDescontoEntrega("Forma de Desconto por Item", soma));
    }

    @Override
    public boolean seAplica(Pedido pedido) {

        boolean confirmacao = false;

        for (Item item : pedido.getItens()) {
            confirmacao = descontosPorTipoItem.containsKey(
                item.getTipo().toUpperCase()
            );
            
            if (confirmacao == true) {
                return confirmacao;
            }
        }

        return confirmacao;
    }
}
