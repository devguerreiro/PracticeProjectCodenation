package br.com.ErrorCenter.exceptions.event;

public class InvalidDateException extends RuntimeException {

    public InvalidDateException() {
        super("Invalid datetime format. Format valid: yyyy-MM-ddTHH:mm:ss");
    }

}
