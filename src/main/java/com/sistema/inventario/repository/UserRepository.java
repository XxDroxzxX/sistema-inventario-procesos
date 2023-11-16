package com.sistema.inventario.repository;

import com.sistema.inventario.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findByFirstNameAndLastName(String firstName, String lastName);
    Optional<UserModel> findByEmail(String email);

    List<UserModel> findByDocument(String document);
}

