package com.viamatica.veterinaria.web.controladores;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
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
import com.viamatica.veterinaria.dominio.servicio.HosTipoCirugiaServicio;
import com.viamatica.veterinaria.web.RespuestaServidor;

import jakarta.annotation.security.RolesAllowed;



@RestController
@RequestMapping("/HosCirugia")
class HosCirugiaControlador {

    @Autowired
    HosCirugiaServicio servicioCirugia;

    @Autowired
    HosTipoCirugiaServicio servicioTipoCirugia;



    //#region lectura
    @GetMapping("{id}")
    public ResponseEntity<Object> obtenerPorId(@PathVariable("id") Integer id)
    {
        try{
            Cirugia Cirugia = servicioCirugia.obtenerPorId(id);

            if (Cirugia == null)
                return new ResponseEntity<>(  new RespuestaServidor(HttpStatus.NOT_FOUND, "No se encontró cirugia con el id:" + id), HttpStatus.NOT_FOUND);

            return new ResponseEntity<>(Cirugia, HttpStatus.OK);
            
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
            List<Cirugia> Cirugias = servicioCirugia.obtenerTodos();

            if(Cirugias.isEmpty())
                return new ResponseEntity<>(new RespuestaServidor(HttpStatus.NO_CONTENT, "No hay datos"), HttpStatus.NO_CONTENT);

            return new ResponseEntity<>(Cirugias, HttpStatus.OK);

        }catch(Exception e)
        {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }



    @GetMapping("/incluidoInactivos")
    public ResponseEntity<Object> obtenerTodosIncluidoInactivos()
    {
        try{
            List<Cirugia> Cirugias = servicioCirugia.obtenerTodosIncluidoInactivos();

            if(Cirugias.isEmpty())
                return new ResponseEntity<>(new RespuestaServidor(HttpStatus.NO_CONTENT, "No hay datos"), HttpStatus.NO_CONTENT);  

            return new ResponseEntity<>(Cirugias, HttpStatus.OK);

        }catch(Exception e)
        {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }

    //#endregion


    @PostMapping
    public ResponseEntity<Object> crear(@RequestBody Cirugia cirugia)
    {
        try{
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.UNPROCESSABLE_ENTITY);
            cirugia.setIdHosCirugia(null);

            //Validaciones de los datos
            if( cirugia.getFechaProgramada().isBefore(LocalDateTime.now())) 
                respuestaServidor.anadirMensaje("La fecha es anterior a la fecha actual");

            if(servicioTipoCirugia.obtenerPorId(cirugia.getTipoCirugia().getIdHosTipoCirugia()) == null)
                respuestaServidor.anadirMensaje("El tipo de cirugia no se encuentra en el registro");

            //Si hay error con los datos enviados devulve los errores
            if(!respuestaServidor.getMensajes().isEmpty())
                return new ResponseEntity<Object>(respuestaServidor, respuestaServidor.getStatus());

            //Intenta guardar el objeto
            servicioCirugia.guardar(cirugia);

            respuestaServidor = new RespuestaServidor(HttpStatus.CREATED, "Se guardó correctamente");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());

        }catch(Exception e)
        {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }



    @PutMapping
    public ResponseEntity<Object> actualizar(@RequestBody Cirugia cirugia)
    {
        try {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.UNPROCESSABLE_ENTITY);

            //Validaciones de los datos
            if( cirugia.getFechaProgramada().isBefore(LocalDateTime.now())) 
                respuestaServidor.anadirMensaje("La fecha es anterior a la fecha actual");

            if(servicioTipoCirugia.obtenerPorId(cirugia.getTipoCirugia().getIdHosTipoCirugia()) == null)
                respuestaServidor.anadirMensaje("El tipo de cirugia no se encuentra en el registro");

            //Si hay error con los datos enviados devulve los errores
            if(!respuestaServidor.getMensajes().isEmpty())
                return new ResponseEntity<Object>(respuestaServidor, respuestaServidor.getStatus());    
            
            //Intenta actualizar el objeto
            Cirugia CirugiaSalvado = servicioCirugia.actualizar(cirugia);

            if(CirugiaSalvado == null)
                respuestaServidor.anadirMensaje("No Existe en el registro la cirugia con el id: " + cirugia.getIdHosCirugia());

            //Si no se pudo actualizar devuelve error
            if(!respuestaServidor.getMensajes().isEmpty())
                return new ResponseEntity<Object>(respuestaServidor, respuestaServidor.getStatus());
            
            //Si todo salió bien devuelve mensaje del servidor OK
            respuestaServidor = new RespuestaServidor(HttpStatus.OK, "Se actualizó correctamente");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());

        } catch (Exception e) {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }



    @DeleteMapping("{id}")
    public ResponseEntity<Object> borrar(@PathVariable("id") Integer id)
    {
        try {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.NOT_FOUND);

            Cirugia cirugiaBorrado = servicioCirugia.borrar(id);

            if(cirugiaBorrado == null)
            {
                respuestaServidor.anadirMensaje( "No existe el registro con el siguiente id: " + id);
                return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
            }

            respuestaServidor = new RespuestaServidor( HttpStatus.OK, "Se borró correctamente");
            return new ResponseEntity<>( respuestaServidor , respuestaServidor.getStatus());

        } catch (Exception e) {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }



    @GetMapping("/fecha/{fecha}")
    public ResponseEntity<Object> cirugiasPorDia(@PathVariable LocalDate fecha)
    {
        try {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.NOT_FOUND);
            var lista =  new ArrayList<Cirugia>(servicioCirugia.obtenerPorFechaProgramada(fecha));

            if(lista.isEmpty())
            {
                respuestaServidor.anadirMensaje("No hay cirugias en la fecha: " + fecha);
                return new ResponseEntity<>(respuestaServidor,respuestaServidor.getStatus());
            }

            return new ResponseEntity<>( lista, HttpStatus.OK);

        } catch (Exception e) {
            RespuestaServidor respuestaServidor = new RespuestaServidor(HttpStatus.INTERNAL_SERVER_ERROR,"Hubo un error en el servidor");
            return new ResponseEntity<>(respuestaServidor, respuestaServidor.getStatus());
        }
    }

}
