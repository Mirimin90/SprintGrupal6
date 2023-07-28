package cl.awakelab.SprintModulo6.model.domain.dto;
import lombok.*;

import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ChecklistDTO {
    private int id;
    private int visitaId;
    private String detalle;
    private String estado;
}
