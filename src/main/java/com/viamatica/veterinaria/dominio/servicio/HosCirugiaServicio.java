package com.viamatica.veterinaria.dominio.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.veterinaria.dominio.Cirugia;
import com.viamatica.veterinaria.persistencia.crud.HosCirugiaRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.HosCirugia;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorCirugia;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HosCirugiaServicio 
{
    @Autowired
    private HosCirugiaRepositorio repositorio;

    @Autowired
    private MapeadorCirugia mapeador;

    //#region lectura
    public Cirugia obtenerPorId(Integer id)
    {
        Optional<HosCirugia> hosCirugia = repositorio.findById(id);
        return mapeador.toCirugia( hosCirugia.get());
    }


    public List<Cirugia> obtenerTodos()
    {
        return mapeador.toCirugias( repositorio.findAll().stream().filter(
            tipo -> tipo.getEstadoCirugia() !=null && tipo.getEstadoCirugia().equals("A")
            ).toList());
    }

    public List<Cirugia> obtenerPorIdPaciente(Integer idPaciente)
    {
        return mapeador.toCirugias( repositorio.findByIdPaciente(idPaciente));
    }

    public List<Cirugia> obtenerPorIdDoctor(Integer idDoctor)
    {
        return mapeador.toCirugias( repositorio.findByIdDoctor(idDoctor));
    }

    public List<Cirugia> obtenerPorFechaProgramada(Date fechaProgramada)
    {
        return mapeador.toCirugias( repositorio.findByFechaProgramada(fechaProgramada));
    }

    public List<Cirugia> obtenerPorTipoCirugias(Integer idTipoCirugia)
    {
        return mapeador.toCirugias( repositorio.findByIdHosTipoCirugia(idTipoCirugia));
    }

    public List<Cirugia> obtenerTodosIncluidoInactivos()
    {
        return mapeador.toCirugias( repositorio.findAll());
    }
    //#endregion
    
    public Cirugia guardar(Cirugia Cirugia)
    {
        HosCirugia hosCirugia = repositorio.save(mapeador.toHosCirugia(Cirugia));
        return mapeador.toCirugia(hosCirugia);
    }

    public Cirugia actualizar(Cirugia Cirugia)
    {   
        Optional<HosCirugia> hosCirugia = repositorio.findById(Cirugia.getIdHosCirugia());
        if(hosCirugia.get() == null)
            return null;
        
        hosCirugia.get().setFechaProgramada(Cirugia.getFechaProgramada());
        hosCirugia.get().setIdDoctor(Cirugia.getIdDoctor());
        
        return  mapeador.toCirugia(repositorio.save(hosCirugia.get()));
    }

    public Cirugia borrar(Integer idCirugia)
    {
        Optional<HosCirugia> hosCirugia = repositorio.findById(idCirugia);
        if(hosCirugia.get() == null)
            return null;

        hosCirugia.get().setEstadoCirugia("I");
        hosCirugia.get().setFechaEliminacion(new Date());
        return  mapeador.toCirugia(repositorio.save(hosCirugia.get()));
    }

}