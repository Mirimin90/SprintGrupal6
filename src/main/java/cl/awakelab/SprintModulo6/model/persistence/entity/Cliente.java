package cl.awakelab.SprintModulo6.model.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="cliente")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="rut")
    private String rut;
    @Column(name ="nombre")
    private String nombre;
    @Column(name ="apellido")
    private String apellido;
    @Column(name ="correo")
    private String correo;
    @Column(name ="telefono")
    private String telefono;
    @Column(name ="afp")
    private String afp;
    @Column(name ="sistema_salud")
    private String sistemaSalud;
    @Column(name ="direccion")
    private String direccion;
    @Column(name ="comuna")
    private String comuna;
    @Column(name ="edad")
    private int edad;
}
