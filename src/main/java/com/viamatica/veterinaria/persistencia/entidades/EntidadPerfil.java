package com.viamatica.veterinaria.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SegPerfil")
@Data
public class EntidadPerfil {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idPerfil;

    @Column(nullable = false)
    private  String nombrePerfil;
    @Column(nullable = false, length = 2)
    private String estadoPerfil;

    @Column(insertable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(insertable = true, updatable = true)
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;


}
