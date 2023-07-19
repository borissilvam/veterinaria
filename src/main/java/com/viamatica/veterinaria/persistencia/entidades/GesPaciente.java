package com.viamatica.veterinaria.persistencia.entidades;

import java.time.LocalDateTime;

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
@Data
@Table(name =  "GesPaciente")
public class GesPaciente {
 
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idPaciente;

    String nombrePaciente;

    LocalDateTime fechaNac;

    Integer edad;

    String raza;

    String idTipoPaciente;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "idCliente")
    GesCliente idCliente;

    String estadoPaciente;

    @Column(name = "fechaCreacion")
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

     @Column(name ="fechaEliminacion")
    private LocalDateTime fechaEliminacion;
    
}
