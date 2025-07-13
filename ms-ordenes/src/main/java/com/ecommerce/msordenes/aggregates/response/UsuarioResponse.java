package com.ecommerce.msordenes.aggregates.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioResponse {
    private Long id;
    private String nombre;
    private String email;
    private String rol;
}
