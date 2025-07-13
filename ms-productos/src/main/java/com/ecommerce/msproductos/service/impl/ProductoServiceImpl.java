package com.ecommerce.msproductos.service.impl;

import com.ecommerce.msproductos.aggregates.request.ProductoRequest;
import com.ecommerce.msproductos.aggregates.request.ValidarProductRequest;
import com.ecommerce.msproductos.entity.Producto;
import com.ecommerce.msproductos.repository.ProductoRepository;
import com.ecommerce.msproductos.service.ProductoService;
import com.ecommerce.msproductos.service.TokenValidatorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductoServiceImpl implements ProductoService {

    private final ProductoRepository productoRepository;
    private final TokenValidatorService tokenValidatorService;

    @Override
    public Producto crearProducto(ProductoRequest productoRequest, HttpServletRequest request) {
        tokenValidatorService.validarRol(request, List.of("ADMIN", "SUPERADMIN"));
        Producto producto = Producto.builder()
                .nombre(productoRequest.getNombre())
                .precio(productoRequest.getPrecio())
                .categoria(productoRequest.getCategoria())
                .build();
        return productoRepository.save(producto);
    }

    @Override
    public List<Producto> listarProducto(HttpServletRequest request) {
        tokenValidatorService.validarRol(request, List.of("ADMIN", "SUPERADMIN"));
        return productoRepository.findAll();
    }

    @Override
    public Producto editarProducto(Long id, ProductoRequest productoRequest, HttpServletRequest request) {
        tokenValidatorService.validarRol(request, List.of("ADMIN", "SUPERADMIN"));
        Producto productExist = productoRepository.findById(id)
                .orElseThrow(()-> new RuntimeException("El producto no existe"));
        productExist.setNombre(productoRequest.getNombre());
        productExist.setPrecio(productoRequest.getPrecio());
        productExist.setCategoria(productoRequest.getCategoria());
        return productoRepository.save(productExist);
    }

    @Override
    public void eliminarProducto(Long id, HttpServletRequest request) {
        tokenValidatorService.validarRol(request, List.of("ADMIN", "SUPERADMIN"));
        productoRepository.deleteById(id);
    }

    @Override
    public boolean validarProductos(ValidarProductRequest validarProductRequest, HttpServletRequest request) {
        tokenValidatorService.validarRol(request, List.of("USER"));
        List<Long> exists = productoRepository.findAllById(validarProductRequest.getProductosids())
                .stream()
                .map(Producto::getId)
                .toList();

        boolean allExists = exists.size() == validarProductRequest.getProductosids().size();
        return allExists;
    }
}
