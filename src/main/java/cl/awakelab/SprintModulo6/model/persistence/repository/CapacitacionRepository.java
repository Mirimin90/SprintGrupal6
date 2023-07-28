package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Capacitacion;
import org.springframework.data.jpa.repository.JpaRepository;
public interface CapacitacionRepository extends JpaRepository<Capacitacion, Integer> {
}
