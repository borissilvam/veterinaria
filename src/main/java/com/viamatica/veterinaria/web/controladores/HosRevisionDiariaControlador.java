package com.viamatica.veterinaria.web.controladores;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

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

import com.viamatica.veterinaria.dominio.RevisionDiaria;
import com.viamatica.veterinaria.dominio.servicio.HosRevisionDiariaServicio;

@RestController
@RequestMapping("/HosRevisionDiaria")
class HosRevisionDiariaControlador {

    @Autowired
    HosRevisionDiariaServicio servicio;

    
    //#region lectura
    @GetMapping("/id/{id}")
    public ResponseEntity<RevisionDiaria> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            RevisionDiaria RevisionDiaria = servicio.obtenerPorId(id);
            if (RevisionDiaria == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(RevisionDiaria, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<RevisionDiaria>> obtenerTodos()
    {
        try{
            List<RevisionDiaria> RevisionDiarias = servicio.obtenerTodos();
            if(RevisionDiarias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(RevisionDiarias, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoInactivos")
    public ResponseEntity<List<RevisionDiaria>> obtenerTodosIncluidoInactivos()
    {
        try{
            List<RevisionDiaria> RevisionDiarias = servicio.obtenerTodosIncluidoInactivos();
            if(RevisionDiarias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(RevisionDiarias, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //#endregion


    @PostMapping
    public ResponseEntity<RevisionDiaria> crear(@RequestBody RevisionDiaria RevisionDiaria)
    {
        try{
            RevisionDiaria RevisionDiariaSalvado = servicio.guardar(RevisionDiaria);
            return new ResponseEntity<RevisionDiaria>(RevisionDiariaSalvado, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<RevisionDiaria> actualizar(@PathVariable Integer id, RevisionDiaria RevisionDiaria)
    {
        try {
            RevisionDiaria RevisionDiariaSalvado = servicio.actualizar(RevisionDiaria);
            return new ResponseEntity<RevisionDiaria>(RevisionDiariaSalvado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<RevisionDiaria> borrar(@PathVariable("id") Integer id)
    {
        try {
            RevisionDiaria RevisionDiariaBorrado = servicio.borrar(id);
            return new ResponseEntity<>(RevisionDiariaBorrado ,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}