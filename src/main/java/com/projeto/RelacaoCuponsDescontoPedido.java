package com.projeto;

import com.projeto.model.CupomDescontoPedido;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

// Essa classe é responsável por guardar a lógica de manipulação dos cupons de desconto no Pedido.

public class RelacaoCuponsDescontoPedido {

    private List<CupomDescontoPedido> cupons;

    public RelacaoCuponsDescontoPedido() {
        cupons = new ArrayList<>();
        // Aqui eu vou adicionar os cupons que já existem;
        cupons.add(new CupomDescontoPedido("DESC10",
                0.1,
                LocalDateTime.of(2026, 9, 1, 00, 00),
                LocalDateTime.of(2026, 9, 10, 00, 00)));
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
