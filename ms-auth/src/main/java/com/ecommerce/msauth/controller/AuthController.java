package com.ecommerce.msauth.controller;

import com.ecommerce.msauth.aggregates.request.LoginRequest;
import com.ecommerce.msauth.aggregates.request.RegisterRequest;
import com.ecommerce.msauth.aggregates.response.LoginResponse;
import com.ecommerce.msauth.aggregates.response.ValidateResponse;
import com.ecommerce.msauth.entity.Usuario;
import com.ecommerce.msauth.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
@RefreshScope
public class AuthController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<Usuario> register(@RequestBody RegisterRequest registerRequest) {
        return ResponseEntity.ok(authenticationService.register(registerRequest));
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity.ok(authenticationService.login(loginRequest));
    }

    @GetMapping("/validate")
    public ResponseEntity<ValidateResponse> validate(@RequestHeader("Authorization") String authorizationHeader) {
        return ResponseEntity.ok(authenticationService.validate(authorizationHeader));
    }
}
