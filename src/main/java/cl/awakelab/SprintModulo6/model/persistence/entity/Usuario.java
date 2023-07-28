package cl.awakelab.SprintModulo6.model.persistence.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name="usuario")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public @Data class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="nombre")
    private String nombre;
    @Column(name ="username")
    private String username;
    @Column(name ="password")
    private String password;
    @Column(name ="role")
    private String role;
}
