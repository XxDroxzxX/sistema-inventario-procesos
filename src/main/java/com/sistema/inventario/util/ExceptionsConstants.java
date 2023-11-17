package com.sistema.inventario.util;

import lombok.Getter;

@Getter
public enum ExceptionsConstants {

    ITEM_VACIO("Item vacio"),
    USER_VACIO( "User vacio"),
    USERS_VACIOS("Users vacios"),
    USER_EXISTE("User exists"),
    ITEM_NULL("Item  null"),
    USER_NULL("User  null"),
    ADDRESS_NULO("Address is nulo"),

    CATEGORY_NOT_FOUND("Category not found"),

    CREDENTIAL_NOVALID("Invalid username or password"),

    CATEGORY_IS_NULO("Category is null"),
    DOCUMENT_EXISTS("Document already exists"),
    ADDRESS_NOT_FOUND("Address not found");
    private final String message;

    private ExceptionsConstants(String message){
        this.message =  message;
    }

}
