package br.com.ErrorCenter.entities;

import br.com.ErrorCenter.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.time.LocalDateTime;

@Entity
@EntityListeners(value = AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    public EventEntity(String description, String log, LevelEnum level, ApplicationEntity application) {
        this.description = description;
        this.log = log;
        this.level = level;
        this.application = application;
    }

    @NotNull
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 255)
    private String log;

    @DateTimeFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    @CreatedDate
    private LocalDateTime createdAt;

    @NotNull
    private LevelEnum level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    @Positive
    @Column(columnDefinition = "int default 1")
    private int quantity = 1;

}
