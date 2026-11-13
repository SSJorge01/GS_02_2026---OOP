package br.com.territorio.territorypredictiveinsights.dominio;

public interface Sensor {
    double lerValor();
    boolean verificarFuncionamento();
    String retornarTipo();
}