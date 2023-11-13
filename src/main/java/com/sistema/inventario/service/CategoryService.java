package com.sistema.inventario.service;
import com.sistema.inventario.exceptions.AlreadyExistsException;
import com.sistema.inventario.exceptions.NotFoundException;
import com.sistema.inventario.repository.CategoryRepository;
import jakarta.persistence.Id;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.sistema.inventario.model.CategoryModel;
import java.nio.file.FileAlreadyExistsException;
import java.rmi.AlreadyBoundException;
import java.util.List;
import java.util.Optional;

@Service
public class CategoryService {
    @Autowired
    private CategoryRepository categoryRepository;

    public CategoryModel createCategoryModel(CategoryModel category){
        if (categoryRepository.findByNameCategory(category.getNameCategory()).isPresent()){
            throw new AlreadyExistsException("Category with name "+ category.getNameCategory()+ "already exists");
        }
        return categoryRepository.save(category);
    }

    public CategoryModel getCategoryById (Long id){
        Optional<CategoryModel> category = categoryRepository.findById(id);
        if (category.isEmpty()){
            throw new NotFoundException("Category not found");
        }

        return category.get();
    }

    public CategoryModel updateCategory (CategoryModel category, Long id){
       if(!categoryRepository.existsById(id)){
           throw new NotFoundException("Category not found");
       }
       Optional<CategoryModel> existingCategoryOptional = categoryRepository.findByNameCategory(category.getNameCategory());

       if (existingCategoryOptional.isPresent() && !existingCategoryOptional.get().getId().equals(id)) {
           throw new AlreadyExistsException("Category with name "+ category.getNameCategory()+ "already exists");
       }

          CategoryModel categoryBd = categoryRepository.findById(id).get();
          categoryBd.setNameCategory(category.getNameCategory());
          categoryBd.setType(category.getType());
          categoryBd.setDescription(category.getDescription());


          return categoryRepository.save(categoryBd);
    }

    public Boolean deleteCategoryById(Long id){
        if (categoryRepository.existsById(id)){
            categoryRepository.deleteById(id);
            return true;
        }else {
            throw new NotFoundException("Category not found");
        }
    }

    public List<CategoryModel> findAllCategorys(){
        List<CategoryModel> category = categoryRepository.findAll();
        if (category.isEmpty()){
            throw new NotFoundException("Not categories found");
    }
        return category;
    }
}
