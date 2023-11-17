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

    @PostMapping("category")
    public ResponseEntity<CategoryModel> create(@Valid @RequestBody CategoryModel category){
        return new ResponseEntity<>(categoryService.createItem(category), HttpStatus.CREATED);
    }

    @GetMapping("category/{id}")
    public ResponseEntity <CategoryModel> getCategoryById(@PathVariable Long id){
        return ResponseEntity.ok(categoryService.getItemByid(id));
    }

    @PutMapping("category/{id}")
    public ResponseEntity <CategoryModel> updateCategory(@Valid @RequestBody CategoryModel category, @PathVariable Long id){
        return ResponseEntity.ok(categoryService.updateItem(category,id));
    }

    @DeleteMapping("category/{id}")
    public ResponseEntity <String> deleteItemById(@PathVariable Long id){
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>("se elimino la categoria",HttpStatus.NO_CONTENT);
    }

    @GetMapping("category")
    public ResponseEntity<List<CategoryModel>> getAll(){
        return ResponseEntity.ok(categoryService.findAllCategory());
    }

}