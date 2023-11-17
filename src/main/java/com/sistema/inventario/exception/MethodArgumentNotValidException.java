package com.sistema.inventario.exception;

public class MethodArgumentNotValidException extends RuntimeException{
    //exception para argumentos no validpos
    public MethodArgumentNotValidException(String message){
        super(message);
    }
}
