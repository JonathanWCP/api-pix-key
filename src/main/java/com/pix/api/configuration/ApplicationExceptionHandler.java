package com.pix.api.configuration;

import br.com.fluentvalidator.exception.ValidationException;
import com.pix.domain.exceptions.*;
import com.pix.domain.models.ExceptionResponseJSONMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.ArrayList;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PixKeyNotFoundException.class)
    public ResponseEntity<ExceptionResponseJSONMessage> handlePixKeyNotFoundException(Exception ex) {
        var response = new ExceptionResponseJSONMessage(404, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PixKeyAlreadyDisableException.class)
    public ResponseEntity<ExceptionResponseJSONMessage> handlePixKeyAlreadyDisableException(Exception ex) {
        var response = new ExceptionResponseJSONMessage(422, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PixKeyAlreadyExistsException.class)
    public ResponseEntity<ExceptionResponseJSONMessage> handlePixKeyAlreadyExistsException(Exception ex) {
        var response = new ExceptionResponseJSONMessage(403, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(CreatePixKeyException.class)
    public ResponseEntity<ExceptionResponseJSONMessage> handleCreatePixKeyExceptionException(ValidationException ex) {
        var exceptionError = new ArrayList<>(ex.getValidationResult().getErrors()).get(0);
        var response = new ExceptionResponseJSONMessage(422, exceptionError.getMessage(), exceptionError.getField());

        return new ResponseEntity<>(response, HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PixKeyLimitReachedException.class)
    public ResponseEntity<ExceptionResponseJSONMessage> handlePixKeyLimitReachedException(Exception ex) {
        var response = new ExceptionResponseJSONMessage(422, ex.getMessage());

        return new ResponseEntity<>(response, HttpStatus.FORBIDDEN);
    }
}
