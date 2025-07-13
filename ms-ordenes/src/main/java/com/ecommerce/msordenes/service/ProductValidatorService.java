package com.ecommerce.msordenes.service;

import com.ecommerce.msordenes.aggregates.request.OrdenRequest;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ProductValidatorService {
    void validarProductoExiste(OrdenRequest ordenRequest, HttpServletRequest request);
}
