package com.example.kingsneaker.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
@Getter
@Setter

public class AppException extends RuntimeException {
    private final HttpStatus code;
    public AppException(String message, HttpStatus code) {
        super(message);
        this.code= code;
    }
}
