
package com.viamatica.veterinaria.persistencia.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viamatica.veterinaria.persistencia.entidades.HisDoctor;
import com.viamatica.veterinaria.persistencia.entidades.HosCirugia;
import com.viamatica.veterinaria.persistencia.entidades.HosTipoCirugia;

import java.util.List;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;





@Repository
public interface HosCirugiaRepositorio extends JpaRepository<HosCirugia, Integer>{
    
    List<HosCirugia> findByIdPaciente(Integer idPaciente);

    List<HosCirugia> findByDoctor(HisDoctor doctor);

    List<HosCirugia> findByTipoCirugia(HosTipoCirugia idHosTipoCirugia);

    // @Query("SELECT c FROM HosCirugia c WHERE DATE_FORMAT(c.fechaProgramada, '%Y-%m-%d') = DATE_FORMAT(:fecha, '%Y-%m-%d')")
    // @Query("SELECT c FROM HosCirugia c WHERE DATE(c.fechaProgramada) = DATE(:fecha)")
    @Query(value = "SELECT * FROM HosCirugia c WHERE CAST(c.fechaProgramada AS date) = CAST(:fecha AS date)", nativeQuery = true)
    List<HosCirugia> findByFechaProgramada(@Param("fecha") LocalDateTime fechaProgramada);
}