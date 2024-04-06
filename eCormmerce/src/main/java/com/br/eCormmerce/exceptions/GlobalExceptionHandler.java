package com.br.eCormmerce.exceptions;


import java.util.HashMap;
import java.util.Map;

import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.client.HttpClientErrorException.Unauthorized;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.resource.NoResourceFoundException;


@ControllerAdvice
public class GlobalExceptionHandler {
    
    @ExceptionHandler(value = DataIntegrityViolationException.class)
    public ResponseEntity<Object> handleDataIntegrityViolationException(DataIntegrityViolationException e) {
        return new ResponseEntity<>("O CPF Informado já esta em uso", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = Unauthorized.class)
    public ResponseEntity<Object> Unauthorized(Unauthorized e) {
        return new ResponseEntity<>("Você não tem permissão de acessar essa rota", HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = HttpRequestMethodNotSupportedException.class)
    public ResponseEntity<Object> httpRequestMethodNotSupportedException(HttpRequestMethodNotSupportedException e){
        return new ResponseEntity<>("Esse método não é suportado", HttpStatus.METHOD_NOT_ALLOWED);
    }

    @ExceptionHandler(value = NoResourceFoundException.class)
    public ResponseEntity<Object> noResourceFoundException(NoResourceFoundException e){
        return new ResponseEntity<>("Rota não encontrada", HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
        public ResponseEntity<Object> handleValidationExceptions(
                MethodArgumentNotValidException ex, WebRequest request) {
            Map<String, String> errors = new HashMap<>();
            ex.getBindingResult().getAllErrors().forEach((error) -> {
                String fieldName = ((org.springframework.validation.FieldError) error).getField();
                String errorMessage = error.getDefaultMessage();
                errors.put(fieldName, errorMessage);
            });
            return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
        }
}
