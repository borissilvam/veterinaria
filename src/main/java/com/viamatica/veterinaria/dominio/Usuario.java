package com.viamatica.veterinaria.dominio;

import lombok.Data;

import java.util.List;

@Data
public class Usuario {


    private Integer idUsuario;

    private String nombreUsuario;

    private String contrasenia;

    private String correo;

    private Boolean bloqueado;

    private String estadoUsuario;

    private List<UsuarioPerfil> usuarioPerfils;
}
