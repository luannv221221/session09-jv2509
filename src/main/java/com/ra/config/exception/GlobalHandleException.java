package com.ra.config.exception;

import com.ra.dto.ResponseWrapper;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalHandleException {
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception){
        Map<String,String> errors = new HashMap<>();

        exception.getFieldErrors().forEach(fieldError -> {
            errors.put(fieldError.getField(),fieldError.getDefaultMessage());
        });

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(ResponseWrapper.error(errors,"Du lieu khong hop le",HttpStatus.BAD_REQUEST.value()));
    }

    @ExceptionHandler(HttpConflict.class)
    public ResponseEntity<?> handleExceptionHttpConflict(HttpConflict exception){

        return ResponseEntity.status(HttpStatus.CONFLICT).body(ResponseWrapper.error(exception.getMessage(),"Du lieu khong hop le",HttpStatus.CONFLICT.value()));
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> handleEntityNotFoundException(EntityNotFoundException exception){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(ResponseWrapper.error(exception.getMessage(),"Khong tim thay ban ghi",HttpStatus.NOT_FOUND.value()));
    }
}
