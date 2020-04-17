package br.com.ErrorCenter.exceptions.event;

public class InvalidIdException extends RuntimeException {

    public InvalidIdException() {
        super("ID must be a number and greater or equal than 0");
    }

}
