package com.viamatica.veterinaria.web.controladores;

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

import com.viamatica.veterinaria.dominio.HospitalizacionPaciente;
import com.viamatica.veterinaria.dominio.servicio.HosHospitalizacionPacienteServicio;


@RestController
@RequestMapping("/HosHospitalizacionPaciente")
class HosHospitalizacionPacienteControlador {

    @Autowired
    HosHospitalizacionPacienteServicio servicio;

    
    //#region lectura
    @GetMapping("/id/{id}")
    public ResponseEntity<HospitalizacionPaciente> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            HospitalizacionPaciente HospitalizacionPaciente = servicio.obtenerPorId(id);
            if (HospitalizacionPaciente == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HospitalizacionPaciente, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping
    public ResponseEntity<List<HospitalizacionPaciente>> obtenerTodos()
    {
        try{
            List<HospitalizacionPaciente> HospitalizacionPacientes = servicio.obtenerTodos();
            if(HospitalizacionPacientes.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HospitalizacionPacientes, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoInactivos")
    public ResponseEntity<List<HospitalizacionPaciente>> obtenerTodosIncluidoInactivos()
    {
        try{
            List<HospitalizacionPaciente> HospitalizacionPacientes = servicio.obtenerTodosIncluidoInactivos();
            if(HospitalizacionPacientes.isEmpty())
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            return new ResponseEntity<>(HospitalizacionPacientes, HttpStatus.OK);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //#endregion


    @PostMapping
    public ResponseEntity<HospitalizacionPaciente> crear(@RequestBody HospitalizacionPaciente hospitalizacionPaciente)
    {
        try{
            hospitalizacionPaciente.setIdHospitalizacion(null);
            if(servicio.pacienteSigueHospitalizado(hospitalizacionPaciente.getPaciente().getIdPaciente()))
            {
                return new ResponseEntity("Paciente ya está hospitalizado", HttpStatus.EXPECTATION_FAILED);
            }
            if(hospitalizacionPaciente.getFechaSalida().isBefore(LocalDateTime.now()) )
            {
                return new ResponseEntity("La fecha de salida es anterior a la de entrada ", HttpStatus.EXPECTATION_FAILED);
            }
            HospitalizacionPaciente HospitalizacionPacienteSalvado = servicio.guardar(hospitalizacionPaciente);
            return new ResponseEntity<HospitalizacionPaciente>(HospitalizacionPacienteSalvado, HttpStatus.CREATED);
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping
    public ResponseEntity<HospitalizacionPaciente> actualizar(@RequestBody HospitalizacionPaciente hospitalizacionPaciente)
    {
        try {
            if(hospitalizacionPaciente.getFechaSalida()!=null && hospitalizacionPaciente.getFechaSalida().isBefore(hospitalizacionPaciente.getFechaIngreso()) )
            {
                return new ResponseEntity("La fecha de salida es anterior a la de entrada ", HttpStatus.EXPECTATION_FAILED);
            }
            HospitalizacionPaciente HospitalizacionPacienteSalvado = servicio.actualizar(hospitalizacionPaciente);
            return new ResponseEntity<HospitalizacionPaciente>(HospitalizacionPacienteSalvado, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HospitalizacionPaciente> borrar(@PathVariable("id") Integer id)
    {
        try {
            HospitalizacionPaciente hospitalizacion = servicio.borrar(id);
            if(hospitalizacion == null)
            {
                return new ResponseEntity("No existe el registro con el siguiente id: " + id, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(hospitalizacion ,HttpStatus.NO_CONTENT);
        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.EXPECTATION_FAILED);
        }
    }


}