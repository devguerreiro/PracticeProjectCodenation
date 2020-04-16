package br.com.ErrorCenter.converters;

import br.com.ErrorCenter.exceptions.event.InvalidDateException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreatedAtParamConverter implements Converter<String, LocalDateTime> {

    @Override
    public LocalDateTime convert(String source) {
        try {
            return LocalDateTime.parse(source).truncatedTo(ChronoUnit.SECONDS);
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    new InvalidDateException().getMessage()
            );
        }
    }

}
