package com.ecommerce.msproductos.service;

import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface TokenValidatorService {
    void validarRol(HttpServletRequest request, List<String> roles);
}
