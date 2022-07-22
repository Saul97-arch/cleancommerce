package br.com.saulo.cleancommerce.core.domain;

import br.com.saulo.cleancommerce.data.entities.CustomerData;
import br.com.saulo.cleancommerce.data.entities.dto.CustomerResponse;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Optional;

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

    public static Customer from(CustomerData customerData) {
        return new Customer(
                customerData.getId(),
                customerData.getCPF(),
                customerData.getName(),
                customerData.getAddress(),
                null,
                customerData.getEmail()
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
