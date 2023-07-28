package cl.awakelab.SprintModulo6.model.domain.service;

import cl.awakelab.SprintModulo6.model.domain.dto.VisitaDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.VisitaMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.VisitaRepository;
import cl.awakelab.SprintModulo6.web.service.VisitaService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VisitaServiceImpl implements VisitaService {

    private final VisitaRepository repository;
    private final VisitaMapper mapper;

    public VisitaServiceImpl(VisitaRepository repository, VisitaMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<VisitaDTO>> findAll() {
        return Optional.of(mapper.toVisitas(repository.findAll()));
    }
    @Override
    public Optional<VisitaDTO> findById(int id) {
        return repository.findById(id).map(mapper::toVisita);
    }

    @Override
    public Optional<VisitaDTO> create(VisitaDTO visitaDTO)
    {
        return Optional.of(
                mapper.toVisita(
                        repository.save(
                                mapper.toVisitadto(visitaDTO)
                        )
                ));
    }
    @Override
    public Optional<VisitaDTO> update(VisitaDTO visitaDTO) {
        if (repository.existsById(visitaDTO.getId())){
            return Optional.of(
                    mapper.toVisita(
                            repository.save(
                                    mapper.toVisitadto(visitaDTO)
                            )
                    ));
        }
        return Optional.of(new VisitaDTO());
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
