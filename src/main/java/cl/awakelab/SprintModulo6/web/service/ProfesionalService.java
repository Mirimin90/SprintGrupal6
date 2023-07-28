package cl.awakelab.SprintModulo6.web.service;


import cl.awakelab.SprintModulo6.model.domain.dto.ProfesionalDTO;

import java.util.List;
import java.util.Optional;

public interface ProfesionalService {
    Optional<List<ProfesionalDTO>> findAll();
    Optional <ProfesionalDTO> findById(int id);
    Optional<ProfesionalDTO> create(ProfesionalDTO profesionalDTO);
    Optional<ProfesionalDTO> update(ProfesionalDTO profesionalDTO);
    boolean delete(int id);
}
