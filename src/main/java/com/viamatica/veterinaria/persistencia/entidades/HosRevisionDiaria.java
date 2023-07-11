
package com.viamatica.veterinaria.persistencia.entidades;

import java.util.Date;

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

    @Column(name = "idPaciente")
    private Integer idPaciente;

    @Column(name = "estadoHosRevisiondiaria")
    private String estadoHosRevisiondiaria;
    
    @Column(name = "fechaCreacion")
    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private Date fechaActualizacion;

     @Column(name ="fechaEliminacion")
    private Date fechaEliminacion;
    
    public HosRevisionDiaria() {
        this.fechaCreacion = new Date();
    }
    
}