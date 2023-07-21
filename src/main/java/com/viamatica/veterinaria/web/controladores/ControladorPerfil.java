package com.viamatica.veterinaria.web.controladores;

import com.viamatica.veterinaria.dominio.Perfil;
import com.viamatica.veterinaria.dominio.servicio.PerfilService;
import com.viamatica.veterinaria.web.error.PerfilNotFoundException;
import com.viamatica.veterinaria.web.error.UsuarioNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.method.P;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfil")
public class ControladorPerfil {
    @Autowired
    private PerfilService perfilService;
    @GetMapping("/todos")
    public ResponseEntity<List<Perfil>> obtenerTodos(){

        try {
            List<Perfil> perfils = perfilService.obtenerTodos();
            if (perfils.isEmpty()){
                throw new PerfilNotFoundException();
            }else {
                return new ResponseEntity<>(perfils, HttpStatus.OK);
            }
        }catch (PerfilNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }



    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Perfil> obtenerPerfil(@PathVariable("id") int idPerfil){

        try {
            return perfilService.obtenerPerfil(idPerfil)
                    .map(perfil -> new ResponseEntity<>(perfil, HttpStatus.OK)
                    )
                    .orElseThrow(()-> new  PerfilNotFoundException(idPerfil));
        }catch (PerfilNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }
    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Perfil> obtenerPerfilPorNombre(@PathVariable("nombre") String nombrePerfil){

        try {
            return perfilService.obtenerPerfilPorNombre(nombrePerfil)
                    .map(perfil -> new ResponseEntity<>(perfil, HttpStatus.OK))
                    .orElseThrow(()-> new PerfilNotFoundException(nombrePerfil));
        }catch (PerfilNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }

    @PostMapping("/guardar")
    public ResponseEntity<?> guardar(@RequestBody Perfil perfil){
        try {
            if (perfilService.obtenerPerfilPorNombre(perfil.getNombrePerfil()).isPresent()){
                String mensaje = "El Perfil ya existe";

                throw  new ResponseStatusException(HttpStatus.CONFLICT, mensaje);
            }else {
                return new ResponseEntity<>(perfilService.guardar(perfil), HttpStatus.CREATED) ;
            }
        }catch (Exception e){
            throw new ResponseStatusException(
                    HttpStatus.CONFLICT, e.getMessage()
            );
        }




    }
    @PutMapping("/actualizar")
    public ResponseEntity<Perfil> actualizar(@RequestBody Perfil perfil){
        try {
            if (perfilService.obtenerPerfilPorNombre(perfil.getNombrePerfil()).isEmpty()){
                throw new PerfilNotFoundException(perfil.getNombrePerfil());
            }else {
                return new ResponseEntity<>(perfilService.actualizar(perfil), HttpStatus.OK);
            }
        }catch (PerfilNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }


    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable("id") int idPerfil){
        try {
            if (perfilService.obtenerPerfil(idPerfil).isEmpty()){
                throw new PerfilNotFoundException(idPerfil);
            }else {
                perfilService.eliminar(idPerfil);
                return new ResponseEntity(HttpStatus.OK);
            }
        }catch (PerfilNotFoundException ex){
            throw new ResponseStatusException(
                    HttpStatus.NOT_FOUND, ex.getMessage()
            );
        }

    }

}
