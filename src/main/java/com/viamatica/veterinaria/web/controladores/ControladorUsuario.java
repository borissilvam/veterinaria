package com.viamatica.veterinaria.web.controladores;

import com.fasterxml.jackson.databind.JsonMappingException;
import com.viamatica.veterinaria.dominio.Usuario;
import com.viamatica.veterinaria.dominio.servicio.UsuarioService;
import com.viamatica.veterinaria.web.error.ApiError;
import com.viamatica.veterinaria.web.error.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> obtenerTodos(){
        try {
            List<Usuario> usuarios = usuarioService.obtenerTodos();
            if (usuarios.isEmpty()){
                throw  new UsuarioNotFoundException();
            }
            return new ResponseEntity<>(usuarios, HttpStatus.OK);
        }catch (UsuarioNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int idUsuario){
        try {
            return usuarioService.obtenerUsuario(idUsuario)
                    .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                    .orElseThrow(()-> new UsuarioNotFoundException(idUsuario));
        } catch (UsuarioNotFoundException ex) {
            throw  new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

       /* return usuarioService.obtenerUsuario(idUsuario)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElseThrow(()-> new UsuarioNotFoundException(idUsuario));*/

    }

    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Usuario> obtenerUsuarioPorNombre(@PathVariable("nombre") String nombreUsuario){
        try {
            return usuarioService.obtenerUsuarioPorNombre(nombreUsuario)
                    .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                    .orElseThrow(()-> new UsuarioNotFoundException(nombreUsuario));
        }catch (UsuarioNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){


        try {
            String mensaje;
            if (usuarioService.obtenerUsuarioPorNombre(usuario.getNombreUsuario()).isPresent()){

                mensaje = "El nombre de usuario ya esta en uso por otro usuario";

               throw new ResponseStatusException(HttpStatus.CONFLICT,mensaje);


            }else if (usuarioService.obtenerUsuarioPorCorreo(usuario.getCorreo()).isPresent()) {
                mensaje= "El correo ya esta en uso por otro usuario";
                throw new ResponseStatusException(HttpStatus.CONFLICT, mensaje);
            } else  {
                return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED) ;
            }

        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage()
            );
        }



    }

    @PutMapping("/actualizar")
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario){
        try {
            if (usuarioService.obtenerUsuarioPorNombre(usuario.getNombreUsuario()).isEmpty()){
                throw new UsuarioNotFoundException(usuario.getNombreUsuario());
            }
            return new ResponseEntity<>(usuarioService.actualizar(usuario), HttpStatus.OK);
        }catch (UsuarioNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }

    @PutMapping("/desbloquear/{id}")
    public ResponseEntity<Void> desbloquear(@PathVariable("id") int idUsuario){
        try {
            if (usuarioService.obtenerUsuario(idUsuario).isEmpty()){
                throw new UsuarioNotFoundException(idUsuario);
            }else {
                usuarioService.desbloquear(idUsuario);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (UsuarioNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }


    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminar(@PathVariable("id") int idUsuario){

        try {
            if (usuarioService.obtenerUsuario(idUsuario).isEmpty()){
                throw new UsuarioNotFoundException(idUsuario);
            }else {
                usuarioService.eliminar(idUsuario);
                return new ResponseEntity<>(HttpStatus.OK);
            }
        }catch (UsuarioNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );


        }

    }

}
