package com.sistema.inventario.exception;

public class AlreadyExistsException extends  RuntimeException {
    public AlreadyExistsException(String message){
        //si existe el item manda la exception
        super(message);
    }
}