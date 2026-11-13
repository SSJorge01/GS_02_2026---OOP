package br.com.territorio.territorypredictiveinsights.servico;

public class ConfiguracaoSistema {

    private static ConfiguracaoSistema instancia;

    private double limiarChuvaCritica;
    private double limiarNivelAguaCritico;

    private ConfiguracaoSistema() {
        this.limiarChuvaCritica = 100.0;
        this.limiarNivelAguaCritico = 6.0;
    }

    public static synchronized ConfiguracaoSistema getInstancia() {
        if (instancia == null) {
            instancia = new ConfiguracaoSistema();
        }
        return instancia;
    }

    public double getLimiarChuvaCritica() {
        return limiarChuvaCritica;
    }

    public void setLimiarChuvaCritica(double limiarChuvaCritica) {
        this.limiarChuvaCritica = limiarChuvaCritica;
    }

    public double getLimiarNivelAguaCritico() {
        return limiarNivelAguaCritico;
    }

    public void setLimiarNivelAguaCritico(double limiarNivelAguaCritico) {
        this.limiarNivelAguaCritico = limiarNivelAguaCritico;
    }
}