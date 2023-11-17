package com.sistema.inventario.controller;


import com.sistema.inventario.model.AddressModel;
import com.sistema.inventario.service.AddressService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class AddressController {

    @Autowired
    private AddressService addressService;

    @PostMapping("address/{idUser}")
    public ResponseEntity create(@Valid @RequestBody AddressModel address, @PathVariable Long idUser){
        return new ResponseEntity(addressService.createAddress(address,idUser), HttpStatus.CREATED);
    }
    @PutMapping("address/{id}")
    public ResponseEntity disabled(@Valid @PathVariable Long id){
        return ResponseEntity.ok(addressService.disabledAddress(id));
    }

    @GetMapping("address/{id}")
    public ResponseEntity<AddressModel> getById(@PathVariable Long id){
        return  ResponseEntity.ok(addressService.getAddressById(id));
    }

    @GetMapping("address")
    public ResponseEntity<List<AddressModel>> getAll(){
        return ResponseEntity.ok(addressService.getAllAddress());
    }

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Map<String, String> handleValidationExceptions(
            MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();
        ex.getBindingResult().getAllErrors().forEach((error) -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }
}
