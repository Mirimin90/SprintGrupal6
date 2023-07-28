package cl.awakelab.SprintModulo6.web.service;

import cl.awakelab.SprintModulo6.model.domain.dto.VisitaDTO;

import java.util.List;
import java.util.Optional;

public interface VisitaService {
    Optional<List<VisitaDTO>> findAll();
    Optional <VisitaDTO> findById(int id);
    Optional<VisitaDTO> create(VisitaDTO visitaDTO);
    Optional<VisitaDTO> update(VisitaDTO visitaDTO);
    boolean delete(int id);
}
