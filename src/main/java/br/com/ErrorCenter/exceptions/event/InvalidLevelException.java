package br.com.ErrorCenter.exceptions.event;

public class InvalidLevelException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidLevelException() {
        super("Invalid level. This level not exist.");
    }

}
