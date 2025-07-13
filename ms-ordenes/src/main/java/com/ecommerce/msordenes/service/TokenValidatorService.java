package com.ecommerce.msordenes.service;

import com.ecommerce.msordenes.aggregates.response.UsuarioResponse;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TokenValidatorService {
    UsuarioResponse obtenerUsuario(HttpServletRequest request);
    void validarToken(HttpServletRequest request, List<String> roles);
    String extractToken(HttpServletRequest request);
}
