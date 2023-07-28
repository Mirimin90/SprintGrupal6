package cl.awakelab.SprintModulo6.model.persistence.entity;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name="administrativo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Administrativo {
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
    @Column(name ="area")
    private String area;
}
