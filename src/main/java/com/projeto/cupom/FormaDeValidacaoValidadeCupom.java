package com.projeto.cupom;

import com.projeto.RelacaoCuponsDescontoPedido;
import com.projeto.model.CupomDescontoPedido;

import java.time.LocalDateTime;

public class FormaDeValidacaoValidadeCupom implements IFormaDeValidacao {

    private String codigo;

    public FormaDeValidacaoValidadeCupom(String codigo) {

        if (codigo == null) {
            throw new RuntimeException("O código para a validação da validade do cupom é nulo.");
        }

        this.codigo = codigo;
    }

    @Override
    public boolean seAplica() {
        RelacaoCuponsDescontoPedido cupons = new RelacaoCuponsDescontoPedido();
        CupomDescontoPedido cupom = cupons.buscarCupom(codigo).get();

        if (compararDataHora(cupom.getInicio()) && !compararDataHora(cupom.getFim())) {
            return true;
        }
        return false;
    }

    public boolean compararDataHora(LocalDateTime dataHora) {
        if (dataHora == null) {
            throw new RuntimeException("Data e hora estão nulos.");
        }

        int dia = dataHora.getDayOfMonth();
        int mes = dataHora.getMonthValue();
        int ano = dataHora.getYear();
        int hora = dataHora.getHour();
        int minuto = dataHora.getMinute();

        int diaAtual = LocalDateTime.now().getDayOfMonth();
        int mesAtual = LocalDateTime.now().getMonthValue();
        int anoAtual = LocalDateTime.now().getYear();
        int horaAtual = LocalDateTime.now().getHour();
        int minutoAtual = LocalDateTime.now().getMinute();

        if (
            dia <= diaAtual &&
            mes <= mesAtual &&
            ano <= anoAtual &&
            hora <= horaAtual &&
            minuto <= minutoAtual
        ) { return true; }

        return false;
    }
}
