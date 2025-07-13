package com.ecommerce.msproductos.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ValidarProductRequest {
    private List<Long> productosids;
}
