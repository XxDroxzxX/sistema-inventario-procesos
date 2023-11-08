package com.sistema.inventario.repository;

import com.sistema.inventario.model.CategoryModel;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CategoryRepository extends CrudRepository<CategoryModel,Long>{
    //List<CategoryModel> findByNameCategoryAndid(String NameCategory,Long id);
}
