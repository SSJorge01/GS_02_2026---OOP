# Territory Predictive Insights - Sistema de Monitoramento Climático e de Alagamentos

Projeto desenvolvido para a **Global Solution (GS 2026.2)** da disciplina de **Object-Oriented Programming (OOP)**.

---

## 1. Público Beneficiado e Tomada de Decisão
* **Quem sofre o problema:** Moradores de comunidades urbanas vulneráveis a enchentes e alagamentos rápidos em períodos de chuvas intensas, além de equipes de defesa civil e associações de bairro.
* **Qual decisão o sistema apoia:** O sistema processa leituras de sensores e emite alertas automáticos (Atenção, Alerta, Crítico) fundamentados em dados históricos persistidos. Ele **apoia** a decisão humana (permitindo que gestores e equipes de apoio evacuem áreas de risco ou acionem bombas de drenagem a tempo), sem tomar decisões autônomas no lugar das pessoas.

---

## 2. Tecnologias e Arquitetura
* **Linguagem:** Java 21 / 26
* **Framework:** Spring Boot 4.x
* **Persistência:** Spring Data JPA com banco de dados em memória **H2** (configurado automaticamente via Spring Boot, sem necessidade de instalação externa)
* **Build:** Maven
* **Testes:** JUnit 5 e Mockito (padrão AAA, `assertThrows` e mocks de repositório)

O projeto segue os princípios de Orientação a Objetos (abstração, encapsulamento, herança e polimorfismo) e arquitetura em camadas (`dominio`, `persistencia`, `servico`, `excecao`).

---

## 3. Padrões de Projeto (Design Patterns)
* **Singleton (`ConfiguracaoSistema`):** Escolhido para gerenciar os limiares críticos de chuva e nível de água da aplicação.
  * *Por que?* Garantimos que exista uma única instância global de configuração compartilhada por todo o sistema em tempo de execução, evitando inconsistências de parâmetros e mantendo o construtor privado.
* **Strategy (`EstrategiaPrevisao`):** Utilizado através de polimorfismo (`PrevisaoMediaMovel` e `PrevisaoComparacao`) para calcular tendências sobre o histórico do banco sem o uso de estruturas condicionais (`if/else` ou `instanceof`).

---

## 4. Reflexão Crítica
1. **Quando o sistema deve se calar?**
   O sistema deve se abster de emitir previsões ou alertas apenas quando houver escassez de dados — por exemplo, com menos de 5 leituras válidas registradas no banco. Nesses casos, a média móvel carece de significância estatística, e o sistema deve sinalizar insuficiência de dados em vez de gerar um falso alarme.
2. **O que os dados não representam?**
   Um único sensor pluviométrico ou de nível de água por região inteira não representa a realidade de pontos isolados. O relevo urbano, bueiros entupidos e variações de pavimentação geram impactos locais que a leitura generalizada do sensor não capta por completo.
3. **Por que o alerta apoia a decisão em vez de decidir por ela?**
   O software processa números e probabilidades, mas carece de contexto humano imediato (como o escoamento específico da via ou prioridade dos locais de evacuação). O alerta atua como um gatilho situacional para que o operador humano tome a decisão final com segurança.

---

## 5. Como Executar o Projeto
1. Certifique-se de ter o **Java JDK** e o **Maven** instalados.
2. Abra o projeto na pasta raíz em uma IDE (ex: IntelliJ IDEA).
3. Execute a classe principal do Spring Boot (`TerritoryPredictiveInsightsApplication.java`) ou rode no terminal:
   `mvn spring-boot:run`
4. Utilize o menu interativo no console para testar sensores, previsões, histórico e limiares.
5. Para rodar os testes automatizados:
   `mvn test`