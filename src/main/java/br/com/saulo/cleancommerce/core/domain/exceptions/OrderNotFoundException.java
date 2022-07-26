package br.com.saulo.cleancommerce.core.domain.exceptions;

public class OrderNotFoundException extends Exception{
    public OrderNotFoundException(String s) {
        super(s);
    }
}
