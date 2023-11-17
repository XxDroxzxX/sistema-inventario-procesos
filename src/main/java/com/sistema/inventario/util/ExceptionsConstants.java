package com.sistema.inventario.util;

import lombok.Getter;

@Getter
public enum ExceptionsConstants {

    ITEM_NOT_FOUND("Item not found"),
    USER_NOT_FOUND( "User not found"),
    USERS_NOT_FOUND("Users not found"),
    USER_ALREADY_EXISTS("User already exists"),
    ITEM_IS_NULL("Item is null"),
    USER_IS_NULL("User is null"),
    ADDRESS_IS_NULL("Address is null"),
    ADDRESS_NOT_FOUND("Address not found"),
    CREDENTIAL_INVALID("Invalid username or password"),
    CATEGORY_NOT_FOUND("Category not found"),
    CATEGORY_IS_NULL("Category is null"),
    DOCUMENT_ALREADY_EXISTS("Document already exists");
    private final String message;

    private ExceptionsConstants(String message){
        this.message =  message;
    }

}
