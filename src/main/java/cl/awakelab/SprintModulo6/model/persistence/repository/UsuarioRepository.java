package cl.awakelab.SprintModulo6.model.persistence.repository;

import cl.awakelab.SprintModulo6.model.persistence.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
}
