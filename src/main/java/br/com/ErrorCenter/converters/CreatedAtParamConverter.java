package br.com.ErrorCenter.converters;

import org.springframework.core.convert.converter.Converter;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

public class CreatedAtParamConverter implements Converter<LocalDateTime, LocalDateTime> {

    @Override
    public LocalDateTime convert(LocalDateTime source) {
        return source.truncatedTo(ChronoUnit.SECONDS);
    }

}
