package com.viamatica.veterinaria.persistencia.mapeadores;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.viamatica.veterinaria.dominio.RevisionDiaria;
import com.viamatica.veterinaria.persistencia.entidades.HosRevisionDiaria;

@Mapper(componentModel = "spring")
public interface MapeadorRevisionDiaria {
    
    @Mappings({
        @Mapping(source = "idRevisionDiaria", target = "idRevisionDiaria"),
        @Mapping(source = "fechaRevision", target = "fechaRevision"),
        @Mapping(source = "paciente", target = "paciente"),
        @Mapping(source = "detalle", target = "detalle"),
        @Mapping(source = "estadoHosRevisiondiaria", target = "estadoHosRevisiondiaria")
    })
    RevisionDiaria toRevisionDiaria(HosRevisionDiaria hosRevisionDiaria);


    List<RevisionDiaria> toRevisionDiarias(List<HosRevisionDiaria> hosRevisionDiarias);


    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target="fechaCreacion", ignore = true),
        @Mapping(target="fechaActualizacion", ignore = true),
        @Mapping(target="fechaEliminacion", ignore = true)
    })
    HosRevisionDiaria toHosRevisionDiaria(RevisionDiaria revisionDiaria);

}
