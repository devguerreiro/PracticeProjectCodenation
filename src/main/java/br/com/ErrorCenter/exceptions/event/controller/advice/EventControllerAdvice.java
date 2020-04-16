package br.com.ErrorCenter.exceptions.event.controller.advice;

import br.com.ErrorCenter.dtos.GenericExceptionResponseDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;

import java.time.ZonedDateTime;

@RestControllerAdvice
public class EventControllerAdvice {

    @ExceptionHandler(NumberFormatException.class)
    @ResponseBody
    public ResponseEntity<GenericExceptionResponseDTO> handleNumberFormatException(ServletWebRequest request) {
        return new ResponseEntity<>(
                new GenericExceptionResponseDTO.Builder()
                .withTimestamp(ZonedDateTime.now())
                .withStatus(HttpStatus.BAD_REQUEST.value())
                .withError("Invalid ID format")
                .withMessage("ID must be a number")
                .withPath(request.getRequest().getRequestURI())
                .build(),
                HttpStatus.BAD_REQUEST
        );
    }

}
