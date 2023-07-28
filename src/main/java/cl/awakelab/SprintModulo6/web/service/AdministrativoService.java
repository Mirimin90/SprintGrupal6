package cl.awakelab.SprintModulo6.web.service;

import cl.awakelab.SprintModulo6.model.domain.dto.AdministrativoDTO;


import java.util.List;
import java.util.Optional;

public interface AdministrativoService {
    Optional<List<AdministrativoDTO>> findAll();
    Optional <AdministrativoDTO> findById(int id);
    Optional<AdministrativoDTO> create(AdministrativoDTO administrativoDTO);
    Optional<AdministrativoDTO> update(AdministrativoDTO administrativoDTO);
    boolean delete(int id);
}
