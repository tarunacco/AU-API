package com.accolite.au.exceptionHandlers;

public class UserAuthenticationException extends Throwable{
    private String message;
    public UserAuthenticationException(String message){
        this.message = message;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
