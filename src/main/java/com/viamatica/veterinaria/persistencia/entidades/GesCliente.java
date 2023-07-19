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
@Data
@Table(name =  "GesCliente")
public class GesCliente {
    
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idCliente;

    String numDocumento;

    String nombreCliente;

    String apellidoCliente;

    LocalDateTime fechaNac;

    String telefono;

    String direccion;

    String correo;

    String estadoCliente;

    Integer idUsuario;


    // @Column(name = "fechaCreacion")
    // @CreationTimestamp
    // private LocalDateTime fechaCreacion;

    // @Column(name = "fechaActualizacion")
    // @UpdateTimestamp
    // private LocalDateTime fechaActualizacion;

    //  @Column(name ="fechaEliminacion")
    // private LocalDateTime fechaEliminacion;

}
