package com.sistema.inventario.controller;

import com.sistema.inventario.model.ItemModel;
import com.sistema.inventario.service.ItemService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping("items")
    public ResponseEntity <ItemModel> create(@Valid @RequestBody ItemModel item){
        return new ResponseEntity<>(itemService.createItem(item), HttpStatus.CREATED);
    }

    @GetMapping("items/{id}")
    public ResponseEntity <ItemModel> getItemById(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getItemById(id));
    }

    @PutMapping ("items/{id}")
    public ResponseEntity <ItemModel> updateItem(@Valid @RequestBody ItemModel item, @PathVariable Long id){
        return ResponseEntity.ok(itemService.updateItem(item , id));
    }

    @DeleteMapping("items/{id}")
    public ResponseEntity <String> deleteItemById(@PathVariable Long id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Se elimino el usuario",HttpStatus.NO_CONTENT) ;
    }

    @GetMapping("items")
    public ResponseEntity <List<ItemModel>> getAll(){
        return ResponseEntity.ok(itemService.findAllItems());
    }

}
