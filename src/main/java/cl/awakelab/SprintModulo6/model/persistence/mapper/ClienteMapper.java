package cl.awakelab.SprintModulo6.model.persistence.mapper;


import cl.awakelab.SprintModulo6.model.domain.dto.ClienteDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Cliente;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface ClienteMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "rut", target = "rut"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "apellido", target = "apellido"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "telefono", target = "telefono"),
            @Mapping(source = "afp", target = "afp"),
            @Mapping(source = "sistemaSalud", target = "sistemaSalud"),
            @Mapping(source = "direccion", target = "direccion"),
            @Mapping(source = "comuna", target = "comuna"),
            @Mapping(source = "edad", target = "edad"),
    })
    ClienteDTO toCliente(Cliente cliente);
    List<ClienteDTO> toClientes(List<Cliente> cliente);
    @InheritInverseConfiguration
    Cliente toClientedto(ClienteDTO clienteDTO);


}
