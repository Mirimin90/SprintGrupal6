package cl.awakelab.SprintModulo6.model.domain.dto;

import lombok.*;
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class UsuarioDTO {
    private int id;
    private String nombre;
    private String username;
    private String password;
    private String role;
}
