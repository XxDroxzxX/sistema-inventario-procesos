package com.sistema.inventario.exceptions;

public class MethodArgumentNotValidException extends RuntimeException{
    public MethodArgumentNotValidException (String message){
        super(message);
    }
}
