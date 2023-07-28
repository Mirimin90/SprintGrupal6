package cl.awakelab.SprintModulo6.model.domain.dto;
import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class ProfesionalDTO {
    private int id;
    private String run;
    private String nombre;
    private String apellido;
    private String correo;
    private String telefono;
    private String cargo;
}
