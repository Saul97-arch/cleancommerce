package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Value;

@Value
public class CreateProductRequest {
    String name;
    String description;
    Double price;
}
