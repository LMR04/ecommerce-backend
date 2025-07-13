package com.ecommerce.msordenes.aggregates.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProductoResponse {
    private Long id;
    private String nombre;
    private Double precio;
    private String categoria;
}
