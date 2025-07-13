package com.ecommerce.msordenes.aggregates.request;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OrdenRequest {
    private List<Long> productosids;
}
