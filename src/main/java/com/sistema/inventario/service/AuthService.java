package com.sistema.inventario.service;

import com.sistema.inventario.controller.AuthResponse;
import com.sistema.inventario.controller.LoginRequest;
import com.sistema.inventario.exception.AlreadyExistsException;
import com.sistema.inventario.exception.AuthenticationFailedException;
import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.repository.UserRepository;
import com.sistema.inventario.util.Constants;
import com.sistema.inventario.util.Rol;
import jakarta.validation.Valid;
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

    private final UserRepository userRepository;

    private final JwtService jwtService;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    //se hace cambio a un try cacht

    public AuthResponse login(LoginRequest request) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    request.getEmail(), request.getPassword()));
        } catch (Exception e) {
            throw new AuthenticationFailedException(Constants.CREDENTIAL_INVALID.getMessage());
        }
        UserDetails user = userRepository.findByEmail(request.getEmail()).
                orElseThrow(() -> new NotFoundException(Constants.CREDENTIAL_INVALID.getMessage()));
        String token = jwtService.getToken(user);
        return AuthResponse.builder().token(token).build();
    }

    public AuthResponse register(@Valid UserModel request) {
        Optional<UserModel> existingUserByEmail = userRepository.findByEmail(request.getEmail());
        if (existingUserByEmail.isPresent()) {
            throw new AlreadyExistsException(Constants.USER_ALREADY_EXISTS.getMessage());
        }
    
        Optional<UserModel> existingUserByDocument = userRepository.findByDocument(request.getDocument());
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
        userRepository.save(userModel);
        return AuthResponse.builder().token(jwtService.getToken(userModel)).build();
    }
}
