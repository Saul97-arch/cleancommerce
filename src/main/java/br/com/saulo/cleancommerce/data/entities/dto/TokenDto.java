package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Value;

@Value
public class TokenDto {

    String token;
    String bearer;
}
