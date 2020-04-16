package br.com.ErrorCenter.converters;

import br.com.ErrorCenter.enums.LevelEnum;
import br.com.ErrorCenter.exceptions.event.InvalidLevelException;
import org.springframework.core.convert.converter.Converter;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class LevelParamConverter implements Converter<String, LevelEnum> {

    @Override
    public LevelEnum convert(String source) {
        try {
            return LevelEnum.valueOf(source.toUpperCase());
        } catch (RuntimeException e) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    new InvalidLevelException().getMessage()
            );
        }
    }

}
