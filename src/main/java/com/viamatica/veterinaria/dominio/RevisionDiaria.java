package com.viamatica.veterinaria.dominio;

import java.util.Date;

import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;

import lombok.Data;

@Data
public class RevisionDiaria {
 
     
    private Integer idRevisionDiaria;

    private String detalle;
    
    private Date fechaRevision;
    
    private GesPaciente paciente;
    
    private String estadoHosRevisiondiaria;

}
