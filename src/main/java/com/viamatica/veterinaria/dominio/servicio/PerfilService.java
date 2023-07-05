package com.viamatica.veterinaria.dominio.servicio;

import com.viamatica.veterinaria.dominio.Perfil;
import com.viamatica.veterinaria.persistencia.PerfilRepositorio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PerfilService {
    @Autowired
    private PerfilRepositorio perfilRepositorio;

    public List<Perfil> obtenerTodos(){
        return perfilRepositorio.obtenerTodos();
    }

    public Optional<Perfil> obtenerPerfil(int idPerfil){
        return perfilRepositorio.obtenerPerfil(idPerfil);
    }

    public Optional<Perfil> obtenerPerfilPorNombre(String nombrePerfil){
        return perfilRepositorio.obtenerPerfilPorNombre(nombrePerfil);
    }

    public Perfil guardar(Perfil perfil){

        if (perfil.getNombrePerfil() == null){
            System.out.println("Service: Nombre del perfil nulo");
        }
        return perfilRepositorio.guardar(perfil);
    }

    public Perfil actualizar(Perfil perfil){
        return perfilRepositorio.actualizar(perfil);
    }

    public boolean eliminar(int idPerfil){
        return obtenerPerfil(idPerfil).map(perfil -> {
                    perfilRepositorio.eliminar(idPerfil);
                    return true;
                }).orElse(false);
    }
}
