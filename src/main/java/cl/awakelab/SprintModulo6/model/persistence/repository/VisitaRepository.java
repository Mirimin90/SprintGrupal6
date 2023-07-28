package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Visita;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VisitaRepository extends JpaRepository<Visita, Integer> {
}
