package br.com.ErrorCenter.dtos;

import br.com.ErrorCenter.enums.LevelEnum;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventListDTO {

    private Long id;
    private String description;
    private LevelEnum level;
    private Long origin_id;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private LocalDateTime created_at;
    private int quantity;

}
