package br.com.saulo.cleancommerce.data.dto;

import lombok.Value;

@Value
public class TokenDto {

    String token;
    String bearer;
}
