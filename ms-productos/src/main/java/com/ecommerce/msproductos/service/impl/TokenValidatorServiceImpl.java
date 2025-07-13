package com.ecommerce.msproductos.service.impl;

import com.ecommerce.msproductos.aggregates.response.UsuarioResponse;
import com.ecommerce.msproductos.service.TokenValidatorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TokenValidatorServiceImpl implements TokenValidatorService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${VALIDATE_URL}")
    private String validateUrl;

    @Override
    public void validarRol(HttpServletRequest request, List<String> roles) {
        String token = extractToken(request);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UsuarioResponse> response = restTemplate.exchange(
                validateUrl, HttpMethod.GET, entity, UsuarioResponse.class
        );

        UsuarioResponse usuario = response.getBody();

        if(usuario == null || !roles.contains(usuario.getRol())) {
            throw new RuntimeException("Acceso denegado - rol no autorizado");
        }
    }

    private String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no presente o mal formado");
        }

        return authHeader.substring(7);
    }
}
