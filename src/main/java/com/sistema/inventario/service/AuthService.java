package com.sistema.inventario.service;

import com.sistema.inventario.controller.AuthResponse;
import com.sistema.inventario.controller.LoginRequest;
import com.sistema.inventario.controller.RegisterRequest;
import com.sistema.inventario.exceptions.AlreadyExistsException;
import com.sistema.inventario.exceptions.NotFoundException;
import com.sistema.inventario.repository.AuthRepository;
import com.sistema.inventario.util.Constants;
import com.sistema.inventario.util.Rol;
import com.sistema.inventario.model.UserModel;
import lombok.Builder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Builder

public class AuthService {


    private final AuthRepository authRepository;
    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthResponse login(LoginRequest request) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        UserDetails user = authRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new NotFoundException(Constants.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken((UserModel) user);
        return AuthResponse.builder().tokens(token).build();
    }

    public AuthResponse register(RegisterRequest request) {
        Optional<UserModel> existingUserByEmail = authRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(Constants.USER_ALREADY_EXISTS.getMessage());
        }

        Optional<UserModel> existingUserByDocument = authRepository.findByDocument(request.getDocument());
        if (existingUserByDocument.isPresent()) {
            throw new AlreadyExistsException(Constants.DOCUMENT_ALREADY_EXISTS.getMessage());
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
        return AuthResponse.builder().tokens(jwtService.getToken(userModel)).build();
    }
}