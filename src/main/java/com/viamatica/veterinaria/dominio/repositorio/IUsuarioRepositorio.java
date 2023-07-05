package com.viamatica.veterinaria.dominio.repositorio;

import com.viamatica.veterinaria.dominio.Usuario;

import java.util.List;
import java.util.Optional;

public interface IUsuarioRepositorio {
    List<Usuario> obtenerTodos();
    Optional<Usuario> obtenerUsuario(int idUsuario);
    Usuario guardar(Usuario usuario);

    Optional<Usuario> obtenerUsuarioPorNombre(String nombreUsuario);

    Optional<List<Usuario>> obtenerUsuariosPorPerfil(int idPerfil);

    Usuario actualizar(Usuario usuario);

    void desbloquear(int idUsuario);
    void eliminar(int id);
}
