package com.sistema.inventario.repository;

import com.sistema.inventario.model.CategoryModel;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CategoryRepository extends JpaRepository<CategoryModel,Long> {
    Optional<CategoryModel> findByNameCategory(String nameCategory);

}