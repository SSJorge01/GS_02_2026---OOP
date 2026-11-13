package br.com.territorio.territorypredictiveinsights.servico;

import br.com.territorio.territorypredictiveinsights.dominio.Medicao;
import java.util.List;

public class PrevisaoMediaMovel extends EstrategiaPrevisao {

    public PrevisaoMediaMovel() {
        super("Média Móvel:");
    }

    @Override
    public double prever(List<Medicao> historico) {
        if (historico == null || historico.isEmpty()) {
            return 0.0;
        }

        double soma = 0.0;
        for (Medicao m : historico) {
            soma += m.getValor();
        }
        return soma / historico.size();
    }
}