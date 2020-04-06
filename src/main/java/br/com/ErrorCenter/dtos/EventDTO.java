package br.com.ErrorCenter.dtos;

import br.com.ErrorCenter.entities.ApplicationEntity;
import br.com.ErrorCenter.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class EventDTO {

    private String description;
    private LevelEnum level;
    private ApplicationEntity origin;
    private LocalDateTime createdAt;
    private int quantity;

}
