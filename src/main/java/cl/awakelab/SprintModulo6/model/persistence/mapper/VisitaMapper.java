package cl.awakelab.SprintModulo6.model.persistence.mapper;

import cl.awakelab.SprintModulo6.model.domain.dto.VisitaDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Visita;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface VisitaMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "fecha", target = "fecha"),
            @Mapping(source = "hora", target = "hora"),
            @Mapping(source = "lugar", target = "lugar"),
            @Mapping(source = "realizado", target = "realizado"),
            @Mapping(source = "detalle", target = "detalle"),
    })
    VisitaDTO toVisita(Visita visita);
    List<VisitaDTO> toVisitas(List<Visita> visita);
    @InheritInverseConfiguration
    Visita toVisitadto(VisitaDTO visitaDTO);
}
