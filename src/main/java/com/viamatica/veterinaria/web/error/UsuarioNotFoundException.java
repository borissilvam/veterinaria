package com.viamatica.veterinaria.web.error;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class UsuarioNotFoundException extends RuntimeException{

    public UsuarioNotFoundException(){
        super("No se han encontrado usuarios");
    }

    public UsuarioNotFoundException(Integer id){
        super("No se ha encontrado el usuario con el ID: " + id);
    }

    public UsuarioNotFoundException(String userName){
        super("No se ha encontrado el usuario con el nombre de Usuario: " + userName);
    }
}
