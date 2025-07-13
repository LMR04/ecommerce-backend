package com.ecommerce.msproductos.controller;

import com.ecommerce.msproductos.aggregates.request.ProductoRequest;
import com.ecommerce.msproductos.aggregates.request.ValidarProductRequest;
import com.ecommerce.msproductos.entity.Producto;
import com.ecommerce.msproductos.service.ProductoService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;

    @PostMapping
    public ResponseEntity<Producto> crearProducto(@RequestBody ProductoRequest productoRequest, HttpServletRequest request) {
        return ResponseEntity.ok(productoService.crearProducto(productoRequest, request));
    }

    @GetMapping
    public ResponseEntity<List<Producto>> listarProductos(HttpServletRequest request) {
        return ResponseEntity.ok(productoService.listarProducto(request));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Producto> editarProducto(@PathVariable Long id, @RequestBody ProductoRequest productoRequest, HttpServletRequest request) {
        return ResponseEntity.ok(productoService.editarProducto(id, productoRequest, request));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarProducto(@PathVariable Long id, HttpServletRequest request) {
        productoService.eliminarProducto(id, request);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/validar")
    public ResponseEntity<Boolean> validarProductos(@RequestBody ValidarProductRequest validarProductRequest, HttpServletRequest request) {
        return ResponseEntity.ok(productoService.validarProductos(validarProductRequest, request));
    }
}
