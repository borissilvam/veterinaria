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

import com.viamatica.veterinaria.modelo.HosCirugia;
import com.viamatica.veterinaria.modelo.HosHospitalizacionPaciente;
import com.viamatica.veterinaria.repositorio.HosHospitalizacionPacienteRepositorio;

@RestController
@RequestMapping("/HosHospitalizacionPaciente")
class HosHospitalizacionPacienteControlador {

    @Autowired
    HosHospitalizacionPacienteRepositorio repositorio;

    @GetMapping
    public ResponseEntity<List<HosHospitalizacionPaciente>> obtenerTodos() {
        try {
            List<HosHospitalizacionPaciente> items = new ArrayList<HosHospitalizacionPaciente>();

            repositorio.findAll().stream().filter( item -> item.getEstadoHosPaciente()!=null &&  item.getEstadoHosPaciente().equals("A")).forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoBorrados")
    public ResponseEntity<List<HosHospitalizacionPaciente>> obtenerTodosIncluidoBorrados() {
        try {
            List<HosHospitalizacionPaciente> items = new ArrayList<HosHospitalizacionPaciente>();

            repositorio.findAll().forEach(items::add);

            if (items.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(items, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("{id}")
    public ResponseEntity<HosHospitalizacionPaciente> obtenerPorId(@PathVariable("id") Integer id) {
        Optional<HosHospitalizacionPaciente> itemOpcional = repositorio.findById(id);

        if (itemOpcional.isPresent()) {
            return new ResponseEntity<>(itemOpcional.get(), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping
    public ResponseEntity<HosHospitalizacionPaciente> crear(@RequestBody HosHospitalizacionPaciente item) {
        try {
            HosHospitalizacionPaciente itemSalvado = repositorio.save(item);
            return new ResponseEntity<>(itemSalvado, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping("{id}")
    public ResponseEntity<HosHospitalizacionPaciente> actualizar(@PathVariable("id") Integer id, @RequestBody HosHospitalizacionPaciente item) {
        Optional<HosHospitalizacionPaciente> itemOpcional = repositorio.findById(id);
        if (itemOpcional.isPresent()) {
            HosHospitalizacionPaciente itemExistente = itemOpcional.get();
            itemExistente.setFechaActualizacion(new Date());
            itemExistente.setFechaIngreso(item.getFechaIngreso());
            itemExistente.setFechaSalida(item.getFechaSalida());
            itemExistente.setMotivo(item.getMotivo());
            return new ResponseEntity<>(repositorio.save(itemExistente), HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<HttpStatus> borrar(@PathVariable("id") Integer id) {
        try {
            Optional<HosHospitalizacionPaciente> itemsOpcional = repositorio.findById(id);
            //Si no existe
            if(!itemsOpcional.isPresent())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            
            //Si ya está desactivado
            if(itemsOpcional.get().getEstadoHosPaciente() == "I")
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);

            //Pone en I el estado
            HosHospitalizacionPaciente itemExistente = itemsOpcional.get();
            itemExistente.setEstadoHosPaciente("I");
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }
}