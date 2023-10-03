package com.sistema.inventario.service;

import com.sistema.inventario.repository.CategoryRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.inventario.model.CategoryModel;
import java.util.List;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategoryModel(CategoryModel category){
        return categoryRepository.save(category);
    }

    public CategoryModel getCategoryById (Long id){
        return categoryRepository.findById(id).get();
    }

    public CategoryModel updateCategory (CategoryModel category, Long id){
       if(categoryRepository.existsById(id)){
           CategoryModel categoryBd = categoryRepository.findById(id).get();
          categoryBd.setNameCategory(category.getNameCategory());
          categoryBd.setType(category.getType());
          categoryBd.setDescription(category.getDescription());
          return categoryRepository.save(categoryBd);
        }
       return null;
    }

    public Boolean deleteCategoryById(Long id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public List<CategoryModel> findAllCategorys(){
        return (List<CategoryModel>) categoryRepository.findAll();
    }
}
