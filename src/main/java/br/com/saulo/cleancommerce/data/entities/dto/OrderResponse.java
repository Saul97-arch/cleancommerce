package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Value;

@Value
public class OrderResponse {
    String status;
    Double total;
}
