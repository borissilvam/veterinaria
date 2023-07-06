package com.viamatica.veterinaria.dominio;

import java.util.Date;

import lombok.Data;

@Data
public class RevisionDiaria {
 
     
    private Integer idRevisionDiaria;
    
    private Date fechaRevision;
    
    private Integer idPaciente;
    
    private String estadoHosRevisiondiaria;
    

}