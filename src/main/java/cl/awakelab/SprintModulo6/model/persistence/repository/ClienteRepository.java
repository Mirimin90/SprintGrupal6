package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClienteRepository extends JpaRepository<Cliente,Integer> {
}
