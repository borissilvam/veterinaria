package com.viamatica.veterinaria.persistencia.mapeadores;

import java.util.List;

import org.mapstruct.InheritInverseConfiguration;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

import com.viamatica.veterinaria.dominio.HospitalizacionPaciente;
import com.viamatica.veterinaria.persistencia.entidades.HosHospitalizacionPaciente;

@Mapper(componentModel = "spring")
public interface MapeadorHospitalizacionPaciente {
    
    @Mappings({
        @Mapping(source = "idHospitalizacion", target = "idHospitalizacion"),
        @Mapping(source = "paciente", target = "paciente"),
        @Mapping(source = "fechaIngreso", target = "fechaIngreso"),
        @Mapping(source = "fechaSalida", target = "fechaSalida"),
        @Mapping(source = "motivo", target = "motivo"),
        @Mapping(source = "estadoHosPaciente", target = "estadoHosPaciente")
    })
    HospitalizacionPaciente toHospitalizacionPaciente(HosHospitalizacionPaciente hosHospitalizacionPaciente);

    List<HospitalizacionPaciente> toHospitalizacionPacientes(List<HosHospitalizacionPaciente> hosHospitalizacionPacientes);

    @InheritInverseConfiguration
    @Mappings({
        @Mapping(target="fechaCreacion", ignore = true),
        @Mapping(target="fechaActualizacion", ignore = true),
        @Mapping(target="fechaEliminacion", ignore = true)
    })
    HosHospitalizacionPaciente toHosHospitalizacionPaciente(HospitalizacionPaciente hospitalizacionPaciente);

}
