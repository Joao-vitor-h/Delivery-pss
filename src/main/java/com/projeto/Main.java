package com.projeto;

import java.time.LocalDateTime;
import java.util.List;

import com.projeto.formasdesconto.CalculadoraDeDescontoService;
import com.projeto.model.Cliente;
import com.projeto.model.CupomDescontoEntrega;
import com.projeto.model.Item;
import com.projeto.model.Pedido;

/**
 * @author João Vitor Henrique
 */

public class Main {
    public static void main( String[] args ) {

        // Cenário de teste: altere as informações do cliente (bairro, tipo) para ver as mudanças de desconto. Altere o valor e o tipo dos itens para verificar as mudanças de desconto.

        CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService();

        Cliente cliente = new Cliente("João Vitor", "prata", 0, "Teste", "cidade maravilhosa", "Teste");

        Pedido pedido = new Pedido(LocalDateTime.of(2026, 8, 18, 16, 33, 45), cliente);

        Item pizza = new Item("Pizza", 2, 250.0, "AliMenTAçÃo");

        Item cocaCola = new Item("Coca-Cola", 1, 10.0, "alimentação");

        pedido.adicionarItem(pizza);
        pedido.adicionarItem(cocaCola);

        List<CupomDescontoEntrega> descontos = calculadora.calcularDesconto(pedido);

        for (CupomDescontoEntrega cupom : descontos) {
            pedido.aplicarDesconto(cupom);
        }

        System.out.println(pedido);

        for (CupomDescontoEntrega cupom : pedido.getCupomDescontoEntrega()) {
            System.out.print(cupom);
        }
    }
}
