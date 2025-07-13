package com.ecommerce.msauth.aggregates.response;

import com.ecommerce.msauth.entity.Rol;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class ValidateResponse {
    private Long id;
    private String nombre;
    private String email;
    private Rol rol;
}
