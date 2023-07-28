package cl.awakelab.SprintModulo6.model.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.sql.Time;
import java.util.Date;

@Entity
@Table(name="visita")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Visita {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="cliente_id")
    private int clienteId;
    @Column(name ="fecha")
    private Date fecha;
    @Column(name ="hora")
    private Time hora;
    @Column(name ="lugar")
    private String lugar;
    @Column(name ="realizado")
    private int realizado ;
    @Column(name ="detalle")
    private String detalle;
}