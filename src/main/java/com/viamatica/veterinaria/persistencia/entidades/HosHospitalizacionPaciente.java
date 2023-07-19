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
@Table(name = "HosHospitalizacionPaciente", schema = "dbo")
@Data
public class HosHospitalizacionPaciente {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idHospitalizacion;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "idPaciente")
    private GesPaciente paciente;

    @Column(name = "fechaIngreso")
    private LocalDateTime fechaIngreso;

    @Column(name = "fechaSalida")
    private LocalDateTime fechaSalida;

    @Column(name = "motivo")
    private String motivo;

    @Column(name = "estadoHosPaciente")
    private String estadoHosPaciente;

    @Column(name = "fechaCreacion", updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

     @Column(name ="fechaEliminacion")
    private LocalDateTime fechaEliminacion;
    
}
