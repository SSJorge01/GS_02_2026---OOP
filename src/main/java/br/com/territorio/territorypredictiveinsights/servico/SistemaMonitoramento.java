package br.com.territorio.territorypredictiveinsights.servico;

import br.com.territorio.territorypredictiveinsights.dominio.*;
import br.com.territorio.territorypredictiveinsights.excecao.DadosInvalidosException;
import br.com.territorio.territorypredictiveinsights.persistencia.AlertaRepository;
import br.com.territorio.territorypredictiveinsights.persistencia.MedicaoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Scanner;

@Component
public class SistemaMonitoramento implements CommandLineRunner {

    @Autowired
    private ServicoMonitoramento servico;

    @Autowired
    private MedicaoRepository medicaoRepository;

    @Autowired
    private AlertaRepository alertaRepository;

    @Override
    public void run(String... args) {
        rodarMenu();
    }

    public void rodarMenu() {
        Scanner scanner = new Scanner(System.in);
        int opcao = -1;

        while (opcao != 0) {
            System.out.println("\n--- TERRITORY PREDICTIVE INSIGHTS - CLIMA LOCAL ---");
            System.out.println("1. Verificar Sensores e Registrar Medição");
            System.out.println("2. Rodar Previsões");
            System.out.println("3. Ver Histórico de Medições");
            System.out.println("4. Ver Alertas Emitidos");
            System.out.println("5. Gerenciar Limiares");
            System.out.println("0. Sair");
            System.out.print("Escolha uma opção: ");

            try {
                opcao = scanner.nextInt();
                scanner.nextLine();

                switch (opcao) {
                    case 1:
                        Sensor sensor1 = new SensorPluviometro();
                        Sensor sensor2 = new SensorNivelAgua();
                        Sensor sensor3 = new SensorUmidadeSolo();

                        servico.processarLeituraSensor(sensor1);
                        servico.processarLeituraSensor(sensor2);
                        servico.processarLeituraSensor(sensor3);
                        System.out.println("Leituras dos sensores efetuadas e gravadas com sucesso!");
                        break;

                    case 2:
                        EstrategiaPrevisao est1 = new PrevisaoMediaMovel();
                        EstrategiaPrevisao est2 = new PrevisaoComparacao();

                        System.out.println("Resultado (" + est1.getNomeEstrategia() + "): " + servico.executarPrevisao(est1));
                        System.out.println("Resultado (" + est2.getNomeEstrategia() + "): " + servico.executarPrevisao(est2));
                        break;

                    case 3:
                        System.out.println("--- HISTÓRICO DE MEDIÇÕES ---");
                        medicaoRepository.findAll().forEach(m ->
                                System.out.println(m.getDataHora() + " | " + m.getTipoSensor() + " | Valor: " + m.getValor())
                        );
                        break;

                    case 4:
                        System.out.println("--- ALERTAS EMITIDOS ---");
                        alertaRepository.findAll().forEach(a ->
                                System.out.println(a.getDataHora() + " | [" + a.getNivel() + "] " + a.getMensagem())
                        );
                        break;

                    case 5:
                        ConfiguracaoSistema config = ConfiguracaoSistema.getInstancia();
                        System.out.println("Limiar de Chuva Crítica atual: " + config.getLimiarChuvaCritica());
                        System.out.print("Novo limiar de chuva: ");
                        double novoLimiar = scanner.nextDouble();
                        config.setLimiarChuvaCritica(novoLimiar);
                        System.out.println("Limiar atualizado com sucesso!");
                        break;

                    case 0:
                        System.out.println("Encerrando o sistema...");
                        break;

                    default:
                        System.out.println("Opção inválida!");
                }
            } catch (DadosInvalidosException e) {
                System.out.println("[ERRO DE NEGÓCIO]: " + e.getMessage());
            } catch (Exception e) {
                System.out.println("[ERRO]: Entrada inválida ou falha na execução.");
                scanner.nextLine();
            }
        }
        scanner.close();
    }
}