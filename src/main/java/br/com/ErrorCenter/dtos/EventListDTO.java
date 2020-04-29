package br.com.ErrorCenter.dtos;

import br.com.ErrorCenter.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventListDTO {

    private Long id;
    private String description;
    private LevelEnum level;
    private Long origin_id;
    private OffsetDateTime created_at;
    private int quantity;

}
