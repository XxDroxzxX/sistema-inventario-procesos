package com.sistema.inventario.service;

import com.sistema.inventario.exception.AlreadyExistsException;
import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.CategoryModel;
import com.sistema.inventario.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    @Autowired
    private CategoryRepository categoryRepositories;

    public CategoryModel createItem(CategoryModel category){
        if (categoryRepositories.findByNameCategory(category.getNameCategory()).isPresent()) {
            throw new AlreadyExistsException("Category with name " + category.getNameCategory() + " already exists");
        }
        return categoryRepositories.save(category);
    }

    public CategoryModel getItemByid(Long id){
        Optional<CategoryModel> category = categoryRepositories.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        return category.get();
    }

    public CategoryModel updateItem(CategoryModel category, Long id){
        if(!categoryRepositories.existsById(id)){
            throw new NotFoundException("Category not found");
        }
        Optional<CategoryModel> existingCategoryOptional = categoryRepositories.findByNameCategory(category.getNameCategory());
        if (existingCategoryOptional.isPresent() && !existingCategoryOptional.get().getCategory_id().equals(id)) {
            throw new AlreadyExistsException("Category with name " + category.getNameCategory() + " already exists");
        }
        CategoryModel categoryDB = categoryRepositories.findById(id).get();
        categoryDB.setNameCategory(category.getNameCategory());
        categoryDB.setDescription(category.getDescription());
        categoryDB.setStatus(category.getStatus());
        categoryDB.setDisplayOrder(category.getDisplayOrder());

        return categoryRepositories.save(categoryDB);

    }

    public boolean deleteCategoryById(Long id){
        if(categoryRepositories.existsById(id)){
            categoryRepositories.deleteById(id);
            return true;
        } else {
            throw new NotFoundException("Category not found");
        }

    }

    public List<CategoryModel> findAllCategory(){
        List<CategoryModel> categories =  categoryRepositories.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("No categories found");
        }
        return categories;
}


}
