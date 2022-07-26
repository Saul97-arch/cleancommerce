package br.com.saulo.cleancommerce.data.dto;

import lombok.Value;

@Value
public class OrderResponse {
    String status;
    Double total;
}
