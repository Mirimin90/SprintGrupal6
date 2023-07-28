package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Administrativo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AdministrativoRepository extends JpaRepository<Administrativo,Integer> {

}
