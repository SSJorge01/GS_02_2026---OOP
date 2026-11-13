package br.com.territorio.territorypredictiveinsights.servico;

import br.com.territorio.territorypredictiveinsights.dominio.*;
import br.com.territorio.territorypredictiveinsights.excecao.DadosInvalidosException;
import br.com.territorio.territorypredictiveinsights.persistencia.AlertaRepository;
import br.com.territorio.territorypredictiveinsights.persistencia.MedicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ServicoMonitoramento {

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    public Medicao processarLeituraSensor(Sensor sensor) {
        if (!sensor.verificarFuncionamento()) {
            throw new DadosInvalidosException("Sensor indisponível: " + sensor.retornarTipo());
        }

        double valorLido = sensor.lerValor();

        if (valorLido < 0) {
            throw new DadosInvalidosException("Valor inválido gerado pelo sensor.");
        }

        Medicao medicao = new Medicao(sensor.retornarTipo(), valorLido);
        medicaoRepository.save(medicao);

        verificarAlertas(medicao);

        return medicao;
    }

    private void verificarAlertas(Medicao medicao) {
        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
        String nivel = null;
        String mensagem = null;

        if (medicao.getTipoSensor().contains("Pluviômetro") && medicao.getValor() > config.getLimiarChuvaCritica()) {
            nivel = "CRÍTICO";
            mensagem = "Risco de alagamento! Volume de chuva acima do limiar: " + medicao.getValor() + " mm.";
        } else if (medicao.getTipoSensor().contains("Nível de Água") && medicao.getValor() > config.getLimiarNivelAguaCritico()) {
            nivel = "ALERTA";
            mensagem = "Nível do rio elevado: " + medicao.getValor() + " metros.";
        }

        if (nivel != null) {
            Alerta alerta = new Alerta(nivel, mensagem);
            alertaRepository.save(alerta);
        }
    }

    public double executarPrevisao(EstrategiaPrevisao estrategia) {
        List<Medicao> historico = medicaoRepository.findAll();
        return estrategia.prever(historico);
    }
}