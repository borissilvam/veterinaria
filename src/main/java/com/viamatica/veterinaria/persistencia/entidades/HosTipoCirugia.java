package com.viamatica.veterinaria.persistencia.entidades;

import java.time.LocalDateTime;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name= "HosTipoCirugia", schema="dbo")
@Data
public class HosTipoCirugia {

    // public HosTipoCirugia() {
    //     this.fechaCreacion = new Date();
    // }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHosTipoCirugia;

    @Column(name ="tipoCirugia")
    private String tipoCirugia;

    @Column(name ="estadoTipoCirugia")
    private String estadoTipoCirugia;

    @Column(name ="fechaCreacion", updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;
    
    // @CreationTimestamp
    @Column(name ="fechaActualizacion")
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

    @Column(name ="fechaEliminacion")
    private LocalDateTime fechaEliminacion;

    
    
}