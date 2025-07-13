package com.ecommerce.msordenes.service.impl;

import com.ecommerce.msordenes.aggregates.request.OrdenRequest;
import com.ecommerce.msordenes.service.ProductValidatorService;
import com.ecommerce.msordenes.service.TokenValidatorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductValidatorServiceImpl implements ProductValidatorService {

    private final RestTemplate restTemplate = new RestTemplate();
    @Value("${PRODUCTO_URL}")
    private String productUrl;
    private final TokenValidatorService tokenValidatorService;

    @Override
    public void validarProductoExiste(OrdenRequest ordenRequest, HttpServletRequest request) {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        String authHeader = tokenValidatorService.extractToken(request);
        headers.set("Authorization", authHeader);

        HttpEntity<OrdenRequest> entity = new HttpEntity<>(ordenRequest, headers);

        ResponseEntity<Boolean> response = restTemplate.exchange(
                productUrl,
                HttpMethod.POST,
                entity,
                Boolean.class
        );

        Boolean valid = response.getBody();
        if (valid == null || !valid) {
            throw new RuntimeException("Uno o más productos no existen");
        }
    }
}
