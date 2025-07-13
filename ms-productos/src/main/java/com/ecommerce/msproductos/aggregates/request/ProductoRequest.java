package com.ecommerce.msproductos.aggregates.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoRequest {
    private String nombre;
    private Double precio;
    private String categoria;
}
