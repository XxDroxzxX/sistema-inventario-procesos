package com.sistema.inventario.service;

import com.sistema.inventario.exception.AlreadyExistsException;
import com.sistema.inventario.exception.NotFoundException;
import com.sistema.inventario.model.CategoryModel;
import com.sistema.inventario.model.ItemModel;
import com.sistema.inventario.repository.CategoryRepository;
import com.sistema.inventario.repository.ItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CategoryService{
    @Autowired
    private CategoryRepository categoryRepositories;
    @Autowired
    private ItemRepository itemRepository;

    public CategoryModel createCategory(CategoryModel category){
        if (categoryRepositories.findByNameCategory(category.getNameCategory()).isPresent()) {
            throw new AlreadyExistsException("Category with name " + category.getNameCategory() + " already exists");
        }
        return categoryRepositories.save(category);
    }

    public CategoryModel getCategoryByid(Long id){
        Optional<CategoryModel> category = categoryRepositories.findById(id);
        if(category.isEmpty()){
            throw new NotFoundException("Category not found");
        }
        return category.get();
    }

    public CategoryModel updateCategory(CategoryModel category, Long id){
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
        //agrego el campo status para actualizarlo en la bd
        categoryDB.setStatus(category.getStatus());
        categoryDB.setDisplayOrder(category.getDisplayOrder());

        return categoryRepositories.save(categoryDB);

    }


    public void deleteCategory(Long id){
        Optional<CategoryModel> unassignedCategoryOptional = categoryRepositories.findByNameCategory("default");
        CategoryModel unassignedCategory;
        if(!unassignedCategoryOptional.isPresent()) {
            unassignedCategory = new CategoryModel();
            unassignedCategory.setNameCategory("default");
            unassignedCategory.setDescription("default");
            unassignedCategory.setStatus("Activo");
            unassignedCategory.setDisplayOrder("on start");
            categoryRepositories.save(unassignedCategory);
        } else {
            unassignedCategory = unassignedCategoryOptional.get();
        }

        CategoryModel categoryToDelete = categoryRepositories.findById(id)
                .orElseThrow(() -> new RuntimeException("La categoría con id " + id + " no existe"));

        List<ItemModel> items = itemRepository.findByCategory(categoryToDelete);
        for(ItemModel item : items){
            item.setCategory(unassignedCategory);
            itemRepository.save(item);
        }

        categoryRepositories.delete(categoryToDelete);
    }


    public List<CategoryModel> findAllCategory(){
        List<CategoryModel> categories =  categoryRepositories.findAll();
        if (categories.isEmpty()) {
            throw new NotFoundException("No categories found");
        }
        return categories;
    }


}