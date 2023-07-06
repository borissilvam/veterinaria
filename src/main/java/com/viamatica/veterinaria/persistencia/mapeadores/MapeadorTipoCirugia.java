package com.viamatica.veterinaria.persistencia.mapeadores;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.viamatica.veterinaria.dominio.TipoCirugia;
import com.viamatica.veterinaria.persistencia.entidades.HosTipoCirugia;

@Mapper(componentModel = "spring")
public interface MapeadorTipoCirugia {
    
    @Mappings({
        @Mapping(source = "idHosTipoCirugia", target="idHosTipoCirugia"),
        @Mapping(source = "tipoCirugia", target="tipoCirugia"),
        @Mapping(source = "estadoTipoCirugia", target="estadoTipoCirugia")
    })
    TipoCirugia toTipoCirugia(HosTipoCirugia hosTipoCirugia);

    List<TipoCirugia> toTiposCirugias(List<HosTipoCirugia> hosTipoCirugias);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target="fechaCreacion", ignore = true),
        @Mapping(target="fechaActualizacion", ignore = true),
        @Mapping(target="fechaEliminacion", ignore = true)
    })
    HosTipoCirugia toHosTipoCirugia(TipoCirugia tipoCirugia);

}
