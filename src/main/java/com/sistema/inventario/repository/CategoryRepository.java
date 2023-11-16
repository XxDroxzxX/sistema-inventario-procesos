package com.sistema.inventario.repository;

import com.sistema.inventario.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;


public interface CategoryRepository extends CrudRepository<CategoryModel,Long>{
    Optional<CategoryModel> findByNameCategory(String nameCategory);

}
