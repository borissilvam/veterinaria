package com.viamatica.veterinaria.web.controladores;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.viamatica.veterinaria.dominio.Cirugia;
import com.viamatica.veterinaria.dominio.servicio.HosCirugiaServicio;



@RestController
@RequestMapping("/HosCirugia")
class HosCirugiaControlador {

    @Autowired
    HosCirugiaServicio servicio;


    //#region lectura
    @GetMapping("/id/{id}")
    public ResponseEntity<Cirugia> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            Cirugia Cirugia = servicio.obtenerPorId(id);
            if (Cirugia == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(Cirugia, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<Cirugia>> obtenerTodos()
    {
        try{
            List<Cirugia> Cirugias = servicio.obtenerTodos();
            if(Cirugias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(Cirugias, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoInactivos")
    public ResponseEntity<List<Cirugia>> obtenerTodosIncluidoInactivos()
    {
        try{
            List<Cirugia> Cirugias = servicio.obtenerTodosIncluidoInactivos();
            if(Cirugias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(Cirugias, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //#endregion


    @PostMapping
    public ResponseEntity<Cirugia> crear(@RequestBody Cirugia Cirugia)
    {
        try{
            Cirugia CirugiaSalvado = servicio.guardar(Cirugia);
            return new ResponseEntity<Cirugia>(CirugiaSalvado, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<Cirugia> actualizar(@PathVariable Integer id, Cirugia Cirugia)
    {
        try {
            Cirugia CirugiaSalvado = servicio.actualizar(Cirugia);
            return new ResponseEntity<Cirugia>(CirugiaSalvado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cirugia> borrar(@PathVariable("id") Integer id)
    {
        try {
            Cirugia CirugiaBorrado = servicio.borrar(id);
            return new ResponseEntity<>(CirugiaBorrado ,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

}
