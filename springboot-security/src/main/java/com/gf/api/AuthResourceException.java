package com.gf.api;

public class AuthResourceException extends RuntimeException {

    public AuthResourceException(String msg, Throwable t) {
        super(msg, t);
    }


    public AuthResourceException(String msg) {
        super(msg);
    }


}
