package cl.awakelab.SprintModulo6.model.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="capacitacion")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Capacitacion {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="nombre")
    private String nombre;
    @Column(name ="detalle")
    private String detalle;
    @Column(name ="fecha")
    private Date fecha;
    @Column(name ="hora")
    private Time hora;
    @Column(name ="lugar")
    private String lugar;
    @Column(name ="duracion")
    private float duracion;
    @Column(name ="cantidad")
    private int cantidad;
}




