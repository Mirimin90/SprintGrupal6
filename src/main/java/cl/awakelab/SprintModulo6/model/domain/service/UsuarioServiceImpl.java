package cl.awakelab.SprintModulo6.model.domain.service;


import cl.awakelab.SprintModulo6.model.domain.dto.UsuarioDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.UsuarioMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.UsuarioRepository;
import cl.awakelab.SprintModulo6.web.service.UsuarioService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private final UsuarioRepository repository;
    private final UsuarioMapper mapper;

    public UsuarioServiceImpl(UsuarioRepository repository, UsuarioMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<UsuarioDTO>> findAll() {
        return Optional.of(mapper.toUsuarios(repository.findAll()));
    }
    @Override
    public Optional<UsuarioDTO> findById(int id) {
        return repository.findById(id).map(mapper::toUsuario);
    }

    @Override
    public Optional<UsuarioDTO> create(UsuarioDTO usuarioDTO)
    {
        return Optional.of(
                mapper.toUsuario(
                        repository.save(
                                mapper.toUsuariodto(usuarioDTO)
                        )
                ));
    }
    @Override
    public Optional<UsuarioDTO> update(UsuarioDTO usuarioDTO) {
        if (repository.existsById(usuarioDTO.getId())){
            return Optional.of(
                    mapper.toUsuario(
                            repository.save(
                                    mapper.toUsuariodto(usuarioDTO)
                            )
                    ));
        }
        return Optional.of(new UsuarioDTO());
    }

    @Override
    public boolean delete(int id) {
        if (repository.existsById(id)){
            repository.deleteById(id);
            return true;
        }
        return false;
    }
}



