package br.com.territorio.territorypredictiveinsights.dominio;

import br.com.territorio.territorypredictiveinsights.excecao.DadosInvalidosException;

public class SensorUmidadeSolo implements Sensor {
    @Override
    public double lerValor() {
        double valor = Math.random() * 100;
        if (valor < 0 || valor > 100) {
            throw new DadosInvalidosException("Umidade do solo fora da faixa permitida (0-100%).");
        }
        return valor;
    }

    @Override
    public boolean verificarFuncionamento() {
        return true;
    }

    @Override
    public String retornarTipo() {
        return "Sensor de Umidade do Solo (%)";
    }
}