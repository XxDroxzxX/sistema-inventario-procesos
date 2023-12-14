package com.sistema.inventario.exception;

public class AlreadyExistsException extends  RuntimeException {
    public AlreadyExistsException(String message){
        super(message);
    }
}