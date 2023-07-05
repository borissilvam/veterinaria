package com.viamatica.veterinaria.persistencia.crud;

import com.viamatica.veterinaria.persistencia.entidades.EntidadUsuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EntidadUsuarioCrudRepositorio extends JpaRepository<EntidadUsuario, Integer> {

    Optional<EntidadUsuario> findByNombreUsuario(String nombreUsuario);




}
