package br.com.territorio.territorypredictiveinsights.servico;

import br.com.territorio.territorypredictiveinsights.dominio.Medicao;
import java.util.List;

public class PrevisaoComparacao extends EstrategiaPrevisao {

    public PrevisaoComparacao() {
        super("Comparação com Período Anterior:");
    }

    @Override
    public double prever(List<Medicao> historico) {
        if (historico == null || historico.size() < 2) {
            return 0.0;
        }
        double ultimo = historico.get(historico.size() - 1).getValor();
        double penultimo = historico.get(historico.size() - 2).getValor();

        return ultimo + (ultimo - penultimo) * 0.1;
    }
}