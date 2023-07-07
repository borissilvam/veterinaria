package com.viamatica.veterinaria.dominio.servicio;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.viamatica.veterinaria.dominio.TipoCirugia;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import com.viamatica.veterinaria.persistencia.crud.HosTipoCirugiaRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.HosTipoCirugia;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorTipoCirugia;

@Service
public class HosTipoCirugiaServicio {
    
    @Autowired
    private HosTipoCirugiaRepositorio repositorio;
    
    @Autowired
    private MapeadorTipoCirugia mapeador;

    public TipoCirugia obtenerPorId(Integer id)
    {
        Optional<HosTipoCirugia> hosTipoCirugia = repositorio.findById(id);
        return mapeador.toTipoCirugia( hosTipoCirugia.get());
    }

    public List<TipoCirugia> obtenerTodos()
    {
        return mapeador.toTiposCirugias( repositorio.findAll().stream().filter(
            tipo -> tipo.getEstadoTipoCirugia() !=null && tipo.getEstadoTipoCirugia().equals("A")
            ).toList());
    }

    public List<TipoCirugia> obtenerPorNombre(String nombre)
    {
        return mapeador.toTiposCirugias( repositorio.findByTipoCirugia(nombre));
    }

    public List<TipoCirugia> obtenerTodosIncluidoInactivos()
    {
        return mapeador.toTiposCirugias( repositorio.findAll());
    }

    public TipoCirugia guardar(TipoCirugia tipoCirugia)
    {
        HosTipoCirugia hosTipoCirugia = repositorio.save(mapeador.toHosTipoCirugia(tipoCirugia));
        return mapeador.toTipoCirugia(hosTipoCirugia);
    }

    public TipoCirugia actualizar(TipoCirugia tipoCirugia)
    {   
        Optional<HosTipoCirugia> hosTipoCirugia = repositorio.findById(tipoCirugia.getIdHosTipoCirugia());
        if(hosTipoCirugia.get() == null)
            return null;
        
        
        hosTipoCirugia.get().setTipoCirugia(tipoCirugia.getTipoCirugia());
        
        return  mapeador.toTipoCirugia(repositorio.save(hosTipoCirugia.get()));
    }

    public TipoCirugia borrar(TipoCirugia tipoCirugia)
    {
        Optional<HosTipoCirugia> hosTipoCirugia = repositorio.findById(tipoCirugia.getIdHosTipoCirugia());
        if(hosTipoCirugia.get() == null)
            return null;

        hosTipoCirugia.get().setEstadoTipoCirugia("I");
        hosTipoCirugia.get().setFechaEliminacion(new Date());
        return  mapeador.toTipoCirugia(repositorio.save(hosTipoCirugia.get()));
    }

}
