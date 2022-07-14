package br.com.saulo.cleancommerce.core.domain.exceptions;

public class UserNotFoundException extends Exception {
    public UserNotFoundException(String s) {
        super(s);
    }
}
