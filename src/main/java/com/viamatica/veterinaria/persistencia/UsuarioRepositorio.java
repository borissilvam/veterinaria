package com.viamatica.veterinaria.persistencia;

import com.viamatica.veterinaria.dominio.Usuario;
import com.viamatica.veterinaria.dominio.repositorio.IUsuarioRepositorio;
import com.viamatica.veterinaria.persistencia.crud.EntidadUsuarioCrudRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.EntidadPerfil;
import com.viamatica.veterinaria.persistencia.entidades.EntidadUsuario;
import com.viamatica.veterinaria.persistencia.mapeadores.MapeadorUsuario;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public class UsuarioRepositorio implements IUsuarioRepositorio {
    @Autowired
    private EntidadUsuarioCrudRepositorio entidadUsuarioCrudRepositorio;
    @Autowired
    private MapeadorUsuario mapeador;
    @Override
    public List<Usuario> obtenerTodos(){
       return mapeador.toUsuarios(entidadUsuarioCrudRepositorio.findAll()) ;
    }
    @Override
    public Optional<Usuario> obtenerUsuario(int idUsuario){
        Optional<EntidadUsuario> usuario = entidadUsuarioCrudRepositorio.findById(idUsuario);

        return usuario.map(mapeador::toUsuario) ;
    }

    @Override
    public Usuario guardar(Usuario usuario) {
        EntidadUsuario entidadUsuario = mapeador.toEntidadUsuario(usuario);

        entidadUsuario.getUsuarioPerfils().forEach(entidadUsuarioPerfil -> entidadUsuarioPerfil.setEntidadUsuario(entidadUsuario));

        return mapeador.toUsuario(entidadUsuarioCrudRepositorio.save(entidadUsuario)) ;
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorNombre(String nombreUsuario) {

        Optional<EntidadUsuario> usuario = entidadUsuarioCrudRepositorio.findByNombreUsuario(nombreUsuario);

        return usuario.map(mapeador::toUsuario);
    }

    @Override
    public Optional<Usuario> obtenerUsuarioPorCorreo(String correo) {
        Optional<EntidadUsuario> usuario = entidadUsuarioCrudRepositorio.findByCorreo(correo);

        return usuario.map(mapeador::toUsuario);
    }

    @Override
    public Optional<List<Usuario>> obtenerUsuariosPorPerfil(int idPerfil) {
        return Optional.empty();
    }

    @Override
    public Usuario actualizar(Usuario usuario) {
        EntidadUsuario usuario2 = mapeador.toEntidadUsuario(usuario);

        EntidadUsuario entidadUsuario = entidadUsuarioCrudRepositorio.getReferenceById(usuario.getIdUsuario());
        entidadUsuario.setEstadoUsuario(usuario2.getEstadoUsuario());
        entidadUsuario.setNombreUsuario(usuario2.getNombreUsuario());
        entidadUsuario.setContrasenia(usuario2.getContrasenia());
        entidadUsuario.setBloqueado(usuario2.getBloqueado());
        entidadUsuario.setCorreo(usuario2.getCorreo());

        entidadUsuario.getUsuarioPerfils().forEach(entidadUsuarioPerfil -> {
            usuario2.getUsuarioPerfils().forEach(entidadUsuarioPerfil1 -> {
                entidadUsuarioPerfil.setEstadoUsuarioPerfil(entidadUsuarioPerfil1.getEstadoUsuarioPerfil());
            });
        });

        return mapeador.toUsuario(entidadUsuarioCrudRepositorio.save(entidadUsuario)) ;
    }

    @Override
    public void desbloquear(int idUsuario) {
        EntidadUsuario entidadUsuario = entidadUsuarioCrudRepositorio.getReferenceById(idUsuario);
        entidadUsuario.setBloqueado(false);
        entidadUsuarioCrudRepositorio.save(entidadUsuario);
    }

    @Override
    public void eliminar(int id) {
        EntidadUsuario entidadUsuario = entidadUsuarioCrudRepositorio.getReferenceById(id);
        entidadUsuario.setEstadoUsuario("I");

        entidadUsuarioCrudRepositorio.save(entidadUsuario);
    }


}
