package com.viamatica.veterinaria.web.controladores;

import com.viamatica.veterinaria.dominio.Usuario;
import com.viamatica.veterinaria.dominio.servicio.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class ControladorUsuario {
    @Autowired
    private UsuarioService usuarioService;
    @GetMapping("/todos")
    public ResponseEntity<List<Usuario>> obtenerTodos(){
        return new ResponseEntity<>(usuarioService.obtenerTodos(), HttpStatus.OK) ;
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Usuario> obtenerUsuario(@PathVariable("id") int idUsuario){
        return usuarioService.obtenerUsuario(idUsuario)
                        .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                        .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Usuario> obtenerUsuarioPorNombre(@PathVariable("nombre") String nombreUsuario){
        return usuarioService.obtenerUsuarioPorNombre(nombreUsuario)
                .map(usuario -> new ResponseEntity<>(usuario, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Usuario usuario){

           if (usuarioService.obtenerUsuarioPorNombre(usuario.getNombreUsuario()).isPresent()){

               String mensaje = "El usuaio ya existe";
                        return new ResponseEntity<>(mensaje, HttpStatus.CONFLICT);
           }
           else  {
            return new ResponseEntity<>(usuarioService.guardar(usuario), HttpStatus.CREATED) ;
        }

    }

    @PutMapping("/actualizar")
    public ResponseEntity<Usuario> actualizar(@RequestBody Usuario usuario){
        return new ResponseEntity<>(usuarioService.actualizar(usuario), HttpStatus.OK);
    }

    @PutMapping("/desbloquear/{id}")
    public ResponseEntity desbloquear(@PathVariable("id") int idUsuario){
        if (usuarioService.desbloquear(idUsuario)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable("id") int idUsuario){
        if (usuarioService.eliminar(idUsuario)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
