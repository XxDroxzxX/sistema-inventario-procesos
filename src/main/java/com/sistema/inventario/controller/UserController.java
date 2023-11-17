package com.sistema.inventario.controller;

import com.sistema.inventario.model.UserModel;
import com.sistema.inventario.service.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("users")
    public ResponseEntity<UserModel> create(@Valid @RequestBody UserModel user){

        return new ResponseEntity<>(userService.createUser(user), HttpStatus.CREATED);
    }

    @GetMapping("users/{id}")
    public ResponseEntity<UserModel> getUserById(@PathVariable Long id){

        return ResponseEntity.ok(userService.getUserById(id));
    }

    @PutMapping("users/{id}")
    public ResponseEntity<UserModel> update(@Valid @RequestBody UserModel user, @PathVariable Long id){
        return ResponseEntity.ok(userService.updateUser(id,user));
    }

    @DeleteMapping("users/{id}")
    public ResponseEntity<String> delete(@PathVariable Long id){
        userService.deleteUser(id);
        return new ResponseEntity<String>("Se elimino el usuario", HttpStatus.NO_CONTENT);
    }

    @GetMapping("users")
    public ResponseEntity<List<UserModel>> getAll(){
        return ResponseEntity.ok(userService.findAllUsers());
    }


}
