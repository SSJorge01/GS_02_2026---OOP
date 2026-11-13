package br.com.territorio.territorypredictiveinsights.persistencia;

import br.com.territorio.territorypredictiveinsights.dominio.Medicao;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicaoRepository extends JpaRepository<Medicao, Long> {
}