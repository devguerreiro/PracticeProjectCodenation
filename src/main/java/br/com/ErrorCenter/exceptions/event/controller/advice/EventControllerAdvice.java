package br.com.ErrorCenter.exceptions.event.controller.advice;

import br.com.ErrorCenter.dtos.GenericExceptionResponseDTO;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.dao.DataIntegrityViolationException;

@RestControllerAdvice
public class EventControllerAdvice {

    private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<GenericExceptionResponseDTO> handleNumberFormatException(NumberFormatException exception, ServletWebRequest request) {
        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(BAD_REQUEST.value())
                .withError(exception.getLocalizedMessage())
                .withMessage("input must be a number")
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

    @ExceptionHandler(InvalidFormatException.class)
    @ResponseBody
    public ResponseEntity<GenericExceptionResponseDTO> handleInvalidFormatException(InvalidFormatException exception, ServletWebRequest request) {
        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(BAD_REQUEST.value())
                .withError(exception.getOriginalMessage())
                .withMessage(
                        exception.getValue().toString() +
                        " is not a valid value."
                )
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseBody
    public ResponseEntity<GenericExceptionResponseDTO> handleConstraintViolationException(ConstraintViolationException exception, ServletWebRequest request) {
        List<HashMap<String, String>> errorPerField = new ArrayList<>();

        exception.getConstraintViolations().forEach(constraintViolation -> {
            HashMap<String, String> error = new HashMap<>();
            error.put("field", constraintViolation.getPropertyPath().toString());
            error.put("message", constraintViolation.getMessage());
            errorPerField.add(error);
        });

        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(BAD_REQUEST.value())
                .withError("Validation error")
                .withMessage(errorPerField.toString())
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseBody
    public ResponseEntity<GenericExceptionResponseDTO> handleDataIntegrityViolationException(DataIntegrityViolationException exception, ServletWebRequest request) {
        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(BAD_REQUEST.value())
                .withError(exception.getMessage())
                .withMessage("This e-mail already used")
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

}
