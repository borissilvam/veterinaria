
package com.viamatica.veterinaria.persistencia.entidades;

import java.time.LocalDateTime;
import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "HosRevisionDiaria", schema = "dbo")
@Data
public class HosRevisionDiaria
{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idRevisionDiaria;

    @Column(name = "detalle")
    private String detalle;

    @Column(name = "fechaRevision")
    private Date fechaRevision;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "idPaciente")
    private GesPaciente paciente;

    @Column(name = "estadoHosRevisiondiaria")
    private String estadoHosRevisiondiaria;
    
    @Column(name = "fechaCreacion", updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

     @Column(name ="fechaEliminacion")
    private LocalDateTime fechaEliminacion;
    
    // public HosRevisionDiaria() {
    //     this.fechaCreacion = new Date();
    // }
    
}