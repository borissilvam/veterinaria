package com.viamatica.veterinaria.dominio.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.veterinaria.dominio.Cirugia;
import com.viamatica.veterinaria.persistencia.crud.HosCirugiaRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;
import com.viamatica.veterinaria.persistencia.entidades.HisDoctor;
import com.viamatica.veterinaria.persistencia.entidades.HosCirugia;
import com.viamatica.veterinaria.persistencia.entidades.HosTipoCirugia;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorCirugia;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorTipoCirugia;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class HosCirugiaServicio 
{
    @Autowired
    private HosCirugiaRepositorio repositorio;

    @Autowired
    private MapeadorCirugia mapeadorCirugia;
    @Autowired
    private MapeadorTipoCirugia mapeadorTipoCirugia;

    //#region lectura
    public Cirugia obtenerPorId(Integer id)
    {
        Optional<HosCirugia> hosCirugia = repositorio.findById(id);
        return mapeadorCirugia.toCirugia( hosCirugia.isPresent() ? hosCirugia.get() : null);
    }


    public List<Cirugia> obtenerTodos()
    {
        return mapeadorCirugia.toCirugias( repositorio.findAll().stream().filter(
            tipo -> tipo.getEstadoCirugia() !=null && tipo.getEstadoCirugia().equals("A")
            ).toList());
    }

    public List<Cirugia> obtenerPorIdPaciente(GesPaciente idPaciente)
    {
        return mapeadorCirugia.toCirugias( repositorio.findByPaciente(idPaciente));
    }

    public List<Cirugia> obtenerPorIdDoctor(Integer idDoctor)
    {
        return mapeadorCirugia.toCirugias( repositorio.findByDoctor( new HisDoctor(){ {setIdDoctor(idDoctor);}}));
    }

    public List<Cirugia> obtenerPorFechaProgramada(LocalDate fechaProgramada)
    {
        return mapeadorCirugia.toCirugias( repositorio.findByFechaProgramada( fechaProgramada.atStartOfDay() ));
    }

    public List<Cirugia> obtenerPorTipoCirugias(Integer idTipoCirugia)
    {
        return mapeadorCirugia.toCirugias( repositorio.findByTipoCirugia(new HosTipoCirugia(){{setIdHosTipoCirugia(idTipoCirugia);}}));
    }

    public List<Cirugia> obtenerTodosIncluidoInactivos()
    {
        return mapeadorCirugia.toCirugias( repositorio.findAll());
    }
    //#endregion
    
    public Cirugia guardar(Cirugia Cirugia)
    {
        HosCirugia hosCirugia = repositorio.save(mapeadorCirugia.toHosCirugia(Cirugia));
        return mapeadorCirugia.toCirugia(hosCirugia);
    }

    public Cirugia actualizar(Cirugia Cirugia)
    {   
        Optional<HosCirugia> hosCirugia = repositorio.findById(Cirugia.getIdHosCirugia());
        if(hosCirugia.isEmpty())
            return null;
        
        hosCirugia.get().setFechaProgramada(Cirugia.getFechaProgramada());
        hosCirugia.get().setDoctor(Cirugia.getDoctor());
        hosCirugia.get().setTipoCirugia(mapeadorTipoCirugia.toHosTipoCirugia(Cirugia.getTipoCirugia()));
        
        return  mapeadorCirugia.toCirugia(repositorio.save(hosCirugia.get()));
    }

    public Cirugia borrar(Integer idCirugia)
    {
        Optional<HosCirugia> hosCirugia = repositorio.findById(idCirugia);
        if(hosCirugia.isEmpty())
            return null;

        hosCirugia.get().setEstadoCirugia("I");
        hosCirugia.get().setFechaEliminacion(LocalDateTime.now());
        return  mapeadorCirugia.toCirugia(repositorio.save(hosCirugia.get()));
    }

}