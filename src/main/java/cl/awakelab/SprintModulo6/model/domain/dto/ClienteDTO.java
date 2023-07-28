package cl.awakelab.SprintModulo6.model.domain.dto;
import lombok.*;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ClienteDTO {
    private int id;
    private String rut;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String afp;
    private String sistemaSalud;
    private String direccion;
    private String comuna;
    private int edad;
}
