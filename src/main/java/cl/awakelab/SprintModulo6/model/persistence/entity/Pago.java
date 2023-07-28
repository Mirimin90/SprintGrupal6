package cl.awakelab.SprintModulo6.model.persistence.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.Date;

@Entity
@Table(name="pago")
@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class Pago {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name ="id")
    private int id;
    @Column(name ="cliente_id")
    private int clienteId;
    @Column(name ="monto")
    private float monto;
    @Column(name ="fecha_pago")
    private Date fechaPago;
}
