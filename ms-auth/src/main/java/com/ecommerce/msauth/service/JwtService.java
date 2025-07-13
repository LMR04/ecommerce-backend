package com.ecommerce.msauth.service;

import com.ecommerce.msauth.entity.Usuario;
import io.jsonwebtoken.Claims;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;
import java.util.function.Function;

public interface JwtService {
    String generateToken(Usuario usuario);
    String generateRefreshToken(Map<String, Object> extraClaims,
                                UserDetails userDetails);
    String extractUsername(String token);
    boolean validateToken(String token, UserDetails userDetails);
    boolean validateIsRefreshToken(String token);
    Claims extractAllClaims(String token);
    <T> T extractClaim(String token, Function<Claims, T> claimsTFunction);
}
