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
public class EventDetailDTO {

    private Long id;
    private String description;
    private LevelEnum level;
    private String log;
    private ApplicationDTO origin;
    @JsonFormat(pattern = "yyyy/dd/MM HH:mm:ss")
    private LocalDateTime createdAt;
    private int quantity;

}
