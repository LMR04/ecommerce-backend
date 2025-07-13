package com.ecommerce.msauth.service.impl;

import com.ecommerce.msauth.aggregates.constants.Constants;
import com.ecommerce.msauth.entity.Usuario;
import com.ecommerce.msauth.service.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Component
public class JwtServiceImpl implements JwtService {

    @Override
    public boolean validateIsRefreshToken(String token) {
        Claims claims = extractAllClaims(token);
        String type = claims.get("type", String.class);
        return Constants.REFRESH.equalsIgnoreCase(type);
    }

    @Value("${key.signature}")
    private String keySignature;


    @Override
    public String generateToken(Usuario usuario) {
        Map<String, Object> claims = addClaim(usuario);
        claims.put("type", Constants.ACCESS);
        claims.put("id", usuario.getId());
        claims.put("nombre", usuario.getNombre());
        claims.put("email", usuario.getEmail());
        claims.put("password", usuario.getPassword());
        claims.put("rol", usuario.getRol());

        Date now = new Date();
        Date expiration = new Date(now.getTime() + 60000);
        return Jwts.builder()
                .setHeaderParam("typ", "JWT")
                .setClaims(claims)
                .setSubject(usuario.getUsername())
                .setIssuedAt(now)
                .setExpiration(expiration)
                .signWith(getSignKey(), SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String generateRefreshToken(Map<String, Object> extraClaims, UserDetails userDetails) {
        return Jwts.builder()
                .setClaims(extraClaims != null ? extraClaims : new HashMap<>())
                .setSubject(userDetails.getUsername())
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 600000))
                .claim("type", Constants.REFRESH)
                .signWith(getSignKey(),SignatureAlgorithm.HS512)
                .compact();
    }

    @Override
    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    @Override
    public boolean validateToken(String token, UserDetails userDetails) {
        final String username = extractUsername(token);
        return (username.equals(userDetails.getUsername())
                && !isTokenExpired(token));
    }

    public boolean isTokenExpired(String token) {
        return extractClaim(token, Claims::getExpiration).before(new Date());
    }

    private Key getSignKey(){
        byte[] key = Decoders.BASE64.decode(keySignature);
        return Keys.hmacShaKeyFor(key);
    }

    private Map<String, Object> addClaim(Usuario usuario) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("accountNonExpired",usuario.isAccountNonExpired());
        claims.put("accountNonLocked",usuario.isAccountNonLocked());
        claims.put("credentialsNonExpired",usuario.isCredentialsNonExpired());
        claims.put("enabled",usuario.isEnabled());
        claims.put("role", "ROLE_" + usuario.getRol().name());
        return claims;
    }

    @Override
    public Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignKey())
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public <T> T extractClaim(String token, Function<Claims, T> claimsTFunction) {
        return claimsTFunction.apply(extractAllClaims(token));
    }

}
