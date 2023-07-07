package com.viamatica.veterinaria.dominio;

import java.util.Date;


import lombok.Data;

@Data
public class HospitalizacionPaciente {
    private Integer idHospitalizacion;

    private Integer idPaciente;

    private Date fechaIngreso;

    private Date fechaSalida;

    private String motivo;

}
