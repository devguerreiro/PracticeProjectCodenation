package br.com.ErrorCenter.exceptions.event;

public class InvalidDateException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidDateException() {
        super("Invalid datetime format. Format valid: yyyy-MM-ddTHH:mm:ss");
    }

}
