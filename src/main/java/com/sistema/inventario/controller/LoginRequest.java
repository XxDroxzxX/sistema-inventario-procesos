package com.sistema.inventario.controller;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginRequest {
    @NotNull(message = "email is required")
    @Email(message = "Email no valid")

    private String email;
    @NotNull(message = "Password is required")
    @Size(min = 8, max = 255,message = "password min 8 characters ")
    private String password;
}
