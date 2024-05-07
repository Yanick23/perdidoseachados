package com.perdidoseachados.perdidoseachados.DTOs;

public class GenerateResponse {
    private String message;

    public GenerateResponse(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
