package cl.awakelab.SprintModulo6.web.service;


import cl.awakelab.SprintModulo6.model.domain.dto.UsuarioDTO;

import java.util.List;
import java.util.Optional;

public interface UsuarioService {
    Optional<List<UsuarioDTO>> findAll();
    Optional <UsuarioDTO> findById(int id);
    Optional<UsuarioDTO> create(UsuarioDTO usuarioDTO);
    Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO);
    boolean delete(int id);

}
