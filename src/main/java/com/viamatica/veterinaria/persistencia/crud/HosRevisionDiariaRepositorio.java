
package com.viamatica.veterinaria.persistencia.crud;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.viamatica.veterinaria.persistencia.entidades.GesPaciente;
import com.viamatica.veterinaria.persistencia.entidades.HosRevisionDiaria;



@Repository
public interface HosRevisionDiariaRepositorio extends JpaRepository<HosRevisionDiaria, Integer>{
    
    List<HosRevisionDiaria> findByPaciente(GesPaciente idPaciente);

}