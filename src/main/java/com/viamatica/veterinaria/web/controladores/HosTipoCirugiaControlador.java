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

import com.viamatica.veterinaria.dominio.TipoCirugia;
import com.viamatica.veterinaria.dominio.servicio.HosTipoCirugiaServicio;

@RestController
@RequestMapping("/HosTipoCirugia")
public class HosTipoCirugiaControlador {
    
    @Autowired
    private HosTipoCirugiaServicio servicio;

    //#region lectura
    @GetMapping("/id/{id}")
    public ResponseEntity<TipoCirugia> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            TipoCirugia tipoCirugia = servicio.obtenerPorId(id);
            if (tipoCirugia == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(tipoCirugia, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<TipoCirugia>> obtenerTodos()
    {
        try{
            List<TipoCirugia> tipoCirugias = servicio.obtenerTodos();
            if(tipoCirugias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(tipoCirugias, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoInactivos")
    public ResponseEntity<List<TipoCirugia>> obtenerTodosIncluidoInactivos()
    {
        try{
            List<TipoCirugia> tipoCirugias = servicio.obtenerTodosIncluidoInactivos();
            if(tipoCirugias.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(tipoCirugias, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //#endregion


    @PostMapping
    public ResponseEntity<TipoCirugia> crear(@RequestBody TipoCirugia tipoCirugia)
    {
        try{
            TipoCirugia tipoCirugiaSalvado = servicio.guardar(tipoCirugia);
            return new ResponseEntity<TipoCirugia>(tipoCirugiaSalvado, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }


    @PutMapping("{id}")
    public ResponseEntity<TipoCirugia> actualizar(@PathVariable Integer id, TipoCirugia tipoCirugia)
    {
        try {
            TipoCirugia tipoCirugiaSalvado = servicio.actualizar(tipoCirugia);
            return new ResponseEntity<TipoCirugia>(tipoCirugiaSalvado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<TipoCirugia> borrar(@PathVariable("id") Integer id)
    {
        try {
            TipoCirugia tipoCirugiaBorrado = servicio.borrar(id);
            return new ResponseEntity<>(tipoCirugiaBorrado ,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}
