package com.ecommerce.msauth.controller;

import com.ecommerce.msauth.entity.Rol;
import com.ecommerce.msauth.entity.Usuario;
import com.ecommerce.msauth.repository.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/test")
public class TestController {

    private final UsuarioRepository usuarioRepository;

    @GetMapping("/superadmin")
    public ResponseEntity<List<Usuario>> getAllSuperadmin() {
        return ResponseEntity.ok(usuarioRepository.findByRol(Rol.SUPERADMIN));
    }

    @GetMapping("/admin")
    public ResponseEntity<List<Usuario>> getAllAdmin() {
        return ResponseEntity.ok(usuarioRepository.findByRol(Rol.ADMIN));
    }

    @GetMapping("/user")
    public ResponseEntity<List<Usuario>> getAllUser() {
        return ResponseEntity.ok(usuarioRepository.findByRol(Rol.USER));
    }
}
