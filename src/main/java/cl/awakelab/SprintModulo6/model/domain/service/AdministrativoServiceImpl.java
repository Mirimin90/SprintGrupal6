package cl.awakelab.SprintModulo6.model.domain.service;

import cl.awakelab.SprintModulo6.model.domain.dto.AdministrativoDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.AdministrativoMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.AdministrativoRepository;
import cl.awakelab.SprintModulo6.web.service.AdministrativoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdministrativoServiceImpl implements AdministrativoService {
    private final AdministrativoRepository repository;
    private final AdministrativoMapper mapper;

    public AdministrativoServiceImpl(AdministrativoRepository repository, AdministrativoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    @Override
    public Optional<List<AdministrativoDTO>> findAll() {
        return Optional.of(mapper.toAdministrativos(repository.findAll()));
    }
    @Override
    public Optional<AdministrativoDTO> findById(int id) {
        return repository.findById(id).map(mapper::toAdministrativo);
    }

    @Override
    public Optional<AdministrativoDTO> create(AdministrativoDTO administrativoDTO)
    {
        return Optional.of(
                mapper.toAdministrativo(
                        repository.save(
                                mapper.toAdministrativodto(administrativoDTO)
                        )
                ));
    }
    @Override
    public Optional<AdministrativoDTO> update(AdministrativoDTO administrativoDTO) {
        if (repository.existsById(administrativoDTO.getId())){
            return Optional.of(
                    mapper.toAdministrativo(
                            repository.save(
                                    mapper.toAdministrativodto(administrativoDTO)
                            )
                    ));
        }
        return Optional.of(new AdministrativoDTO());
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
