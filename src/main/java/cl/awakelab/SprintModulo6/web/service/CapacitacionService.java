package cl.awakelab.SprintModulo6.web.service;

import cl.awakelab.SprintModulo6.model.domain.dto.CapacitacionDTO;
import java.util.List;
import java.util.Optional;

public interface CapacitacionService {

    Optional<List<CapacitacionDTO>> findAll();
    Optional <CapacitacionDTO> findById(int id);
    Optional<CapacitacionDTO> create(CapacitacionDTO capacitacionDTO);
    Optional<CapacitacionDTO> update(CapacitacionDTO capacitacionDTO);
    boolean delete(int id);

}

