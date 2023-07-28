package cl.awakelab.SprintModulo6.model.domain.service;

import cl.awakelab.SprintModulo6.model.domain.dto.ChecklistDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.ChecklistMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.ChecklistRepository;
import cl.awakelab.SprintModulo6.web.service.ChecklistService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ChecklistServiceImpl implements ChecklistService {
    private final ChecklistRepository repository;
    private final ChecklistMapper mapper;

    public ChecklistServiceImpl(ChecklistRepository repository, ChecklistMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<ChecklistDTO>> findAll() {

        return Optional.of(mapper.toChecklists(repository.findAll()));
    }
    @Override
    public Optional<ChecklistDTO> findById(int id) {
        return repository.findById(id).map(mapper::toChecklist);
    }

    @Override
    public Optional<ChecklistDTO> create(ChecklistDTO checklistDTO)
    {
        return Optional.of(
                mapper.toChecklist(
                        repository.save(
                                mapper.toChecklistdto(checklistDTO)
                        )
                ));
    }
    @Override
    public Optional<ChecklistDTO> update(ChecklistDTO checklistDTO) {
        if (repository.existsById(checklistDTO.getId())){
            return Optional.of(
                    mapper.toChecklist(
                            repository.save(
                                    mapper.toChecklistdto(checklistDTO)
                            )
                    ));
        }
        return Optional.of(new ChecklistDTO());
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
