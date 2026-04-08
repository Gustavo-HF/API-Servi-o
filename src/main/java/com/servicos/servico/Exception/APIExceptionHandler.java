package com.servicos.servico.Exception;

import java.util.Map;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class APIExceptionHandler {

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> handleNotFound(NotFoundException ex) {
        return ResponseEntity.status(404)
                .body(Map.of("error", ex.getErrorCode(), "message", ex.getMessage()));
    }

    @ExceptionHandler(RequestException.class)
    public ResponseEntity<?> handle(RequestException ex) {
        return ResponseEntity.badRequest()
                .body(Map.of("error", ex.getErrorCode(), "message", ex.getMessage()));
    }

    @ExceptionHandler(org.springframework.web.bind.MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleValidationErrors(org.springframework.web.bind.MethodArgumentNotValidException ex) {
        var erros = ex.getFieldErrors().stream()
                .map(error -> Map.of(
                "campo", error.getField(),
                "mensagem", error.getDefaultMessage()
        ))
                .toList();

        return ResponseEntity.badRequest().body(erros);
    }
}
