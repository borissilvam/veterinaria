package com.viamatica.veterinaria.dominio;

import java.time.LocalDateTime;
import java.util.Date;

import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;

import lombok.Data;

@Data
public class HospitalizacionPaciente {
    private Integer idHospitalizacion;

    private GesPaciente paciente;

    private LocalDateTime fechaIngreso;

    private LocalDateTime fechaSalida;

    private String motivo;

    private String estadoHosPaciente;
}
