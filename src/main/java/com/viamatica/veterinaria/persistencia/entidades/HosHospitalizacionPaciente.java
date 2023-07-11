package com.viamatica.veterinaria.persistencia.entidades;

import java.util.Date;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.beans.factory.annotation.Value;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "HosHospitalizacionPaciente", schema = "dbo")
@Data
public class HosHospitalizacionPaciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHospitalizacion;

    @Column(name = "idPaciente")
    private Integer idPaciente;

    @Column(name = "fechaIngreso")
    private Date fechaIngreso;

    @Column(name = "fechaSalida")
    private Date fechaSalida;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "estadoHosPaciente")
    private String estadoHosPaciente;

    @Column(name = "fechaCreacion")
    @CreationTimestamp
    private Date fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private Date fechaActualizacion;

     @Column(name ="fechaEliminacion")
    private Date fechaEliminacion;
    
    public HosHospitalizacionPaciente() {
        this.fechaCreacion = new Date();
    }
}
