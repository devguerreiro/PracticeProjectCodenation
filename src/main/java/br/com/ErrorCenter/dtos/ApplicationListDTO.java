package br.com.ErrorCenter.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationListDTO {

    private Long id;
    private String name;
    private LocalDateTime createdAt;

}
