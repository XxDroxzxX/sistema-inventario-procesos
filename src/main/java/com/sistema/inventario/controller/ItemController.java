package com.sistema.inventario.controller;
import com.sistema.inventario.model.ItemModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import com.sistema.inventario.service.ItemService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
public class ItemController {
    @Autowired
    private ItemService itemService;

    @PostMapping("items")
    public ResponseEntity <ItemModel> createItem (@RequestBody ItemModel item){
        return new ResponseEntity(itemService.createItemModel(item), HttpStatus.CREATED);
    }

    @GetMapping("items/{id}")
    public ResponseEntity <ItemModel> getItemById(@PathVariable Long id){
        return ResponseEntity.ok(itemService.getItemModelById(id));
    }

    @PutMapping("items/{id}")
    public ResponseEntity <ItemModel> updateItem(@RequestBody ItemModel item, @PathVariable Long id){
        return ResponseEntity.ok(itemService.updateItemModel(item ,id));
    }

    @DeleteMapping("items/{id}")
    public ResponseEntity <String> deleteItemById(@PathVariable Long id){
        itemService.deleteItemById(id);
        return new ResponseEntity<>("Se elimino el articulo" , HttpStatus.NO_CONTENT);
    }

    @GetMapping("items")
    public ResponseEntity <List<ItemModel>> getAll(){
        return ResponseEntity.ok(itemService.findAllItems());
    }

}
