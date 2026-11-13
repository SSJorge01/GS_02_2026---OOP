package br.com.territorio.territorypredictiveinsights.dominio;

import br.com.territorio.territorypredictiveinsights.excecao.DadosInvalidosException;

public class SensorPluviometro implements Sensor {
    @Override
    public double lerValor() {
        double valor = Math.random() * 150;
        if (valor < 0) {
            throw new DadosInvalidosException("Medição de chuva inválida.");
        }
        return valor;
    }

    @Override
    public boolean verificarFuncionamento() {
        return true;
    }

    @Override
    public String retornarTipo() {
        return "Pluviômetro (Volume de Chuva - mm)";
    }
}