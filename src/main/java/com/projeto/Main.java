package com.projeto;

import java.time.LocalDateTime;

import com.projeto.entrega.CalculadoraTaxaDescontoEntregaService;
import com.projeto.model.*;

/**
 * @author João Vitor Henrique
 */

// Teste

public class Main {
    public static void main( String[] args ) {
        
        try {
            // Itens
            Item pizza = new Item("Pizza", 1, 150.0, "Alimentação");
            Item bolo = new Item("Bolo", 1, 50.0, "Alimentação");
            Item cocaCola = new Item("Coca-Cola", 1, 15.0, "Bebida");
            Item caderno = new Item("Caderno", 1, 35, "teste");
            Item cubo = new Item("Cubo Mágico", 1, 20.0, "lazer");

            // Cliente
            Cliente cliente1 = new Cliente("João Vitor", "bronze", 0, "teste", "cidade maravilhosa", "teste");
            Cliente cliente2 = new Cliente("Carlos Henrique", "ouro", 0, "teste", "centro", "teste");

            // Pedidos
            Pedido pedido1 = new Pedido(LocalDateTime.of(2026, 8, 20, 17, 15, 52), cliente1);
            Pedido pedido2 = new Pedido(LocalDateTime.of(2026, 8, 20, 17, 27, 31), cliente2);

            // Itens do pedido 1.
            pedido1.adicionarItem(pizza);
            pedido1.adicionarItem(caderno);
            pedido1.adicionarItem(cubo);

            // Itens do pedido 2.
            pedido2.adicionarItem(bolo);
            pedido2.adicionarItem(caderno);
            pedido2.adicionarItem(pizza);
            pedido2.adicionarItem(cocaCola);

            CupomDescontoPedido cupomPedido = new CupomDescontoPedido(
                    "DESC10",
                    0.1,
                    LocalDateTime.of(2026, 9, 1, 00, 00),
                    LocalDateTime.of(2026, 9, 10, 00, 00)
            );

            CalculadoraDeDescontoService calculadora = new CalculadoraDeDescontoService(cupomPedido);

            // Referente ao pedido 1.
            calculadora.calcularDesconto(pedido1);

            System.out.println("=====================================================");
            System.out.println(pedido1);
            System.out.println("-ITENS:\n");

            for (Item item : pedido1.getItens()) {
                System.out.println(item);
            }

            System.out.println("-DESCONTOS APLICADOS:\n");
            for (CupomDescontoEntrega cupom : pedido1.getCuponsDescontoEntrega()) {
                System.out.println(cupom);
            }

            System.out.println("=====================================================");

            // Referente ao pedido 2.
            calculadora.calcularDesconto(pedido2);

            System.out.println("=====================================================");
            System.out.println(pedido2);
            System.out.println("-ITENS:\n");

            for (Item item : pedido2.getItens()) {
                System.out.println(item);
            }

            System.out.println("-DESCONTOS APLICADOS:\n");
            for (CupomDescontoEntrega cupom : pedido2.getCuponsDescontoEntrega()) {
                System.out.println(cupom);
            }

            System.out.println("=====================================================");

        } catch (RuntimeException e) {
            System.out.println(e.getMessage());
        }
    }
}
