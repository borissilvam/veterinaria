package com.viamatica.veterinaria.dominio.servicio;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.viamatica.veterinaria.dominio.HospitalizacionPaciente;
import com.viamatica.veterinaria.persistencia.crud.HosHospitalizacionPacienteRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.HosHospitalizacionPaciente;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorHospitalizacionPaciente;

@Service
public class HosHospitalizacionPacienteServicio {
    
    @Autowired
    private HosHospitalizacionPacienteRepositorio repositorio;

    @Autowired
    private MapeadorHospitalizacionPaciente mapeador;


     //#region lectura

    public HospitalizacionPaciente obtenerPorId(Integer id)
    {
        Optional<HosHospitalizacionPaciente> hosHospitalizacionPaciente = repositorio.findById(id);
        return mapeador.toHospitalizacionPaciente( hosHospitalizacionPaciente.get());
    }

    public List<HospitalizacionPaciente> obtenerTodos()
    {
        return mapeador.toHospitalizacionPacientes( repositorio.findAll().stream().filter(
            tipo -> tipo.getEstadoHosPaciente() !=null && tipo.getEstadoHosPaciente().equals("A")
            ).toList());
    }

    public List<HospitalizacionPaciente> obtenerPorIdPaciente(Integer idPaciente)
    {
        return mapeador.toHospitalizacionPacientes( repositorio.findByIdPaciente(idPaciente));
    }

    public List<HospitalizacionPaciente> obtenerPorFechaIngreso(Date fechaIngreso)
    {
        return mapeador.toHospitalizacionPacientes( repositorio.findByFechaIngreso(fechaIngreso));
    }

    public List<HospitalizacionPaciente> obtenerPorFechaSalida(Date fechaSalida)
    {
        return mapeador.toHospitalizacionPacientes( repositorio.findByFechaSalida(fechaSalida));
    }
   

    public List<HospitalizacionPaciente> obtenerTodosIncluidoInactivos()
    {
        return mapeador.toHospitalizacionPacientes( repositorio.findAll());
    }
    //#endregion
    
    public HospitalizacionPaciente guardar(HospitalizacionPaciente HospitalizacionPaciente)
    {
        HosHospitalizacionPaciente hosHospitalizacionPaciente = repositorio.save(mapeador.toHosHospitalizacionPaciente(HospitalizacionPaciente));
        return mapeador.toHospitalizacionPaciente(hosHospitalizacionPaciente);
    }

    public HospitalizacionPaciente actualizar(HospitalizacionPaciente HospitalizacionPaciente)
    {   
        Optional<HosHospitalizacionPaciente> hosHospitalizacionPaciente = repositorio.findById(HospitalizacionPaciente.getIdHospitalizacion());
        if(hosHospitalizacionPaciente.get() == null)
            return null;
        
        hosHospitalizacionPaciente.get().setFechaIngreso(HospitalizacionPaciente.getFechaIngreso());
        hosHospitalizacionPaciente.get().setFechaSalida(HospitalizacionPaciente.getFechaSalida());
        hosHospitalizacionPaciente.get().setMotivo(HospitalizacionPaciente.getMotivo());
        
        return  mapeador.toHospitalizacionPaciente(repositorio.save(hosHospitalizacionPaciente.get()));
    }

    public HospitalizacionPaciente borrar(HospitalizacionPaciente HospitalizacionPaciente)
    {
        Optional<HosHospitalizacionPaciente> hosHospitalizacionPaciente = repositorio.findById(HospitalizacionPaciente.getIdHospitalizacion());
        if(hosHospitalizacionPaciente.get() == null)
            return null;

        hosHospitalizacionPaciente.get().setEstadoHosPaciente("I");
        hosHospitalizacionPaciente.get().setFechaEliminacion(new Date());
        return  mapeador.toHospitalizacionPaciente(repositorio.save(hosHospitalizacionPaciente.get()));
    }


}
