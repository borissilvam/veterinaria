package com.viamatica.veterinaria.persistencia.entidades;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "HosCirugia", schema = "dbo")
@Data
public class HosCirugia {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idHosCirugia")
    private Integer idHosCirguia;

    @Column(name = "idPaciente")
    private Integer idPaciente;

    @Column(name = "idHostipoCirugia")
    private Integer idHosTipoCirugia;

    @Column(name = "fechaProgramada")
    private Date fechaProgramada;

    @Column(name = "idDoctor")
    private Integer idDoctor;

    @Column(name = "estadoCirugia")
    private String estadoCirugia;

    @Column(name = "fechaCreacion")
    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private Date fechaActualizacion;

    @Column(name ="fechaEliminacion")
    private Date fechaEliminacion;
    
    public HosCirugia() {
        this.fechaCreacion = new Date();
    }
    
}
