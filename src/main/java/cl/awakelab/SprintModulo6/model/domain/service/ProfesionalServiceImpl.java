package cl.awakelab.SprintModulo6.model.domain.service;

import cl.awakelab.SprintModulo6.model.domain.dto.ClienteDTO;
import cl.awakelab.SprintModulo6.model.domain.dto.ProfesionalDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.ProfesionalMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.ProfesionalRepository;
import cl.awakelab.SprintModulo6.web.service.ProfesionalService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProfesionalServiceImpl implements ProfesionalService {
    private final ProfesionalRepository repository;
    private final ProfesionalMapper mapper;

    public ProfesionalServiceImpl(ProfesionalRepository repository, ProfesionalMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<ProfesionalDTO>> findAll() {
        return Optional.of(mapper.toProfesionales(repository.findAll()));
    }
    @Override
    public Optional<ProfesionalDTO> findById(int id) {
        return repository.findById(id).map(mapper::toProfesional);
    }

    @Override
    public Optional<ProfesionalDTO> create(ProfesionalDTO profesionalDTO)
    {
        return Optional.of(
                mapper.toProfesional(
                        repository.save(
                                mapper.toProfesionaldto(profesionalDTO)
                        )
                ));
    }
    @Override
    public Optional<ProfesionalDTO> update(ProfesionalDTO profesionalDTO) {
        if (repository.existsById(profesionalDTO.getId())){
            return Optional.of(
                    mapper.toProfesional(
                            repository.save(
                                    mapper.toProfesionaldto(profesionalDTO)
                            )
                    ));
        }
        return Optional.of(new ProfesionalDTO());
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
