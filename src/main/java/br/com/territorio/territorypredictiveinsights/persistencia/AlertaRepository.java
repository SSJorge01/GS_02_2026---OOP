package br.com.territorio.territorypredictiveinsights.persistencia;

import br.com.territorio.territorypredictiveinsights.dominio.Alerta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AlertaRepository extends JpaRepository<Alerta, Long> {
}