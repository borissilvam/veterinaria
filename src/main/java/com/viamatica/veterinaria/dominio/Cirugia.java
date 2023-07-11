
package com.viamatica.veterinaria.dominio;

import java.util.Date;

import lombok.Data;

@Data
public class Cirugia  {

    private Integer idHosCirugia;
    
    private Integer idPaciente;

    private Integer idHosTipoCirugia;

    private Date fechaProgramada;

    private Integer idDoctor;

    private String estadoCirugia;
    
}
