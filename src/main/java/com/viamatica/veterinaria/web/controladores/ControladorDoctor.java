package com.viamatica.veterinaria.web.controladores;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/controlador")
public class ControladorDoctor {
    @GetMapping("doctor")
    public String doctor(){
        return "Controlador Doctor";
    }
}
