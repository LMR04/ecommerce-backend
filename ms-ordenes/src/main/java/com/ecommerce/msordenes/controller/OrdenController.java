package com.ecommerce.msordenes.controller;

import com.ecommerce.msordenes.aggregates.request.OrdenRequest;
import com.ecommerce.msordenes.entity.Orden;
import com.ecommerce.msordenes.service.OrdenService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequiredArgsConstructor
@RequestMapping("/ordenes")
public class OrdenController {

    private final OrdenService ordenService;

    @PostMapping
    public ResponseEntity<Orden> crearOrden(@RequestBody OrdenRequest ordenRequest, HttpServletRequest request) {
        return ResponseEntity.ok(ordenService.crearOrden(ordenRequest, request));
    }

    @GetMapping
    public ResponseEntity<List<Orden>> listarOrdenes(HttpServletRequest request) {
        return ResponseEntity.ok(ordenService.listarOrdenes(request));
    }
}
