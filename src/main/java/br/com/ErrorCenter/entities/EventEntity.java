package br.com.ErrorCenter.entities;

import br.com.ErrorCenter.enums.LevelEnum;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.time.OffsetDateTime;

@Entity
@Table(name = "Event")
@EntityListeners(value = AuditingEntityListener.class)
@AllArgsConstructor
@NoArgsConstructor
@Data
@EqualsAndHashCode(of = "id")
public class EventEntity {

    public static final Integer DEFAULT_QUANTITY = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private EventEntity(Builder builder) {
        this.description = builder.description;
        this.log = builder.log;
        this.level = builder.level;
        this.application = builder.application;
        this.quantity = builder.quantity;
    }

    @NotNull
    @NotBlank
    @Size(min = 10, max = 255)
    private String description;

    @NotNull
    @NotBlank
    @Size(min = 10, max = 255)
    private String log;

    private OffsetDateTime createdAt;

    @NotNull
    private LevelEnum level;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "application_id", nullable = false)
    private ApplicationEntity application;

    @Positive
    @Column(columnDefinition = "int default 1")
    private Integer quantity = DEFAULT_QUANTITY;

    public static class Builder {

        private String description;
        private String log;
        private LevelEnum level;
        private ApplicationEntity application;
        private Integer quantity = DEFAULT_QUANTITY;

        public Builder withDescription(String description) {
            this.description = description;
            return this;
        }

        public Builder withLog(String log) {
            this.log = log;
            return this;
        }

        public Builder withLevel(LevelEnum level) {
            this.level = level;
            return this;
        }

        public Builder withOrigin(ApplicationEntity application) {
            this.application = application;
            return this;
        }

        public Builder withQuantity(int quantity) {
            this.quantity = quantity;
            return this;
        }

        public EventEntity build() {
            return new EventEntity(this);
        }

    }

}
