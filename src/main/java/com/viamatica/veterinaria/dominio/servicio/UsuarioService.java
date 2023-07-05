package com.viamatica.veterinaria.dominio.servicio;

import com.viamatica.veterinaria.dominio.Usuario;
import com.viamatica.veterinaria.persistencia.UsuarioRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UsuarioService {
    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    public List<Usuario> obtenerTodos(){
        return usuarioRepositorio.obtenerTodos();
    }

    public Optional<Usuario> obtenerUsuario(int idUsuario){
        return usuarioRepositorio.obtenerUsuario(idUsuario);
    }

    public Usuario guardar(Usuario usuario){
        return usuarioRepositorio.guardar(usuario);
    }

    public Usuario actualizar(Usuario usuario){
        return usuarioRepositorio.actualizar(usuario);
    }

    public boolean desbloquear(int idUsuario){
        return obtenerUsuario(idUsuario).map(usuario -> {
            usuarioRepositorio.desbloquear(idUsuario);
            return true;
        }).orElse(false);
    }
    public boolean eliminar(int idUsuario){
        return obtenerUsuario(idUsuario).map(usuario -> {
            usuarioRepositorio.eliminar(idUsuario);
            return true;
        }).orElse(false);
    }
    public Optional<Usuario> obtenerUsuarioPorNombre(String nombreUsuario){
        return usuarioRepositorio.obtenerUsuarioPorNombre(nombreUsuario);
    }
}
