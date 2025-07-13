package com.ecommerce.msauth.service.impl;

import com.ecommerce.msauth.aggregates.constants.Constants;
import com.ecommerce.msauth.aggregates.request.LoginRequest;
import com.ecommerce.msauth.aggregates.request.RegisterRequest;
import com.ecommerce.msauth.aggregates.response.LoginResponse;
import com.ecommerce.msauth.aggregates.response.ValidateResponse;
import com.ecommerce.msauth.entity.Rol;
import com.ecommerce.msauth.entity.Usuario;
import com.ecommerce.msauth.repository.UsuarioRepository;
import com.ecommerce.msauth.service.AuthenticationService;
import com.ecommerce.msauth.service.JwtService;
import com.ecommerce.msauth.service.UsuarioService;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UsuarioRepository usuarioRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;
    private final UsuarioService usuarioService;

    @Override
    public Usuario register(RegisterRequest registerRequest) {
        Usuario usuario = getUsuarioEntity(registerRequest);
        return usuarioRepository.save(usuario);
    }

    @Override
    public LoginResponse login(LoginRequest loginRequest) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail()
        , loginRequest.getPassword()));

        Usuario usuario = usuarioRepository.findByEmail(loginRequest.getEmail())
                .orElseThrow(()-> new UsernameNotFoundException("Usuario no encontrado"));

        String accessToken = jwtService.generateToken(usuario);
        String refreshToken = jwtService.generateRefreshToken(new HashMap<>(), usuario);

        return LoginResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public ValidateResponse validate(String authorizationHeader){
        if (authorizationHeader == null || !authorizationHeader.startsWith("Bearer ")) {
            throw new RuntimeException("Token faltante o mal formado");
        }

        String token = authorizationHeader.substring(7);

        try {
            String username = jwtService.extractUsername(token);
            UserDetails userDetails = usuarioService.userDetailsService()
                    .loadUserByUsername(username);

            boolean isValid = jwtService.validateToken(token, userDetails)
                    && !jwtService.validateIsRefreshToken(token);

            if(!isValid){
                throw new RuntimeException("Token invalido");
            }

            Claims claims = jwtService.extractAllClaims(token);

            return ValidateResponse.builder()
                    .id(claims.get("id", Long.class))
                    .email(claims.get("email", String.class))
                    .nombre(claims.get("nombre", String.class))
                    .rol(Rol.valueOf(claims.get("rol", String.class)))
                    .build();
        } catch (Exception e) {
            throw new RuntimeException("Token invalido", e);
        }
    }

    private Usuario getUsuarioEntity(RegisterRequest registerRequest) {
        return Usuario.builder()
                .nombre(registerRequest.getNombre())
                .email(registerRequest.getEmail())
                .password(new BCryptPasswordEncoder().encode(registerRequest.getPassword()))
                .rol(Rol.valueOf(registerRequest.getRol()))
                .isAccountNonExpired(Constants.STATUS_ACTIVE)
                .isAccountNonLocked(Constants.STATUS_ACTIVE)
                .isCredentialsNonExpired(Constants.STATUS_ACTIVE)
                .isEnabled(Constants.STATUS_ACTIVE)
                .build();
    }
}
