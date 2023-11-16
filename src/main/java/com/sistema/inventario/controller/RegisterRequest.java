package com.sistema.inventario.controller;

import com.sistema.inventario.model.AddressModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Builder
@Data

public class RegisterRequest {
    private String firstName;
    private String lastName;
   private String email;
    private String phone;
   private String password;
   private String document;
}
