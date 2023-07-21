package com.viamatica.veterinaria.persistencia.entidades;

import jakarta.persistence.*;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "SegUsuario")
@Data
public class EntidadUsuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idUsuario;
    @Column(unique = true, nullable = false)
    private String nombreUsuario;
    @Column(nullable = false)
    private String contrasenia;
    @Column(unique = true, nullable = false)
    private String correo;
    @Column(nullable = false)
    private Boolean bloqueado;
    @Column(nullable = false, length = 1)
    private String estadoUsuario;
    @Column(insertable = true, updatable = false)
    @CreationTimestamp
    private LocalDateTime fechaCreacion;

    @Column(insertable = true, updatable = true)
    @UpdateTimestamp
    private LocalDateTime fechaActualizacion;
    @OneToMany(mappedBy = "entidadUsuario", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<EntidadUsuarioPerfil> usuarioPerfils;

    @Override
    public String toString() {
        return "EntidadUsuario{" +
                "idUsuario=" + idUsuario +
                ", nombreUsuario='" + nombreUsuario + '\'' +
                ", contrasenia='" + contrasenia + '\'' +
                ", correo='" + correo + '\'' +
                ", bloqueado=" + bloqueado +
                ", estadoUsuario='" + estadoUsuario + '\'' +
                ", fechaCreacion=" + fechaCreacion +
                ", fechaActualizacion=" + fechaActualizacion +
                ", usuarioPerfils=" + usuarioPerfils +
                '}';
    }
}
