package com.backend.ecommerce.shared.exceptions;

import com.backend.ecommerce.shared.response.GlobalResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.nio.file.AccessDeniedException;
import java.util.NoSuchElementException;
import java.util.Objects;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GlobalResponse<CustomErrorVm>> handleAllArgumentException(MethodArgumentNotValidException exception) {
        CustomErrorVm errorVm = new CustomErrorVm(
                "Validation error: " + Objects.requireNonNull(exception.getBindingResult().getFieldError()).getDefaultMessage(),
                HttpStatus.BAD_REQUEST.value()
        );
        GlobalResponse<CustomErrorVm> response = new GlobalResponse<>(null, errorVm);
        return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(NoSuchElementException.class)
    public ResponseEntity<GlobalResponse<CustomErrorVm>> handleNotFoundException(NoSuchElementException exception) {
        CustomErrorVm errorVm = new CustomErrorVm(
                "Resource not found: " + exception.getMessage(),
                HttpStatus.NOT_FOUND.value()
        );
        GlobalResponse<CustomErrorVm> response = new GlobalResponse<>(null, errorVm);
        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<GlobalResponse<CustomErrorVm>> handleMethodNotSupportedException(HttpRequestMethodNotSupportedException exception) {
        CustomErrorVm errorVm = new CustomErrorVm(
                "HTTP method not supported: " + exception.getMethod(),
                HttpStatus.METHOD_NOT_ALLOWED.value()
        );
        GlobalResponse<CustomErrorVm> response = new GlobalResponse<>(null, errorVm);
        return new ResponseEntity<>(response, HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(AccessDeniedException.class)
    public ResponseEntity<GlobalResponse<CustomErrorVm>> handleAccessDeniedException(AccessDeniedException exception) {
        CustomErrorVm errorVm = new CustomErrorVm(
                "Access denied: " + exception.getMessage(),
                HttpStatus.FORBIDDEN.value()
        );
        GlobalResponse<CustomErrorVm> response = new GlobalResponse<>(null, errorVm);
        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<GlobalResponse<CustomErrorVm>> handleGenericException(Exception exception) {
        CustomErrorVm errorVm = new CustomErrorVm(
                "An unexpected error occurred: " + exception.getMessage(),
                HttpStatus.INTERNAL_SERVER_ERROR.value()
        );
        GlobalResponse<CustomErrorVm> response = new GlobalResponse<>(null, errorVm);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<GlobalResponse<CustomErrorVm>> handleCustomException(CustomException exception) {
        CustomErrorVm errorVm = new CustomErrorVm(
                exception.getMessage(),
                exception.getStatusCode()
        );
        GlobalResponse<CustomErrorVm> response = new GlobalResponse<>(null, errorVm);
        return new ResponseEntity<>(response, HttpStatus.resolve(exception.getStatusCode()) != null ? HttpStatus.valueOf(exception.getStatusCode()) : HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
