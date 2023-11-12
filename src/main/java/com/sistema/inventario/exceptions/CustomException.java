package com.sistema.inventario.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class CustomException extends Exception {

        public CustomException(String message) {
            super(message);
        }
    }


