package br.com.saulo.cleancommerce.core.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@AllArgsConstructor
@Getter
@Setter
public class Customer {
    Long id;
    String CPF;
    String name;
    String address;
    String password;
    String email;

    public Customer newInstance(Long id, String cpf, String name, String address, String password, String email) {
        return new Customer(
                null,
                cpf,
                name,
                address,
                password,
                email
        );
    }
}
