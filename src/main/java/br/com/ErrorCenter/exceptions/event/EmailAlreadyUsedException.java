package br.com.ErrorCenter.exceptions.event;

public class EmailAlreadyUsedException extends RuntimeException {

    private static final long serialVersionUID = 1L;

    public EmailAlreadyUsedException() {
        super("This email is already used");
    }
}
