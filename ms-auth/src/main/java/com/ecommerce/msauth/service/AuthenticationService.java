package com.ecommerce.msauth.service;

import com.ecommerce.msauth.aggregates.request.LoginRequest;
import com.ecommerce.msauth.aggregates.request.RegisterRequest;
import com.ecommerce.msauth.aggregates.response.LoginResponse;
import com.ecommerce.msauth.aggregates.response.ValidateResponse;
import com.ecommerce.msauth.entity.Usuario;

import java.util.List;

public interface AuthenticationService {
    Usuario register(RegisterRequest registerRequest);
    LoginResponse login(LoginRequest loginRequest);
    ValidateResponse validate(String token);
}
