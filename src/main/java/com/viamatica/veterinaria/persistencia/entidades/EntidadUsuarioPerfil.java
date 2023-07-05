package com.viamatica.veterinaria.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "SegUsuarioPerfil")
@Data
public class EntidadUsuarioPerfil {
    @EmbeddedId
    private UsuarioPerfilPK idUsuarioPerfil;

    @Column(nullable = false, length = 1)
    private String estadoUsuarioPerfil;

    @Column(insertable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(insertable = true, updatable = true)
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
    @MapsId("idUsuario")
    @ManyToOne
    @JoinColumn(name = "idUsuario", insertable = false, updatable = false)
    private EntidadUsuario entidadUsuario;
    @ManyToOne
    @JoinColumn(name = "idPerfil", insertable = false, updatable = false)
    private EntidadPerfil entidadPerfil;


}
