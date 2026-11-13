# 🌧️ Territory Predictive Insights - Sistema de Monitoramento Climático e de Alagamentos

O sistema tem como objetivo monitorar sensores meteorológicos e de nível de água em tempo real, emitir alertas automáticos de risco de enchentes, aplicar estratégias de previsão polimórficas e persistir os dados utilizando arquitetura em camadas com Spring Boot e banco de dados H2.

---

## 🛠️ Tecnologias e Ferramentas Utilizadas
* **Java 21 / 26**
* **Spring Boot 4.x** (Spring Data JPA, H2 Database)
* **Maven** (Gerenciamento de Dependências e Build)
* **JUnit 5 & Mockito** (Testes Unitários automatizados)

---

## 🏛️ Arquitetura do Projeto e Padrões de Projeto (Design Patterns)

O projeto segue estritamente os princípios de Orientação a Objetos e Separation of Concerns (SoC), dividido em camadas limpas:

1. **Camada de Domínio (`dominio`)**: 
   * Entidades de negócio (`Medicao`, `Alerta`).
   * Hierarquia de Sensores (`Sensor` como classe/interface base, e implementações como `SensorPluviometro`, `SensorNivelAgua`, `SensorUmidadeSolo`).
2. **Camada de Persistência (`persistencia`)**:
   * Repositórios Spring Data JPA (`MedicaoRepository`, `AlertaRepository`) integrados com o banco de dados em memória H2.
3. **Camada de Serviço (`servico`)**:
   * `ServicoMonitoramento`: Concentra a regra de negócio principal (validação de sensores, gravação de medições e checagem de limiares críticos para emissão de alertas).
   * `SistemaMonitoramento`: Implementa `CommandLineRunner` para gerenciar a interface de menu interativo via console.
4. **Design Patterns Aplicados**:
   * **Singleton (`ConfiguracaoSistema`)**: Garante uma instância única global para gerenciamento dos limiares críticos de chuva e nível de água da aplicação.
   * **Strategy (`EstrategiaPrevisao`)**: Utiliza polimorfismo para alternar dinamicamente entre diferentes algoritmos de previsão meteorológica (`PrevisaoMediaMovel` e `PrevisaoComparacao`).

---

## 🧪 Testes Automatizados (JUnit 5 + Mockito)

O projeto conta com uma suíte robusta de testes unitários que cobrem:
* Padrão **AAA (Arrange, Act, Assert)**.
* Validação de exceções customizadas (`DadosInvalidosException`) utilizando `assertThrows`.
* Isolamento de componentes e repositórios através de mocks com **Mockito**.

Para executar os testes automatizados pelo terminal:

```bash
mvn test
```

---

## 🚀 Como Executar o Projeto

1. Certifique-se de ter o **Java JDK** e o **Maven** configurados em sua máquina.
2. Clone ou abra o projeto na sua IDE (como IntelliJ IDEA).
3. Execute a classe principal do Spring Boot:
   `src/main/java/br/com/territorio/territorypredictiveinsights/TerritoryPredictiveInsightsApplication.java`
4. O menu interativo do sistema será iniciado automaticamente no console, permitindo:
   * **Opção 1**: Efetuar a leitura dos sensores e registrar medições/alertas.
   * **Opção 2**: Executar previsões baseadas em estratégias polimórficas.
   * **Opção 3**: Consultar o histórico de medições gravadas no banco H2.
   * **Opção 4**: Visualizar os alertas críticos emitidos.
   * **Opção 5**: Gerenciar e atualizar os limiares do sistema via Padrão Singleton.
   * **Opção 0**: Sair do sistema.
