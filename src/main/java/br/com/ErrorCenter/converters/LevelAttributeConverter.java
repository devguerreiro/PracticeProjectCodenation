package br.com.ErrorCenter.converters;

import br.com.ErrorCenter.enums.LevelEnum;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;
import java.util.stream.Stream;

@Converter(autoApply = true)
public class LevelAttributeConverter implements AttributeConverter<LevelEnum, String> {

    @Override
    public String convertToDatabaseColumn(LevelEnum attribute) {
        return attribute == null ? null : attribute.name().toLowerCase();
    }

    @Override
    public LevelEnum convertToEntityAttribute(String dbData) {
        return dbData == null ? null : Stream.of(LevelEnum.values())
                .filter(level -> level.name().toLowerCase().equals(dbData))
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

}
