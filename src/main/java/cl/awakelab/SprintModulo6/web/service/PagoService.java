package cl.awakelab.SprintModulo6.web.service;

import cl.awakelab.SprintModulo6.model.domain.dto.PagoDTO;

import java.util.List;
import java.util.Optional;

public interface PagoService {
    Optional<List<PagoDTO>> findAll();
    Optional <PagoDTO> findById(int id);
    Optional<PagoDTO> create(PagoDTO pagoDTO);
    Optional<PagoDTO> update(PagoDTO pagoDTO);
    boolean delete(int id);
}
