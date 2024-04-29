package com.perdidoseachados.perdidoseachados.Servicies.exeptions;

public class TokenVerificationException extends RuntimeException {

    public TokenVerificationException(String message) {
        super(message);
    }

    public TokenVerificationException(String message, Throwable cause) {
        super(message, cause);
    }
}