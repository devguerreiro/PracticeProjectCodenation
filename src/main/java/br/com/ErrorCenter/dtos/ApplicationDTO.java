package br.com.ErrorCenter.dtos;

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
public class ApplicationDTO {

    private Long id;
    private String name;
    @JsonFormat(pattern = "yyyy/dd/MM HH:mm:ss")
    private LocalDateTime createdAt;

}
