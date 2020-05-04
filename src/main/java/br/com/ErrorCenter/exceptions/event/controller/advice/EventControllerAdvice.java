package br.com.ErrorCenter.exceptions.event.controller.advice;

import br.com.ErrorCenter.dtos.GenericExceptionResponseDTO;
import br.com.ErrorCenter.exceptions.event.EmailAlreadyUsedException;
import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import javax.validation.ConstraintViolationException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@RestControllerAdvice
public class EventControllerAdvice {

    private final HttpStatus BAD_REQUEST = HttpStatus.BAD_REQUEST;

    @ExceptionHandler(NumberFormatException.class)
    public ResponseEntity<GenericExceptionResponseDTO> handleNumberFormatException(NumberFormatException exception, ServletWebRequest request) {
        List<String> errors = new ArrayList<>();
        String message = "input must be a number";
        errors.add(message);

        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withStatus(BAD_REQUEST.value())
                .withError(exception.getLocalizedMessage())
                .withMessage(errors)
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

    @ExceptionHandler(InvalidFormatException.class)
    public ResponseEntity<GenericExceptionResponseDTO> handleInvalidFormatException(InvalidFormatException exception, ServletWebRequest request) {
        List<String> errors = new ArrayList<>();
        String message = exception.getValue().toString() +
                        " is not a valid value.";
        errors.add(message);

        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withStatus(BAD_REQUEST.value())
                .withError(exception.getOriginalMessage())
                .withMessage(errors)
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

    @ExceptionHandler(ConstraintViolationException.class)
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
                .withStatus(BAD_REQUEST.value())
                .withError("Validation error")
                .withMessage(errorPerField)
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<GenericExceptionResponseDTO> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, ServletWebRequest request) {
        List<HashMap<String, String>> errorPerField = new ArrayList<>();

        exception.getBindingResult().getFieldErrors().forEach(fieldError -> {
            HashMap<String, String> error = new HashMap<>();
            error.put("field", fieldError.getField());
            error.put("message", fieldError.getDefaultMessage());
            errorPerField.add(error);
        });

        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                        .withStatus(BAD_REQUEST.value())
                        .withError("Validation error")
                        .withMessage(errorPerField)
                        .withPath(request.getRequest().getRequestURI())
                        .build()
        );
    }

    @ExceptionHandler(EmailAlreadyUsedException.class)
    public ResponseEntity<GenericExceptionResponseDTO> handleEmailAlreadyUsedException(EmailAlreadyUsedException exception, ServletWebRequest request) {
        List<String> errors = new ArrayList<>();
        errors.add(exception.getMessage());

        return ResponseEntity.badRequest().body(
                new GenericExceptionResponseDTO.Builder()
                .withStatus(BAD_REQUEST.value())
                .withError("Validation error")
                .withMessage(errors)
                .withPath(request.getRequest().getRequestURI())
                .build()
        );
    }

}
