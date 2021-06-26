package com.example.system_user_app.exceptions;

public class InvalidIdException extends Exception { 
    public InvalidIdException(String errorMessage) {
        super(errorMessage);
    }
}