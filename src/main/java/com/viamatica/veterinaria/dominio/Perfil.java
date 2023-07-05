package com.viamatica.veterinaria.dominio;

import jakarta.persistence.Column;
import lombok.Data;

@Data
public class Perfil {

    private Integer idPerfil;
    private  String nombrePerfil;
    private String estadoPerfil;
}
