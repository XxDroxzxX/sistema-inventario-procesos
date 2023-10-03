package com.sistema.inventario.controller;
import com.sistema.inventario.service.CategoryService;
import com.sistema.inventario.model.CategoryModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    @PostMapping("categorys")
    public ResponseEntity <CategoryModel> create(@RequestBody CategoryModel category){
        return new ResponseEntity(categoryService.createCategoryModel(category), HttpStatus.CREATED);
    }

    @GetMapping("categorys/{id}")
    public ResponseEntity <CategoryModel> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryById(id));
    }

    @PutMapping("categorys/{id}")
    public ResponseEntity <CategoryModel> updateCategory(@RequestBody CategoryModel category, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCategory(category , id));
    }

    @DeleteMapping("categorys/{id}")
    public ResponseEntity <String> deleteCategoryById(@PathVariable Long id){
        return new ResponseEntity("Se elimino el usuario" , HttpStatus.NO_CONTENT);
    }

    @GetMapping("categorys")
    public ResponseEntity <List<CategoryModel>> getAllgetAll(){
        return ResponseEntity.ok(categoryService.findAllCategorys());
    }

}