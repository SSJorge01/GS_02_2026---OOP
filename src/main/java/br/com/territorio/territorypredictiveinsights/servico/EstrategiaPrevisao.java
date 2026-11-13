package br.com.territorio.territorypredictiveinsights.servico;

import br.com.territorio.territorypredictiveinsights.dominio.Medicao;
import java.util.List;

public abstract class EstrategiaPrevisao {

    protected String nomeEstrategia;

    public EstrategiaPrevisao(String nomeEstrategia) {
        this.nomeEstrategia = nomeEstrategia;
    }

    public abstract double prever(List<Medicao> historico);

    public String getNomeEstrategia() {
        return nomeEstrategia;
    }
}