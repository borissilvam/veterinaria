package com.viamatica.veterinaria.web.controladores;

import com.auth0.jwt.JWT;
import com.viamatica.veterinaria.dominio.dto.LoginDto;
import com.viamatica.veterinaria.web.config.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    private AuthenticationManager authenticationManager;
    @Autowired
    private JwtUtil jwtUtil;
    @PostMapping("/login")
    public ResponseEntity<Void> login(@RequestBody LoginDto loginDto){
        UsernamePasswordAuthenticationToken login = new
                UsernamePasswordAuthenticationToken(loginDto.getUsername(),
                loginDto.getPassword());
        Authentication authentication = authenticationManager.authenticate(login);

        System.out.println("Bollean : " + authentication.isAuthenticated());
        System.out.println("Principal : " + authentication.getPrincipal());
        System.out.println("ROLES: " + authentication.getAuthorities());

        String jwt = jwtUtil.create(loginDto.getUsername());

        return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, jwt).build();
    }
}
