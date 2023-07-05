package com.viamatica.veterinaria.web.controladores;

import com.viamatica.veterinaria.dominio.Perfil;
import com.viamatica.veterinaria.dominio.servicio.PerfilService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/perfil")
public class ControladorPerfil {
    @Autowired
    private PerfilService perfilService;
    @GetMapping("/todos")
    public ResponseEntity<List<Perfil>> obtenerTodos(){
        return new ResponseEntity<>(perfilService.obtenerTodos(), HttpStatus.OK);
    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Perfil> obtenerPerfil(@PathVariable("id") int idPerfil){
        return perfilService.obtenerPerfil(idPerfil)
                .map(perfil -> new ResponseEntity<>(perfil, HttpStatus.OK)
                )
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
    @GetMapping("nombre/{nombre}")
    public ResponseEntity<Perfil> obtenerPerfilPorNombre(@PathVariable("nombre") String nombrePerfil){
        return perfilService.obtenerPerfilPorNombre(nombrePerfil)
                .map(perfil -> new ResponseEntity<>(perfil, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping("/guardar")
    public ResponseEntity<Perfil> guardar(@RequestBody Perfil perfil){

        return new ResponseEntity<>(perfilService.guardar(perfil), HttpStatus.CREATED) ;
    }
    @PutMapping("/actualizar")
    public ResponseEntity<Perfil> actualizar(@RequestBody Perfil perfil){

        return new ResponseEntity<>(perfilService.actualizar(perfil), HttpStatus.OK);
    }
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity eliminar(@PathVariable("id") int idPerfil){
        if (perfilService.eliminar(idPerfil)){
            return new ResponseEntity(HttpStatus.OK);
        }else {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
    }

}
