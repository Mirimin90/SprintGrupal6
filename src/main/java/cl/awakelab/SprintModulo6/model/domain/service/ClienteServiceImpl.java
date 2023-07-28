package cl.awakelab.SprintModulo6.model.domain.service;
import cl.awakelab.SprintModulo6.model.domain.dto.ClienteDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.ClienteMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.ClienteRepository;
import cl.awakelab.SprintModulo6.web.service.ClienteService;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ClienteServiceImpl implements ClienteService {
    private final ClienteRepository repository;
    private final ClienteMapper mapper;

    public ClienteServiceImpl(ClienteRepository repository, ClienteMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<ClienteDTO>> findAll() {
        return Optional.of(mapper.toClientes(repository.findAll()));
    }
    @Override
    public Optional<ClienteDTO> findById(int id) {
        return repository.findById(id).map(mapper::toCliente);
    }

    @Override
    public Optional<ClienteDTO> create(ClienteDTO clienteDTO)
    {
        return Optional.of(
                mapper.toCliente(
                        repository.save(
                                mapper.toClientedto(clienteDTO)
                        )
                ));
    }
    @Override
    public Optional<ClienteDTO> update(ClienteDTO clienteDTO) {
        if (repository.existsById(clienteDTO.getId())){
            return Optional.of(
                    mapper.toCliente(
                            repository.save(
                                    mapper.toClientedto(clienteDTO)
                            )
                    ));
        }
        return Optional.of(new ClienteDTO());
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
