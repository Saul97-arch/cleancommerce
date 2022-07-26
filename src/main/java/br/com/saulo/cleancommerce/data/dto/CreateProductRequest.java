package br.com.saulo.cleancommerce.data.dto;

import lombok.Value;

@Value
public class CreateProductRequest {
    String name;
    String description;
    Double price;
}
