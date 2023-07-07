
package com.viamatica.veterinaria.persistencia.crud;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.viamatica.veterinaria.persistencia.entidades.HosCirugia;
import java.util.List;
import java.util.Date;





@Repository
public interface HosCirugiaRepositorio extends JpaRepository<HosCirugia, Integer>{
    
    List<HosCirugia> findByIdPaciente(Integer idPaciente);

    List<HosCirugia> findByIdDoctor(Integer idDoctor);

    List<HosCirugia> findByIdHosTipoCirugia(Integer idHosTipoCirugia);

    @Query("SELECT c FROM HosCirugia c WHERE DATE_FORMAT(c.fechaProgramada, '%Y-%m-%d') = DATE_FORMAT(:fecha, '%Y-%m-%d')")
    List<HosCirugia> findByFechaProgramada(@Param("fecha") Date fechaProgramada);
}