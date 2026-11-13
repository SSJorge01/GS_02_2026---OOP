package br.com.territorio.territorypredictiveinsights.dominio;

import br.com.territorio.territorypredictiveinsights.excecao.DadosInvalidosException;

public class SensorNivelAgua implements Sensor {
    @Override
    public double lerValor() {
        double valor = Math.random() * 10;
        if (valor < 0) {
            throw new DadosInvalidosException("Medição de nível de água inválida.");
        }
        return valor;
    }

    @Override
    public boolean verificarFuncionamento() {
        return true;
    }

    @Override
    public String retornarTipo() {
        return "Sensor de Nível de Água (Metros)";
    }
}