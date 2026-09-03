package com.projeto;

import com.projeto.model.CupomDescontoPedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author João Vitor Henrique
 */

public class CupomDescontoPedidoRepository {

    private List<CupomDescontoPedido> cupons;

    public CupomDescontoPedidoRepository() {
        cupons = new ArrayList<>();
        cupons.add(new CupomDescontoPedido(
                "DESC10",
                0.1,
                LocalDateTime.of(2026, 9, 1, 00, 00),
                LocalDateTime.of(2026, 9, 10, 00, 00))
        );
        cupons.add(new CupomDescontoPedido(
                "DESC20",
                0.20,
                LocalDateTime.of(2026, 10, 1, 0, 0),
                LocalDateTime.of(2026, 10, 5, 23, 59)
        ));
        cupons.add(new CupomDescontoPedido(
                "DESC30",
                0.30,
                LocalDateTime.of(2026, 9, 24, 0, 0),
                LocalDateTime.of(2026, 9, 24, 23, 59)
        ));
        cupons.add(new CupomDescontoPedido(
                "DIAPAI12",
                0.12,
                LocalDateTime.of(2026, 10, 9, 0, 0),
                LocalDateTime.of(2026, 10, 10, 23, 59)
        ));

        cupons.add(new CupomDescontoPedido(
                "DIAMAE12",
                0.12,
                LocalDateTime.of(2026, 10, 10, 0, 0),
                LocalDateTime.of(2026, 10, 12, 23, 59)
        ));

        cupons.add(new CupomDescontoPedido(
                "NATAL10",
                0.10,
                LocalDateTime.of(2026, 9, 20, 0, 0),
                LocalDateTime.of(2026, 9, 26, 23, 59)
        ));

        cupons.add(new CupomDescontoPedido(
                "FESTA15",
                0.15,
                LocalDateTime.of(2026, 9, 30, 18, 0),
                LocalDateTime.of(2026, 10, 1, 6, 0)
        ));

        cupons.add(new CupomDescontoPedido(
                "BLACK50",
                0.50,
                LocalDateTime.of(2026, 9, 28, 0, 0),
                LocalDateTime.of(2026, 9, 28, 23, 59)
        ));
    }

    public void adicionarCupom(CupomDescontoPedido cupom) {
        cupons.add(cupom);
    }

    public void removerCupons(String codigo) {

        Optional<CupomDescontoPedido> optCupom = buscarCupom(codigo);

        if (optCupom.isEmpty()) {
            throw new RuntimeException("O cupom não existe!");
        }

        cupons.remove(optCupom.get());
    }

    public Optional<CupomDescontoPedido> buscarCupom(String codigo) {
        for (CupomDescontoPedido cupom : cupons) {
            if (cupom.getCodigo().equals(codigo)) {
                return Optional.of(cupom);
            }
        }
        return Optional.empty();
    }

    public void alterarPercentualCupom(String codigo, double percentual) {

        Optional<CupomDescontoPedido> optCupom = buscarCupom(codigo);

        if (optCupom.isEmpty()) {
            throw new RuntimeException("O cupom não existe!");
        }

        optCupom.get().setPercentual(percentual);
    }

    public void alterarValidadeCupom(String codigo, LocalDateTime inicio, LocalDateTime fim) {
        Optional<CupomDescontoPedido> optCupom = buscarCupom(codigo);

        if (optCupom.isEmpty()) {
            throw new RuntimeException("O cupom não existe!");
        }

        optCupom.get().setInicio(inicio);
        optCupom.get().setFim(fim);
    }
}
