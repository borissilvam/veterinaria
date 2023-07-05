package com.viamatica.veterinaria.persistencia;

import com.viamatica.veterinaria.dominio.Perfil;
import com.viamatica.veterinaria.dominio.repositorio.IPerfilRepositorio;
import com.viamatica.veterinaria.persistencia.crud.EntidadPerfilCrudRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.EntidadPerfil;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorPerfil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class PerfilRepositorio implements IPerfilRepositorio {
    @Autowired
    private EntidadPerfilCrudRepositorio entidadPerfilCrudRepositorio;
    @Autowired
    private MapeadorPerfil mappeador;
    @Override
    public List<Perfil> obtenerTodos(){

        return mappeador.toPerfiles(entidadPerfilCrudRepositorio.findAll()) ;
    }
    @Override
    public Optional<Perfil> obtenerPerfil(int idPerfil){
        Optional<EntidadPerfil> perfil = entidadPerfilCrudRepositorio.findById(idPerfil);
        return perfil.map(mappeador::toPerfil )  ;
    }

    @Override
    public Optional<Perfil> obtenerPerfilPorNombre(String nombrePerfil) {
        Optional<EntidadPerfil> perfil = entidadPerfilCrudRepositorio.findByNombrePerfil(nombrePerfil);
        return perfil.map(mappeador::toPerfil);
    }

    @Override
    public Perfil actualizar(Perfil perfil) {
        EntidadPerfil perfil2 = mappeador.toEntidadPerfil(perfil);

        EntidadPerfil perfil1 = entidadPerfilCrudRepositorio.getReferenceById(perfil.getIdPerfil());
        perfil1.setNombrePerfil(perfil2.getNombrePerfil());
        perfil1.setEstadoPerfil(perfil2.getEstadoPerfil());

        return mappeador.toPerfil(entidadPerfilCrudRepositorio.save(perfil1)) ;
    }

    @Override
    public Perfil guardar(Perfil perfil) {
        if (perfil.getNombrePerfil() == null){
            System.out.println("Repositorio 1: Nombre del perfil nulo");
        }
        EntidadPerfil entidadPerfil = mappeador.toEntidadPerfil(perfil);

        if (entidadPerfil.getNombrePerfil() == null){
            System.out.println("Repositorio 2: Nombre del perfil nulo");
        }

        return mappeador.toPerfil(entidadPerfilCrudRepositorio.save(entidadPerfil)) ;
    }

    @Override
    public void eliminar(int idPerfil) {
        EntidadPerfil perfil = entidadPerfilCrudRepositorio.getReferenceById(idPerfil);
        perfil.setEstadoPerfil("I");

        entidadPerfilCrudRepositorio.save(perfil);
    }


}
