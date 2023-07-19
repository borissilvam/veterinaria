package com.viamatica.veterinaria.persistencia.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;
import com.viamatica.veterinaria.persistencia.entidades.HosHospitalizacionPaciente;

import java.util.Date;
import java.util.List;




@Repository
public interface HosHospitalizacionPacienteRepositorio extends JpaRepository<HosHospitalizacionPaciente, Integer>{



    List<HosHospitalizacionPaciente> findByPaciente(GesPaciente paciente);

    @Query("SELECT c FROM HosHospitalizacionPaciente c WHERE DATE_FORMAT(c.fechaIngreso, '%Y-%m-%d') = DATE_FORMAT(:fecha, '%Y-%m-%d')")
    List<HosHospitalizacionPaciente> findByFechaIngreso(@Param("fecha") Date fechaIngreso);

    @Query("SELECT c FROM HosHospitalizacionPaciente c WHERE DATE_FORMAT(c.fechaSalida, '%Y-%m-%d') = DATE_FORMAT(:fecha, '%Y-%m-%d')")
    List<HosHospitalizacionPaciente> findByFechaSalida(@Param("fecha") Date fechaSalida);

    @Query(value = "SELECT CASE WHEN EXISTS (SELECT 1 FROM HosHospitalizacionPaciente h WHERE h.idPaciente = :idPaciente AND h.fechaSalida IS NULL) THEN 1 ELSE 0 END", nativeQuery = true)
    int hasNullFechaSalidaByIdPaciente(@Param("idPaciente") Integer idPaciente);



    // @Query("SELECT h.fechaSalida FROM HosHospitalizacionPaciente h WHERE h.idPaciente = :idPaciente AND h.fechaSalida IS NOT NULL ORDER BY h.fechaSalida DESC")
    // Date findLatestFechaSalidaByIdPaciente(@Param("idPaciente") Integer idPaciente);

}