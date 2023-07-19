package com.viamatica.veterinaria.web.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
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
import org.springframework.web.server.ResponseStatusException;

import com.viamatica.veterinaria.dominio.Cirugia;
import com.viamatica.veterinaria.dominio.servicio.HosCirugiaServicio;
import com.viamatica.veterinaria.dominio.servicio.HosTipoCirugiaServicio;
import com.viamatica.veterinaria.dominio.validaciones.Validador;



@RestController
@RequestMapping("/HosCirugia")
class HosCirugiaControlador {

    @Autowired
    HosCirugiaServicio servicioCirugia;

    @Autowired
    HosTipoCirugiaServicio servicioTipoCirugia;

    //#region lectura
    @GetMapping("{id}")
    public ResponseEntity<Cirugia> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            Cirugia Cirugia = servicioCirugia.obtenerPorId(id);
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
            List<Cirugia> Cirugias = servicioCirugia.obtenerTodos();
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
            List<Cirugia> Cirugias = servicioCirugia.obtenerTodosIncluidoInactivos();
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
    public ResponseEntity<Cirugia> crear(@RequestBody Cirugia cirugia)
    {
        try{
            cirugia.setIdHosCirugia(null);
            if( cirugia.getFechaProgramada().isBefore(LocalDateTime.now())) 
            {
                return new ResponseEntity( "La fecha es anterior a la fecha actual", HttpStatus.EXPECTATION_FAILED);
            }
            if(servicioTipoCirugia.obtenerPorId(cirugia.getTipoCirugia().getIdHosTipoCirugia()) == null)
            {
                return new ResponseEntity( "El tipo de cirugia no se encuentra en el registro", HttpStatus.EXPECTATION_FAILED);
            }

            Cirugia CirugiaSalvado = servicioCirugia.guardar(cirugia);


            return new ResponseEntity<Cirugia>(CirugiaSalvado, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PutMapping
    public ResponseEntity<Cirugia> actualizar(@RequestBody Cirugia cirugia)
    {
        try {

            if(Validador.fechaAntesDeHoy(cirugia.getFechaProgramada())) 
            {
                return new ResponseEntity( "La fecha es anterior a la fecha actual", HttpStatus.EXPECTATION_FAILED);
            }
            Cirugia CirugiaSalvado = servicioCirugia.actualizar(cirugia);
            return new ResponseEntity<Cirugia>(CirugiaSalvado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Cirugia> borrar(@PathVariable("id") Integer id)
    {
        try {
            Cirugia cirugiaBorrado = servicioCirugia.borrar(id);
            if(cirugiaBorrado == null)
            {
                return new ResponseEntity("No existe el registro con el siguiente id: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(cirugiaBorrado ,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }

    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<List< Cirugia>> cirugiasPorDia(@PathVariable LocalDate fecha)
    {
        try {
            return new ResponseEntity<List<Cirugia>>(servicioCirugia.obtenerPorFechaProgramada(fecha), HttpStatus.OK);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage());
        }
    }

}
