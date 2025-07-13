package com.ecommerce.msordenes.service;

import com.ecommerce.msordenes.aggregates.request.OrdenRequest;
import com.ecommerce.msordenes.entity.Orden;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface OrdenService {
    Orden crearOrden(OrdenRequest ordenRequest, HttpServletRequest request);
    List<Orden> listarOrdenes(HttpServletRequest request);
}
