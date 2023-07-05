package com.viamatica.veterinaria.persistencia.crud;

import com.viamatica.veterinaria.persistencia.entidades.EntidadPerfil;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EntidadPerfilCrudRepositorio extends JpaRepository<EntidadPerfil, Integer> {

    Optional<EntidadPerfil> findByNombrePerfil(String nombrePerfil);
}
