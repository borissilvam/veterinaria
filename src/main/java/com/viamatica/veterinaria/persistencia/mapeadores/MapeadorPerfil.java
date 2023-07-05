package com.viamatica.veterinaria.persistencia.mapeadores;

import com.viamatica.veterinaria.dominio.Perfil;
import com.viamatica.veterinaria.persistencia.entidades.EntidadPerfil;
import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import java.util.List;

@Mapper(componentModel = "spring")
public interface MapeadorPerfil {

    @Mappings({
            @Mapping(source = "idPerfil", target = "idPerfil"),
            @Mapping(source = "nombrePerfil", target = "nombrePerfil"),
            @Mapping(source = "estadoPerfil", target = "estadoPerfil")
    })
    Perfil toPerfil(EntidadPerfil entidadPerfil);

    List<Perfil> toPerfiles(List<EntidadPerfil> entidadPerfils);

    @InheritInverseConfiguration
    @Mappings({
            @Mapping(target = "fechaCreacion", ignore = true),
            @Mapping(target = "fechaActualizacion", ignore = true)
    })
    EntidadPerfil toEntidadPerfil(Perfil perfil);
}
