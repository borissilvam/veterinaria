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

import com.viamatica.veterinaria.persistencia.crud.HosCirugiaRepositorio;



@RestController
@RequestMapping("/HosCirugia")
class HosCirugiaControlador {

    @Autowired
    HosCirugiaRepositorio repositorio;

    @GetMapping
    public ResponseEntity<List<HosCirugia>> obtenerTodos() {
        try {
            List<HosCirugia> items = new ArrayList<HosCirugia>();

            repositorio.findAll().stream().filter( item -> item.getEstadoCirugia()!=null &&  item.getEstadoCirugia().equals("A")).forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoBorrados")
    public ResponseEntity<List<HosCirugia>> obtenerTodosIncluidoBorrados() {
        try {
            List<HosCirugia> items = new ArrayList<HosCirugia>();

            repositorio.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<HosCirugia> obtenerPorId(@PathVariable("id") int id) {
        Optional<HosCirugia> itemsOpcional = repositorio.findById(id);

        if (itemsOpcional.isPresent()) {
            return new ResponseEntity<>(itemsOpcional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HosCirugia> crear(@RequestBody HosCirugia item) {
        try {
            HosCirugia itemSalvado = repositorio.save(item);
            return new ResponseEntity<>(itemSalvado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<HosCirugia> actualizar(@PathVariable("id") int id, @RequestBody HosCirugia item) {
        Optional<HosCirugia> itemsOpcional = repositorio.findById(id);
        if (itemsOpcional.isPresent()) {
            HosCirugia itemExistente = itemsOpcional.get();
            itemExistente.setEstadoCirugia( item.getEstadoCirugia());
            itemExistente.setFechaActualizacion(new Date());
            itemExistente.setFechaProgramada(item.getFechaProgramada());
            itemExistente.setIdDoctor(item.getIdDoctor());
            itemExistente.setIdHosTipoCirugia(item.getIdHosTipoCirugia());
            return new ResponseEntity<>(repositorio.save(itemExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> borrar(@PathVariable("id") int id) {
        try {
            Optional<HosCirugia> itemsOpcional = repositorio.findById(id);
            //Si no existe
            if(!itemsOpcional.isPresent())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            //Si ya está desactivado
            if(itemsOpcional.get().getEstadoCirugia() == "I")
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            //Pone en I el estado
            HosCirugia itemExistente = itemsOpcional.get();
            itemExistente.setEstadoCirugia("I");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}
