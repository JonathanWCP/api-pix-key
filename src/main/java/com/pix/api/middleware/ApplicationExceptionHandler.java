package com.pix.api.middleware;

import com.pix.domain.exceptions.*;
import com.pix.domain.models.ExceptionResponseJSONMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ApplicationExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(PixKeyNotFoundException.class)
    public ResponseEntity handlePixKeyNotFoundException(Exception ex) {
        return new ResponseEntity(buildResponseMessage(404, ex.getMessage()), HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(PixKeyAlreadyDisableException.class)
    public ResponseEntity<ExceptionResponseJSONMessage> handlePixKeyAlreadyDisableException(Exception ex) {
        return new ResponseEntity(buildResponseMessage(422, ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PixKeyAlreadyExistsException.class)
    public ResponseEntity handlePixKeyAlreadyExistsException(Exception ex) {
        return new ResponseEntity(buildResponseMessage(403, ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    @ExceptionHandler(EmptyFieldException.class)
    public ResponseEntity handleValidationException(Exception ex) {
        return new ResponseEntity(buildResponseMessage(422, ex.getMessage()), HttpStatus.UNPROCESSABLE_ENTITY);
    }

    @ExceptionHandler(PixKeyLimitReachedException.class)
    public ResponseEntity handlePixKeyLimitReachedException(Exception ex) {
        return new ResponseEntity(buildResponseMessage(422, ex.getMessage()), HttpStatus.FORBIDDEN);
    }

    private ExceptionResponseJSONMessage buildResponseMessage(int statusCode, String message) {
        ExceptionResponseJSONMessage response = new ExceptionResponseJSONMessage();
        response.setStatusCode(statusCode);
        response.setMessage(message);
        return response;
    }
}
