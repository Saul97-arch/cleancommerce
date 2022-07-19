package br.com.saulo.cleancommerce.core.domain;

import br.com.saulo.cleancommerce.data.entities.dto.CustomerResponse;
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

    public static Customer newInstance(String cpf, String name, String address, String password, String email) {
        return new Customer(
                null,
                cpf,
                name,
                address,
                password,
                email
        );
    }

    public CustomerResponse fromThisCustomer() {
        return new CustomerResponse(
                name,
                email,
                CPF,
                address
        );
    }
}
