package com.sistema.inventario.repository;

import com.sistema.inventario.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface AuthRepository extends JpaRepository<UserModel, Integer> {
    public Optional<UserModel> findByEmail(String email);
    public Optional<UserModel> findByDocument(String document);
}
