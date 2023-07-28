package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PagoRepository extends JpaRepository<Pago, Integer> {
}
