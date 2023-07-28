package cl.awakelab.SprintModulo6.model.persistence.mapper;

import cl.awakelab.SprintModulo6.model.domain.dto.ChecklistDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Checklist;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ChecklistMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "visitaId", target = "visitaId"),
            @Mapping(source = "detalle", target = "detalle"),
            @Mapping(source = "estado", target = "estado"),
    })
    ChecklistDTO toChecklist(Checklist checklist);
    List<ChecklistDTO> toChecklists(List<Checklist> checklist);
    @InheritInverseConfiguration
    Checklist toChecklistdto(ChecklistDTO checklistDTO);






}
