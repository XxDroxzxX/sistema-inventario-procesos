package com.sistema.inventario.repository;

import com.sistema.inventario.model.UserModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserModel,Long> {
    List<UserModel> findByFirstNameAndLastName(String firstName, String lastName);
    List<UserModel> findByDocument(String document);
}

