package com.ecommerce.msordenes.service.impl;

import com.ecommerce.msordenes.aggregates.response.UsuarioResponse;
import com.ecommerce.msordenes.service.TokenValidatorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
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
public class TokenValidatorServiceImpl  implements TokenValidatorService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${VALIDATE_URL}")
    private String validateUrl;

    @Override
    public void validarToken(HttpServletRequest request, List<String> roles) {
        UsuarioResponse usuario = obtenerUsuario(request);
        if(usuario == null || !roles.contains(usuario.getRol())) {
            throw new RuntimeException("Acceso denegado - rol no autorizado");
        }
    }

    @Override
    public UsuarioResponse obtenerUsuario(HttpServletRequest request) {
        String token = extractToken(request).substring(7);
        HttpHeaders headers = new HttpHeaders();
        headers.setBearerAuth(token);
        HttpEntity<Void> entity = new HttpEntity<>(headers);

        ResponseEntity<UsuarioResponse> response = restTemplate.exchange(
                validateUrl, HttpMethod.GET, entity, UsuarioResponse.class
        );

        UsuarioResponse usuario = response.getBody();

        return usuario;
    }

    @Override
    public String extractToken(HttpServletRequest request) {
        String authHeader = request.getHeader("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token no presente o mal formado");
        }

        return authHeader;
    }
}
