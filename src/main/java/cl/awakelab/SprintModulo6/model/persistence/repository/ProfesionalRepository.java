package cl.awakelab.SprintModulo6.model.persistence.repository;


import cl.awakelab.SprintModulo6.model.persistence.entity.Profesional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProfesionalRepository extends JpaRepository<Profesional,Integer> {
}
