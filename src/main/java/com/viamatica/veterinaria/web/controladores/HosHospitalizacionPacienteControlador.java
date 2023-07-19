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
import com.viamatica.veterinaria.web.RespuestaServidor;


@RestController
@RequestMapping("/HosHospitalizacionPaciente")
class HosHospitalizacionPacienteControlador {

    @Autowired
    HosHospitalizacionPacienteServicio servicio;

    
    //#region lectura
    @GetMapping("{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            HospitalizacionPaciente HospitalizacionPaciente = servicio.obtenerPorId(id);

            if (HospitalizacionPaciente == null)
                return new ResponseEntity<>( new RespuestaServidor(HttpStatus.NOT_FOUND, "No se encontró cirugia con el id:" + id), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(HospitalizacionPaciente, HttpStatus.OK);

        }catch(Exception e)
        {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }

    @GetMapping
    public ResponseEntity<Object> obtenerTodos()
    {
        try{
            List<HospitalizacionPaciente> HospitalizacionPacientes = servicio.obtenerTodos();

            if(HospitalizacionPacientes.isEmpty())
                return new ResponseEntity<>(new RespuestaServidor(HttpStatus.NO_CONTENT, "No hay datos"), HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(HospitalizacionPacientes, HttpStatus.OK);

        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/incluidoInactivos")
    public ResponseEntity<Object> obtenerTodosIncluidoInactivos()
    {
        try{
            List<HospitalizacionPaciente> HospitalizacionPacientes = servicio.obtenerTodosIncluidoInactivos();

            if(HospitalizacionPacientes.isEmpty())
                return new ResponseEntity<>(new RespuestaServidor(HttpStatus.NO_CONTENT, "No hay datos"), HttpStatus.NO_CONTENT);  

            return new ResponseEntity<>(HospitalizacionPacientes, HttpStatus.OK);

        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //#endregion


    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody HospitalizacionPaciente hospitalizacionPaciente)
    {
        try{
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.UNPROCESSABLE_ENTITY);
            hospitalizacionPaciente.setIdHospitalizacion(null);

            //Validaciones de los datos
            if(servicio.pacienteSigueHospitalizado(hospitalizacionPaciente.getPaciente().getIdPaciente()))
                respuestaServidor.anadirMensaje("El paciente ya se encuentra hospitalizado");

            if(hospitalizacionPaciente.getFechaIngreso()!=null && hospitalizacionPaciente.getFechaIngreso().isBefore(LocalDateTime.now()) )
                respuestaServidor.anadirMensaje("La fecha es anterior a la actual");

            if(hospitalizacionPaciente.getFechaSalida()!=null && hospitalizacionPaciente.getFechaSalida().isBefore(LocalDateTime.now()) )
                respuestaServidor.anadirMensaje("La fecha es anterior a la actual");

            //Si hay error con los datos enviados devulve los errores
            if(!respuestaServidor.getMensajes().isEmpty())
                return new ResponseEntity<Object>(respuestaServidor, respuestaServidor.getStatus());    

            //Intenta guardar el objeto
            HospitalizacionPaciente HospitalizacionPacienteSalvado = servicio.guardar(hospitalizacionPaciente);

            if(HospitalizacionPacienteSalvado == null)
            {
                respuestaServidor.anadirMensaje("No se pudo guardar el registro");
                return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
            }

            respuestaServidor = new RespuestaServidor(HttpStatus.OK, "Se guardó correctamente");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }catch(Exception e)
        {
            return new ResponseEntity<>(null, HttpStatus.EXPECTATION_FAILED);
        }
    }

    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody HospitalizacionPaciente hospitalizacionPaciente)
    {
        try {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.UNPROCESSABLE_ENTITY);

             //Validaciones de los datos
            if(hospitalizacionPaciente.getFechaSalida()!=null && hospitalizacionPaciente.getFechaSalida().isBefore(hospitalizacionPaciente.getFechaIngreso()) )
                respuestaServidor.anadirMensaje("La fecha de salida es anterior a la de entrada" );
            
            //Si hay error con los datos enviados devulve los errores
            if(!respuestaServidor.getMensajes().isEmpty())
                return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());

            //Intenta actualizar el objeto
            HospitalizacionPaciente HospitalizacionPacienteSalvado = servicio.actualizar(hospitalizacionPaciente);

            //Si no se pudo actualizar devuelve error
            if(HospitalizacionPacienteSalvado == null)
            {
                respuestaServidor.anadirMensaje("No se pudo actualizar el registro de Hospitalizacion con el id: " + hospitalizacionPaciente.getIdHospitalizacion());
                return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
            }

            //Si todo salió bien devuelve mensaje del servidor OK

            respuestaServidor = new RespuestaServidor(HttpStatus.OK, "Se actualizó correctamente");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());

        } catch (Exception e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrar(@PathVariable("id") Integer id)
    {
        try {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.NOT_FOUND);

            HospitalizacionPaciente hospitalizacion = servicio.borrar(id);

            if(hospitalizacion == null)
            {
                respuestaServidor.anadirMensaje( "No existe el registro con el siguiente id: " + id);
                return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
            }

            respuestaServidor = new RespuestaServidor( HttpStatus.OK, "Se borró correctamente");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        } catch (Exception e) {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }


}