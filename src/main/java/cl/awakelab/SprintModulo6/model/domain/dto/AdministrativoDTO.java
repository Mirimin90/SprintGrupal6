package cl.awakelab.SprintModulo6.model.domain.dto;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class AdministrativoDTO {
    private int id;
    private String run;
    private String nombre;
    private String apellido;
    private String correo;
    private String area;
}


