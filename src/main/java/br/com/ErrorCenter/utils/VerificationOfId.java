package br.com.ErrorCenter.utils;

import br.com.ErrorCenter.exceptions.event.InvalidIdException;
import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class VerificationOfId {

    public static void isValidId(Long id) {
        if (id < 0) {
            throw new ResponseStatusException(
                    HttpStatus.BAD_REQUEST,
                    new InvalidIdException().getMessage()
            );
        }
    }

}
