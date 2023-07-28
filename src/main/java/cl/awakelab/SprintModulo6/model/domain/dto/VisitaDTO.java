package cl.awakelab.SprintModulo6.model.domain.dto;
import lombok.*;
import java.sql.Time;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class VisitaDTO {
    private int id;
    private int clienteId;
    private Date fecha;
    private Time hora;
    private String lugar;
    private int realizado ;
    private String detalle;
}
