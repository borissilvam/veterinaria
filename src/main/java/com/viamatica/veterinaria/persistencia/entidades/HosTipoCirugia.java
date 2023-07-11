package com.viamatica.veterinaria.persistencia.entidades;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.annotation.CreatedDate;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Data;

@Entity
@Table(name= "HosTipoCirugia", schema="dbo")
@Data
public class HosTipoCirugia {

    public HosTipoCirugia() {
        this.fechaCreacion = new Date();
    }

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHosTipoCirugia;

    @Column(name ="tipoCirugia")
    private String tipoCirugia;

    @Column(name ="estadoTipoCirugia")
    private String estadoTipoCirugia;

    @Column(name ="fechaCreacion")
    @CreationTimestamp
    private Date fechaCreacion;
    
    // @CreationTimestamp
    @Column(name ="fechaActualizacion")
    @UpdateTimestamp
    private Date fechaActualizacion;

    @Column(name ="fechaEliminacion")
    private Date fechaEliminacion;

    
    
}