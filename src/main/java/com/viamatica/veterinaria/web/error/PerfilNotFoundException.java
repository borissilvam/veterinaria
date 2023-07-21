package com.viamatica.veterinaria.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class PerfilNotFoundException extends RuntimeException{

    public PerfilNotFoundException(){
        super("No se ha encontrado perfiles");
    }
    public PerfilNotFoundException(String nombre){
        super("No se ha encontrado el perfil con el nombre : " + nombre);
    }

    public PerfilNotFoundException(Integer id){
        super("No se ha encontrado el perfil con el ID: " + id);
    }
}
