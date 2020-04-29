package br.com.ErrorCenter.exceptions.event;

public class InvalidIdException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public InvalidIdException() {
        super("ID must be a number and greater or equal than 0");
    }

}
