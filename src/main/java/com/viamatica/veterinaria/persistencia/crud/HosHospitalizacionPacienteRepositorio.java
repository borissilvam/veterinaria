package com.viamatica.veterinaria.persistencia.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viamatica.veterinaria.persistencia.entidades.HosHospitalizacionPaciente;

import java.util.Date;
import java.util.List;




@Repository
public interface HosHospitalizacionPacienteRepositorio extends JpaRepository<HosHospitalizacionPaciente, Integer>{


    List<HosHospitalizacionPaciente> findByIdPaciente(Integer idPaciente);

    @Query("SELECT c FROM HosHospitalizacionPaciente c WHERE DATE_FORMAT(c.fechaIngreso, '%Y-%m-%d') = DATE_FORMAT(:fecha, '%Y-%m-%d')")
    List<HosHospitalizacionPaciente> findByFechaIngreso(@Param("fecha") Date fechaIngreso);

    @Query("SELECT c FROM HosHospitalizacionPaciente c WHERE DATE_FORMAT(c.fechaSalida, '%Y-%m-%d') = DATE_FORMAT(:fecha, '%Y-%m-%d')")
    List<HosHospitalizacionPaciente> findByFechaSalida(@Param("fecha") Date fechaSalida);

}