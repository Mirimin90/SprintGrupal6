package cl.awakelab.SprintModulo6.model.persistence.mapper;

import cl.awakelab.SprintModulo6.model.domain.dto.UsuarioDTO;
import cl.awakelab.SprintModulo6.model.persistence.entity.Usuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface UsuarioMapper {
    @Mappings({
            @Mapping(source = "id", target = "id"),
            @Mapping(source = "nombre", target = "nombre"),
            @Mapping(source = "username", target = "username"),
            @Mapping(source = "password", target = "password"),
            @Mapping(source = "role", target = "role"),
    })
    UsuarioDTO toUsuario(Usuario usuario);
    List<UsuarioDTO> toUsuarios(List<Usuario> usuario);
    @InheritInverseConfiguration
    Usuario toUsuariodto(UsuarioDTO usuarioDTO);
}
