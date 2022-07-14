package br.com.saulo.cleancommerce.data.entities.dto;

import br.com.saulo.cleancommerce.core.domain.Customer;
import lombok.Value;

@Value
public class CustomerResponse {
    String name;
    String email;
    String cpf;
    String address;

    public static CustomerResponse fromCustomer(Customer customer) {
        return new CustomerResponse(
                customer.getName(),
                customer.getEmail(),
                customer.getCPF(),
                customer.getAddress()
        );
    }
}
