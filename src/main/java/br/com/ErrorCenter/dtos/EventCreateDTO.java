package br.com.ErrorCenter.dtos;

import br.com.ErrorCenter.entities.EventEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventCreateDTO {

    private String description;
    private String log;
    private LevelEnum level;
    private Long origin_id;
    private Integer quantity = EventEntity.DEFAULT_QUANTITY;

}
