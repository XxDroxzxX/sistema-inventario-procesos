package com.sistema.inventario.exception;

public class NotFoundException  extends  RuntimeException{
    public NotFoundException(String message){
        super(message);
    }
}