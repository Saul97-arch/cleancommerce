package br.com.saulo.cleancommerce.core.domain.exceptions;

public class ProductNotFoundException extends Exception{

    public ProductNotFoundException(String message) {
        super(message);
    }
}
