package com.viamatica.veterinaria.dominio.servicio;


import com.viamatica.veterinaria.persistencia.crud.EntidadUsuarioCrudRepositorio;
import com.viamatica.veterinaria.persistencia.entidades.EntidadUsuario;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


import java.util.List;
import java.util.stream.Collectors;

@Service
public class UsuarioSecurityService implements UserDetailsService {
    @Autowired
    private EntidadUsuarioCrudRepositorio entidadUsuarioCrudRepositorio;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        EntidadUsuario entidadUsuario = entidadUsuarioCrudRepositorio.findByNombreUsuario(username)
                .orElseThrow(() -> new UsernameNotFoundException("User " + username + "not found"));



   //     String[] roles = entidadUsuario.getUsuarioPerfils()
     //           .stream().map(entidadUsuarioPerfil -> {
       //             return entidadUsuarioPerfil.getEntidadPerfil().getNombrePerfil();
         //       }).toArray(String[]::new);
//
  //      System.out.println("Aquii : " + Arrays.toString(roles));
//
  //      return User.builder()
    //            .username(entidadUsuario.getNombreUsuario())
      //          .password(entidadUsuario.getContrasenia())
        //        .roles("READ")

        //     .build();

        List<String> roles = entidadUsuario.getUsuarioPerfils()
                .stream()
                .map(entidadUsuarioPerfil -> entidadUsuarioPerfil.getEntidadPerfil().getNombrePerfil())
                .collect(Collectors.toList());

        return User.builder()
                .username(entidadUsuario.getNombreUsuario())
                .password(entidadUsuario.getContrasenia())
                .roles(roles.toArray(new String[0]))
                .build();
    }

}

