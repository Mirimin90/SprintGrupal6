package cl.awakelab.SprintModulo6.model.persistence.mapper;

import cl.awakelab.SprintModulo6.model.domain.dto.ProfesionalDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Profesional;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ProfesionalMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "run", target = "run"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "cargo", target = "cargo"),

    })
    ProfesionalDTO toProfesional(Profesional profesional);
    List<ProfesionalDTO> toProfesionales(List<Profesional> profesional);
    @InheritInverseConfiguration
    Profesional toProfesionaldto(ProfesionalDTO profesionalDTO);
}
