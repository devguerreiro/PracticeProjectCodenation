package br.com.ErrorCenter.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.OffsetDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ApplicationDTO {

    private Long id;
    private String name;
    private OffsetDateTime created_at;

}
