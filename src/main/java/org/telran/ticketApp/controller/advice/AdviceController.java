package org.telran.ticketApp.controller.advice;

import org.hibernate.exception.ConstraintViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.stream.Collectors;

@RestControllerAdvice
public class AdviceController {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(Exception.class)
    public String handlerException(Exception exception) {
        exception.printStackTrace();
        return "Sorry, an error has occurred : " + exception.getMessage();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        exception.printStackTrace();
        Map<String, ?> errors = exception.getBindingResult().getFieldErrors().stream()
                .collect(Collectors.toMap( FieldError::getField, fieldError -> fieldError.getDefaultMessage() != null ? fieldError.getDefaultMessage() : "Invalid data"));
        return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
    }
}
