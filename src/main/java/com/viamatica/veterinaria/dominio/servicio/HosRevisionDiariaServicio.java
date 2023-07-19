package com.viamatica.veterinaria.dominio.servicio;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.veterinaria.dominio.RevisionDiaria;
import com.viamatica.veterinaria.persistencia.crud.HosRevisionDiariaRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;
import com.viamatica.veterinaria.persistencia.entidades.HosRevisionDiaria;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorRevisionDiaria;

@Service
public class HosRevisionDiariaServicio {
 
    @Autowired
    private HosRevisionDiariaRepositorio repositorio;

    @Autowired
    private MapeadorRevisionDiaria mapeador;

    public RevisionDiaria obtenerPorId(Integer id)
    {
        Optional<HosRevisionDiaria> hosRevisionDiaria = repositorio.findById(id);
        return mapeador.toRevisionDiaria( hosRevisionDiaria.get());
    }

    public List<RevisionDiaria> obtenerTodos()
    {
        return mapeador.toRevisionDiarias( repositorio.findAll().stream().filter(
            tipo -> tipo.getEstadoHosRevisiondiaria() !=null && tipo.getEstadoHosRevisiondiaria().equals("A")
            ).toList());
    }

    public List<RevisionDiaria> obtenerPorIdPaciente(Integer idPaciente)
    {
        return mapeador.toRevisionDiarias( repositorio.findByPaciente(
            new GesPaciente(){ 
                {setIdPaciente( idPaciente);}
            }
        ));
    }

    public List<RevisionDiaria> obtenerTodosIncluidoInactivos()
    {
        return mapeador.toRevisionDiarias( repositorio.findAll());
    }

    public RevisionDiaria guardar(RevisionDiaria revisionDiaria)
    {
        
        revisionDiaria.setIdRevisionDiaria(null);
        HosRevisionDiaria hosRevisionDiaria = repositorio.save(mapeador.toHosRevisionDiaria(revisionDiaria));
        return mapeador.toRevisionDiaria(hosRevisionDiaria);
    }

    public RevisionDiaria actualizar(RevisionDiaria RevisionDiaria)
    {   
        Optional<HosRevisionDiaria> hosRevisionDiaria = repositorio.findById(RevisionDiaria.getIdRevisionDiaria());
        if(!hosRevisionDiaria.isPresent())
            return null;
        
        hosRevisionDiaria.get().setDetalle(RevisionDiaria.getDetalle());
        hosRevisionDiaria.get().setFechaRevision(RevisionDiaria.getFechaRevision());
        
        return  mapeador.toRevisionDiaria(repositorio.save(hosRevisionDiaria.get()));
    }

    public RevisionDiaria borrar(Integer idRevisionDiaria)
    {
        Optional<HosRevisionDiaria> hosRevisionDiaria = repositorio.findById(idRevisionDiaria);
        if(!hosRevisionDiaria.isPresent())
            return null;

        hosRevisionDiaria.get().setEstadoHosRevisiondiaria("I");
        hosRevisionDiaria.get().setFechaEliminacion(LocalDateTime.now());
        return  mapeador.toRevisionDiaria(repositorio.save(hosRevisionDiaria.get()));
    }

}
