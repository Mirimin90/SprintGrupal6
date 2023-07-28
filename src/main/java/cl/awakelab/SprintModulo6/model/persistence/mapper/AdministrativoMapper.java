package cl.awakelab.SprintModulo6.model.persistence.mapper;


import cl.awakelab.SprintModulo6.model.domain.dto.AdministrativoDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Administrativo;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface AdministrativoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "run", target = "run"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "area", target = "area"),
    })
    AdministrativoDTO toAdministrativo(Administrativo administrativo);
    List<AdministrativoDTO> toAdministrativos(List<Administrativo> administrativo);
    @InheritInverseConfiguration
    Administrativo toAdministrativodto(AdministrativoDTO AdministrativoDTO);
}
