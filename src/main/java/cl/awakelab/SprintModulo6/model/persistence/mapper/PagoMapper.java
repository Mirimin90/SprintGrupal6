package cl.awakelab.SprintModulo6.model.persistence.mapper;

import cl.awakelab.SprintModulo6.model.domain.dto.PagoDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Pago;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PagoMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "clienteId", target = "clienteId"),
            @Mapping(source = "monto", target = "monto"),
            @Mapping(source = "fechaPago", target = "fechaPago"),
    })
    PagoDTO toPago(Pago pago);
    List<PagoDTO> toPagos(List<Pago> pago);
    @InheritInverseConfiguration
    Pago toPagodto(PagoDTO pagoDTO);
}
