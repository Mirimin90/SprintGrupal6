package cl.awakelab.SprintModulo6.model.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="profesional")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Profesional {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="run")
    private String run;
    @Column(name ="nombre")
    private String nombre;
    @Column(name ="apellido")
    private String apellido;
    @Column(name ="correo")
    private String correo;
    @Column(name ="telefono")
    private String telefono;
    @Column(name ="cargo")
    private String cargo;
}
