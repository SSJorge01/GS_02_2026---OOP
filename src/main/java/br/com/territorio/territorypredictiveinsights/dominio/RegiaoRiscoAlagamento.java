package br.com.territorio.territorypredictiveinsights.dominio;

public class RegiaoRiscoAlagamento extends ComponenteTerritorial {
    private double nivelCriticoMetros;

    public RegiaoRiscoAlagamento(Long id, String nome, String regiao, String status, double nivelCriticoMetros) {
        super(id, nome, regiao, status);
        this.nivelCriticoMetros = nivelCriticoMetros;
    }

    @Override
    public String verificarStatus() {
        return "Região monitorada. Nível crítico: " + nivelCriticoMetros + "m.";
    }

    public double getNivelCriticoMetros() { return nivelCriticoMetros; }
    public void setNivelCriticoMetros(double nivelCriticoMetros) { this.nivelCriticoMetros = nivelCriticoMetros; }
}