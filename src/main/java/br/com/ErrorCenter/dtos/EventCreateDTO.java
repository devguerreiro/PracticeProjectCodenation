package br.com.ErrorCenter.dtos;

import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCreateDTO {

    @NotNull
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 255)
    private String log;

    @NotNull
    private LevelEnum level;

    @Positive
    private Integer quantity = EventEntity.DEFAULT_QUANTITY;

}
