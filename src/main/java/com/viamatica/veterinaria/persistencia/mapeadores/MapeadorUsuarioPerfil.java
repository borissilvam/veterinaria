package com.viamatica.veterinaria.persistencia.mapeadores;

import com.viamatica.veterinaria.dominio.UsuarioPerfil;
import com.viamatica.veterinaria.persistencia.entidades.EntidadUsuarioPerfil;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface MapeadorUsuarioPerfil {

    @Mappings({
            @Mapping(source = "idUsuarioPerfil.idPerfil", target = "idPerfil"),
            @Mapping(source = "estadoUsuarioPerfil", target = "estadoUsuarioPerfil")
    })
    UsuarioPerfil toUsuarioPerfil(EntidadUsuarioPerfil entidadUsuarioPerfil);
    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "idUsuarioPerfil.idUsuario", ignore = true),
            @Mapping(target = "entidadPerfil", ignore = true),
            @Mapping(target = "entidadUsuario", ignore = true),
            @Mapping(target = "fechaActualizacion", ignore = true),
            @Mapping(target = "fechaCreacion", ignore = true)
    })
    EntidadUsuarioPerfil toEntidadUsuarioPerfil(UsuarioPerfil usuarioPerfil);
}
