package cl.awakelab.SprintModulo6.web.service;

import cl.awakelab.SprintModulo6.model.domain.dto.ClienteDTO;

import java.util.List;
import java.util.Optional;

public interface ClienteService {

    Optional<List<ClienteDTO>> findAll();
    Optional <ClienteDTO> findById(int id);
    Optional<ClienteDTO> create(ClienteDTO clienteDTO);
    Optional<ClienteDTO> update(ClienteDTO clienteDTO);
    boolean delete(int id);
}
