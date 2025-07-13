package com.ecommerce.msproductos.repository;

import com.ecommerce.msproductos.entity.Producto;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoRepository extends JpaRepository<Producto, Long> {
}
