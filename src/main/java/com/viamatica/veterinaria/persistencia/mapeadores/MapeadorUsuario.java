package com.viamatica.veterinaria.persistencia.mapeadores;

import com.viamatica.veterinaria.dominio.Usuario;
import com.viamatica.veterinaria.persistencia.entidades.EntidadUsuario;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring", uses = {MapeadorUsuarioPerfil.class})
public interface MapeadorUsuario {
    @Mappings({
            @Mapping(source = "idUsuario", target = "idUsuario"),
            @Mapping(source = "nombreUsuario", target = "nombreUsuario"),
            @Mapping(source = "contrasenia", target = "contrasenia"),
            @Mapping(source = "estadoUsuario", target = "estadoUsuario"),
            @Mapping(source = "correo", target = "correo"),
            @Mapping(source = "bloqueado", target = "bloqueado"),
            @Mapping(source = "usuarioPerfils", target = "usuarioPerfils")
    })
    Usuario toUsuario(EntidadUsuario entidadUsuario);

    List<Usuario> toUsuarios(List<EntidadUsuario> entidadUsuarios);
    @InheritInverseConfiguration
    @Mapping(target = "fechaActualizacion", ignore = true)
    @Mapping(target = "fechaCreacion", ignore = true)
    EntidadUsuario toEntidadUsuario(Usuario usuario);
}
