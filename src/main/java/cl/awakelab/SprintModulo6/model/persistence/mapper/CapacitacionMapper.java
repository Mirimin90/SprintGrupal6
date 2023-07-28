package cl.awakelab.SprintModulo6.model.persistence.mapper;

import cl.awakelab.SprintModulo6.model.domain.dto.CapacitacionDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Capacitacion;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CapacitacionMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "detalle", target = "detalle"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "hora", target = "hora"),
            @Mapping(source = "lugar", target = "lugar"),
            @Mapping(source = "duracion", target = "duracion"),
            @Mapping(source = "cantidad", target = "cantidad"),
    })
    CapacitacionDTO toCapacitacion(Capacitacion capacitacion);
    List<CapacitacionDTO> toCapacitaciones(List<Capacitacion> capacitacion);
    @InheritInverseConfiguration
    Capacitacion toCapacitaciondto(CapacitacionDTO capacitacionDTO);
}
