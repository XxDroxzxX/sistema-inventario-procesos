package com.sistema.inventario.service;

import com.sistema.inventario.jwtAuth.AuthResponse;
import com.sistema.inventario.jwtAuth.LoginRequest;
import com.sistema.inventario.jwtAuth.RegisterRequest;
import com.sistema.inventario.exception.AlreadyExistsException;
import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.AuthRepository;
import com.sistema.inventario.util.ExceptionsConstants;
import com.sistema.inventario.util.Rol;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class AuthService {

    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = authRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new NotFoundException(ExceptionsConstants.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<UserModel> existingUserByEmail = authRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(ExceptionsConstants.USER_ALREADY_EXISTS.getMessage());
        }
    
        UserModel userModel = UserModel.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .phone(request.getPhone())
                .document(request.getDocument())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .rol(Rol.USER)
                .build();
        authRepository.save(userModel);
        return AuthResponse.builder().token(jwtService.getToken(userModel)).build();
    }
}
