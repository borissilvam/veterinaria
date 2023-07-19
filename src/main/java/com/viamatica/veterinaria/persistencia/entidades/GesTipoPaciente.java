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
@Table(name =  "GesTipoPaciente")
public class GesTipoPaciente {
    

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer idTipoPaciente;

    String tipoPaciente;

    String estadoTipoPaciente;
}
