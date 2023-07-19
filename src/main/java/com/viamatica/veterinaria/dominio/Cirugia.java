
package com.viamatica.veterinaria.dominio;

import java.time.LocalDateTime;

import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;
import com.viamatica.veterinaria.persistencia.entidades.HisDoctor;

import lombok.Data;

@Data
public class Cirugia  {

    private Integer idHosCirugia;
    
    private GesPaciente paciente;

    private TipoCirugia tipoCirugia;

    private LocalDateTime fechaProgramada;

    private HisDoctor doctor;

    private String estadoCirugia;
    
}
