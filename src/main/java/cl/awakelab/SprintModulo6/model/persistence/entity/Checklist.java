package cl.awakelab.SprintModulo6.model.persistence.entity;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Entity
@Table(name="chequeo")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Checklist {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="visita_id")
    private int visitaId;
    @Column(name ="detalle")
    private String detalle;
    @Column(name ="estado")
    private String estado;
}
