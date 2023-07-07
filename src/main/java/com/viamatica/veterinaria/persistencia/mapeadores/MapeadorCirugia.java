package com.viamatica.veterinaria.persistencia.mapeadores;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.viamatica.veterinaria.dominio.Cirugia;
import com.viamatica.veterinaria.persistencia.entidades.HosCirugia;

@Mapper(componentModel = "spring")
public interface MapeadorCirugia {
    
    @Mappings({
        @Mapping(source = "idHosCirguia", target = "idHosCirugia"),
        @Mapping(source = "idPaciente", target = "idPaciente"),
        @Mapping(source = "idHosTipoCirugia", target = "idHosTipoCirugia"),
        @Mapping(source = "fechaProgramada", target = "fechaProgramada"),
        @Mapping(source = "idDoctor", target = "idDoctor")
    })
    Cirugia toCirugia(HosCirugia hosCirugia);

    List<Cirugia> toCirugias(List<HosCirugia> hosCirugias);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target="fechaCreacion", ignore = true),
        @Mapping(target="fechaActualizacion", ignore = true),
        @Mapping(target="fechaEliminacion", ignore = true),
        @Mapping(target = "estadoCirugia", ignore = true)
    })
    HosCirugia toHosCirugia(Cirugia cirugia);

}
