package com.viamatica.veterinaria.dominio.repositorio;

import com.viamatica.veterinaria.dominio.Perfil;

import java.util.List;
import java.util.Optional;

public interface IPerfilRepositorio {
    List<Perfil> obtenerTodos();
    Optional<Perfil> obtenerPerfil(int idPerfil);

    Optional<Perfil> obtenerPerfilPorNombre(String nombrePerfil);

    Perfil actualizar(Perfil perfil);

    Perfil guardar(Perfil perfil);

    void eliminar(int idPerfil);


}
