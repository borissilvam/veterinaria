package com.viamatica.veterinaria.dominio.validaciones;

import java.time.LocalDateTime;

public class Validador {
 
    public static boolean fechaAntesDeHoy(LocalDateTime fecha)
    {
        boolean flag = false;

        if(
            fecha.isBefore(LocalDateTime.now())
        )
        {
            flag = true;
        }

        return flag;
    }


}
