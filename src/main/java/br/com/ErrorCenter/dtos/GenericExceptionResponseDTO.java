package br.com.ErrorCenter.dtos;

import lombok.Getter;
import lombok.Setter;

import java.time.OffsetDateTime;
import java.util.HashMap;
import java.util.List;

@Getter
@Setter
public class GenericExceptionResponseDTO {

    private OffsetDateTime timestamp;
    private int status;
    private String error;
    private List<?> message;
    private String path;

    private GenericExceptionResponseDTO(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
        this.path = builder.path;
    }

    public static class Builder {

        private final OffsetDateTime timestamp = OffsetDateTime.now();
        private int status;
        private String error;
        private List<?> message;
        private String path;

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public Builder withMessage(List<?> message) {
            this.message = message;
            return this;
        }

        public Builder withPath(String path) {
            this.path = path;
            return this;
        }

        public GenericExceptionResponseDTO build() {
            return new GenericExceptionResponseDTO(this);
        }
    }

}
