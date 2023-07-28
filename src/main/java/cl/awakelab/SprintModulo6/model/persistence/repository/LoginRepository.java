package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LoginRepository extends JpaRepository<Usuario, String> {
    Optional<Usuario> findByUsername(String s);
}