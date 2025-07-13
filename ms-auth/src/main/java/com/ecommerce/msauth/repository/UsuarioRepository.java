package com.ecommerce.msauth.repository;

import com.ecommerce.msauth.entity.Rol;
import com.ecommerce.msauth.entity.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    List<Usuario> findByRol(Rol rol);
    Optional<Usuario> findByEmail(String email);
}
