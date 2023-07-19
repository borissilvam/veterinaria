package com.viamatica.veterinaria.web;


import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;

import lombok.Data;

@Data
public class RespuestaServidor {
    private HttpStatus status;
    private List<String> mensajes;

    public RespuestaServidor()
    {
        mensajes = new ArrayList<>();
    }

    public RespuestaServidor(HttpStatus status)
    {
        this.status = status;
        mensajes = new ArrayList<>();
    }
    public RespuestaServidor(HttpStatus status, String mensaje)
    {
        this.status = status;
        mensajes = new ArrayList<>();
        mensajes.add(mensaje);
    }

    public void anadirMensaje(String mensaje)
    {
        mensajes.add(mensaje);
    }
}
