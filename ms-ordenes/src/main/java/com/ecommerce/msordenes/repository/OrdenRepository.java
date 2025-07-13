package com.ecommerce.msordenes.repository;

import com.ecommerce.msordenes.entity.Orden;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdenRepository extends JpaRepository<Orden, Long> {
}
