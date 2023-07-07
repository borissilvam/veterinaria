package com.viamatica.veterinaria.persistencia.crud;


import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.viamatica.veterinaria.persistencia.entidades.HosTipoCirugia;

public interface HosTipoCirugiaRepositorio extends JpaRepository<HosTipoCirugia, Integer> {
    
    public List<HosTipoCirugia> findByTipoCirugia(String tipoCirugia);

}
