package com.example.dbserver.global.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ErrorResponse> handleIllegalArgumentException(IllegalArgumentException e) {
        String message = e.getMessage();

        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (message != null && message.contains("찾을 수 없습니다")) {
            status = HttpStatus.NOT_FOUND;
        }

        ErrorResponse response = new ErrorResponse(status.value(), message);

        return ResponseEntity.status(status).body(response);
    }
}