package com.sistema.inventario.controller;

import com.sistema.inventario.model.CategoryModel;
import com.sistema.inventario.service.CategoryService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class CategoryController{
    @Autowired
    private CategoryService categoryService;

    @PostMapping("categorys")
    public ResponseEntity<CategoryModel> create(@Valid @RequestBody CategoryModel category){
        return new ResponseEntity<>(categoryService.createCategory(category), HttpStatus.CREATED);
    }

    @GetMapping("categorys/{id}")
    public ResponseEntity <CategoryModel> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getCategoryByid(id));
    }

    @PutMapping("categorys/{id}")
    public ResponseEntity <CategoryModel> updateCategory(@Valid @RequestBody CategoryModel category, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateCategory(category,id));
    }

    @DeleteMapping("categorys/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable Long id){
    categoryService.deleteCategory(id);
    return ResponseEntity.noContent().build();
    }

    @GetMapping("categorys")
    public ResponseEntity<List<CategoryModel>> getAll(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

}