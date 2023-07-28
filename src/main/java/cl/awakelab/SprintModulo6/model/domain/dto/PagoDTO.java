package cl.awakelab.SprintModulo6.model.domain.dto;
import lombok.*;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class PagoDTO {
    private int id;
    private int clienteId;
    private float monto;
    private Date fechaPago;
}
