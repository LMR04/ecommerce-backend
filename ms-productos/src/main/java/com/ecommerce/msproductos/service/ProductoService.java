package com.ecommerce.msproductos.service;

import com.ecommerce.msproductos.aggregates.request.ProductoRequest;
import com.ecommerce.msproductos.aggregates.request.ValidarProductRequest;
import com.ecommerce.msproductos.entity.Producto;
import jakarta.servlet.http.HttpServletRequest;

import java.util.List;

public interface ProductoService {
    Producto crearProducto(ProductoRequest productoRequest, HttpServletRequest request);
    List<Producto> listarProducto(HttpServletRequest request);
    Producto editarProducto(Long id, ProductoRequest productoRequest, HttpServletRequest request);
    void eliminarProducto(Long id, HttpServletRequest request);
    boolean validarProductos(ValidarProductRequest validarProductRequest, HttpServletRequest request);
}
