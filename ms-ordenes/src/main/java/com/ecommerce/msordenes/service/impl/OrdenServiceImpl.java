package com.ecommerce.msordenes.service.impl;

import com.ecommerce.msordenes.aggregates.request.OrdenRequest;
import com.ecommerce.msordenes.aggregates.response.UsuarioResponse;
import com.ecommerce.msordenes.entity.Orden;
import com.ecommerce.msordenes.repository.OrdenRepository;
import com.ecommerce.msordenes.service.OrdenService;
import com.ecommerce.msordenes.service.ProductValidatorService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrdenServiceImpl implements OrdenService {

    private final OrdenRepository ordenRepository;
    private final TokenValidatorServiceImpl tokenValidatorService;
    private final ProductValidatorService productValidatorService;

    @Override
    public Orden crearOrden(OrdenRequest ordenRequest, HttpServletRequest request) {
        tokenValidatorService.validarToken(request, List.of("USER"));
        UsuarioResponse usuario = tokenValidatorService.obtenerUsuario(request);
        productValidatorService.validarProductoExiste(ordenRequest, request);

        Orden newOrden = Orden.builder()
                .usuarioId(usuario.getId())
                .productosIds(ordenRequest.getProductosids())
                .fecha(LocalDateTime.now())
                .build();

        return ordenRepository.save(newOrden);
    }

    @Override
    public List<Orden> listarOrdenes(HttpServletRequest request) {
        tokenValidatorService.validarToken(request, List.of("ADMIN", "SUPERADMIN"));
        return ordenRepository.findAll();
    }
}
