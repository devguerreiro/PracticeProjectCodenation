package br.com.ErrorCenter.enums.converters;

import br.com.ErrorCenter.enums.LevelEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LevelConverter implements AttributeConverter<LevelEnum, String> {

    @Override
    public String convertToDatabaseColumn(LevelEnum attribute) {
        if (attribute == null) {
            return null;
        }

        return attribute.getLevel();
    }

    @Override
    public LevelEnum convertToEntityAttribute(String dbData) {
        if (dbData == null) {
            return null;
        }

        return Stream.of(LevelEnum.values())
                .filter(level -> level.getLevel().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
