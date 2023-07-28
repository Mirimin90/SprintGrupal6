package cl.awakelab.SprintModulo6.model.domain.service;

import cl.awakelab.SprintModulo6.model.domain.dto.PagoDTO;
import cl.awakelab.SprintModulo6.model.persistence.mapper.PagoMapper;
import cl.awakelab.SprintModulo6.model.persistence.repository.PagoRepository;
import cl.awakelab.SprintModulo6.web.service.PagoService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoServiceImpl implements PagoService {

    private final PagoRepository repository;
    private final PagoMapper mapper;

    public PagoServiceImpl(PagoRepository repository, PagoMapper mapper) {
        this.repository = repository;
        this.mapper = mapper;
    }

    public Optional<List<PagoDTO>> findAll() {

        return Optional.of(mapper.toPagos(repository.findAll()));
    }
    @Override
    public Optional<PagoDTO> findById(int id) {
        return repository.findById(id).map(mapper::toPago);
    }

    @Override
    public Optional<PagoDTO> create(PagoDTO pagoDTO)
    {
        return Optional.of(
                mapper.toPago(
                        repository.save(
                                mapper.toPagodto(pagoDTO)
                        )
                ));
    }
    @Override
    public Optional<PagoDTO> update(PagoDTO pagoDTO) {
        if (repository.existsById(pagoDTO.getId())){
            return Optional.of(
                    mapper.toPago(
                            repository.save(
                                    mapper.toPagodto(pagoDTO)
                            )
                    ));
        }
        return Optional.of(new PagoDTO());
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
