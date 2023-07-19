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
@Table(name = "HisDoctor")
@Data
public class HisDoctor {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idDoctor")
    private Integer idDoctor;

    @Column
    String nombre;

    @Column
    Integer idUsuario;

    @Column(name = "cedula")
    String cedula;

    @Column
    Integer edad;

    @Column
    String telefono;

    @Column
    String especialidad;

    @Column
    String estadoDoctor;

    @Column
    @CreationTimestamp
    LocalDateTime fechaCreacion;

    @Column
    @UpdateTimestamp
    LocalDateTime fechaActualizacion;

    @Column
    LocalDateTime fechaEliminacion;


}
