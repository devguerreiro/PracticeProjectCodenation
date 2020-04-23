package br.com.ErrorCenter.exceptions.event;

public class EmailAlreadyUsedException extends RuntimeException {

    public EmailAlreadyUsedException() {
        super("This email is already used");
    }
}
