package org.example.gestor_de_ventas_api.exception;


import org.example.gestor_de_ventas_api.exception.custom.ResourceAlreadyExistsException;
import org.example.gestor_de_ventas_api.exception.custom.ResourcePersistenceException;
import org.example.gestor_de_ventas_api.exception.custom.ResourcesNotFoundException;
import org.example.gestor_de_ventas_api.exception.dto.ErrorResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourcesNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleReourcesNotFoundException(ResourcesNotFoundException ex){
        ErrorResponse error = ErrorResponse.builder()
                .code("NOT_FOUND")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<ErrorResponse>(error, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex) {
        ErrorResponse error = ErrorResponse.builder()
                .code("VALIDATION_ERROR")
                .message("Request validation failed")
                .build();

        return new ResponseEntity<ErrorResponse>(error, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ResourcePersistenceException.class)
    public ResponseEntity<ErrorResponse> handleResourcePersistenceException(ResourcePersistenceException ex){
        ErrorResponse error = ErrorResponse.builder()
                .code(ex.getMessage())
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(ResourceAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleResourceAlreadyExistsException(ResourceAlreadyExistsException ex){
        ErrorResponse error = ErrorResponse.builder()
                .code("RESOURCE_ALREADY_EXISTS")
                .message(ex.getMessage())
                .build();
        return new ResponseEntity<>(error, HttpStatus.CONFLICT);
    }

}
