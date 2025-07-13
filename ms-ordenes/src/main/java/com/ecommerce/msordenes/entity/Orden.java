package com.ecommerce.msordenes.entity;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "orden")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Orden {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long usuarioId;
    @ElementCollection
    @CollectionTable(
            name = "orden_producto",
            joinColumns = @JoinColumn(name = "orden_id")
    )
    @Column(name = "producto_id")
    private List<Long> productosIds;
    private LocalDateTime fecha;
}
