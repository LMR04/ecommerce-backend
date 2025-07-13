package com.ecommerce.msauth.aggregates.response;

import com.ecommerce.msauth.entity.Rol;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthResponse {
    private String email;
    private String nombre;
    private Rol rol;
}
