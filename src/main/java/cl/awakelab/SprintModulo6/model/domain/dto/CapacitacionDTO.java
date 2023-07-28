package cl.awakelab.SprintModulo6.model.domain.dto;

import lombok.*;
import lombok.experimental.Accessors;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class CapacitacionDTO {
    private int id;
    private String nombre;
    private String detalle;
    private Date fecha;
    private Time hora;
    private String lugar;
    private Float duracion;
    private int cantidad;
}
