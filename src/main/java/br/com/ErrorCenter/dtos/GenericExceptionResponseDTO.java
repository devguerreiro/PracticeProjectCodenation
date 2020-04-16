package br.com.ErrorCenter.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.ZonedDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class GenericExceptionResponseDTO {

    private ZonedDateTime timestamp;
    private int status;
    private String error;
    private String message;
    private String path;

    private GenericExceptionResponseDTO(Builder builder) {
        this.timestamp = builder.timestamp;
        this.status = builder.status;
        this.error = builder.error;
        this.message = builder.message;
        this.path = builder.path;
    }

    public static class Builder {

        private ZonedDateTime timestamp;
        private int status;
        private String error;
        private String message;
        private String path;

        public Builder withTimestamp(ZonedDateTime timestamp) {
            this.timestamp = timestamp;
            return this;
        }

        public Builder withStatus(int status) {
            this.status = status;
            return this;
        }

        public Builder withError(String error) {
            this.error = error;
            return this;
        }

        public Builder withMessage(String message) {
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
