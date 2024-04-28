package com.perdidoseachados.perdidoseachados.Controllers.Exeptions;


import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

public class ExceptionResponse {
    
    private final HttpStatus status;
    private final String message;
    private final String details;
    private final LocalDateTime timestamp;
    
    public ExceptionResponse(HttpStatus status, String message, String details) {
        this.status = status;
        this.message = message;
        this.details = details;
        this.timestamp = LocalDateTime.now();
    }
    
    public HttpStatus getStatus() {
        return status;
    }
    
    public String getMessage() {
        return message;
    }
    
    public String getDetails() {
        return details;
    }
    
    public LocalDateTime getTimestamp() {
        return timestamp;
    }
}
