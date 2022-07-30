package br.com.saulo.cleancommerce.data.dto;

import lombok.Value;

@Value
public class OrderItemRequest {
    Long orderId;
    Long productId;
    Long quantity;
    String name;
}
