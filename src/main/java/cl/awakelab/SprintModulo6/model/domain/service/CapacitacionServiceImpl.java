package cl.awakelab.SprintModulo6.model.domain.service;

import cl.awakelab.SprintModulo6.model.domain.dto.CapacitacionDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.CapacitacionMapper;
import cl.awakelab.SprintModulo6.web.service.CapacitacionService;
import cl.awakelab.SprintModulo6.model.persistence.repository.CapacitacionRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CapacitacionServiceImpl implements CapacitacionService {

    private final CapacitacionRepository repository;
    private final CapacitacionMapper mapper;

    public CapacitacionServiceImpl(CapacitacionRepository repository, CapacitacionMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<CapacitacionDTO>> findAll() {
        return Optional.of(mapper.toCapacitaciones(repository.findAll()));
    }
    @Override
    public Optional<CapacitacionDTO> findById(int id) {
        return repository.findById(id).map(mapper::toCapacitacion);
    }

    @Override
    public Optional<CapacitacionDTO> create(CapacitacionDTO capacitacionDTO)
    {
        return Optional.of(
                mapper.toCapacitacion(
                        repository.save(
                                mapper.toCapacitaciondto(capacitacionDTO)
                        )
                ));
    }
    @Override
    public Optional<CapacitacionDTO> update(CapacitacionDTO capacitacionDTO) {
        if (repository.existsById(capacitacionDTO.getId())){
            return Optional.of(
                    mapper.toCapacitacion(
                            repository.save(
                                    mapper.toCapacitaciondto(capacitacionDTO)
                            )
                    ));
        }
        return Optional.of(new CapacitacionDTO());
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
