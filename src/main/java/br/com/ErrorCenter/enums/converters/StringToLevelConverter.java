package br.com.ErrorCenter.enums.converters;

import br.com.ErrorCenter.enums.LevelEnum;
import org.springframework.core.convert.converter.Converter;

public class StringToLevelConverter implements Converter<String, LevelEnum> {

    @Override
    public LevelEnum convert(String source) {
        return LevelEnum.valueOf(source.toUpperCase());
    }

}
