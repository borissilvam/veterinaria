package com.viamatica.veterinaria.persistencia.entidades;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.io.Serializable;

@Embeddable
@Data
public class UsuarioPerfilPK implements Serializable {

    private Integer idUsuario;
    private Integer idPerfil;

}
