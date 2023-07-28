package cl.awakelab.SprintModulo6.web.service;

import cl.awakelab.SprintModulo6.model.domain.dto.ChecklistDTO;


import java.util.List;
import java.util.Optional;

public interface ChecklistService {
    Optional<List<ChecklistDTO>> findAll();
    Optional <ChecklistDTO> findById(int id);
    Optional<ChecklistDTO> create(ChecklistDTO checklistDTO);
    Optional<ChecklistDTO> update(ChecklistDTO checklistDTO);
    boolean delete(int id);
}
