package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Value;

@Value
public class OrderItemRequest {
    Long quantity;
    String name;
}
