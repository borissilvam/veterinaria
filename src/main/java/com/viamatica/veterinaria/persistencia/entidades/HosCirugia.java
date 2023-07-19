package com.viamatica.veterinaria.persistencia.entidades;

import java.time.LocalDateTime;

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

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "idPaciente")
    private GesPaciente paciente;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "idHostipoCirugia")
    private HosTipoCirugia tipoCirugia;

    @Column(name = "fechaProgramada")
    private LocalDateTime fechaProgramada;

    @OneToOne(fetch =  FetchType.EAGER)
    @JoinColumn(name = "idDoctor")
    private HisDoctor doctor;

    @Column(name = "estadoCirugia")
    private String estadoCirugia;

    @Column(name = "fechaCreacion", updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(name = "fechaActualizacion")
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;

    @Column(name ="fechaEliminacion")
    private LocalDateTime fechaEliminacion;
    
    // public HosCirugia() {
    //     this.fechaCreacion = new LocalDate
    // }

    
}
