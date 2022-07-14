package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Value;

@Value
public class CustomerCreateRequest {
    String cpf;
    String name;
    String email;
    String password;
    String address;
}
