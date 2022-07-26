package br.com.saulo.cleancommerce.data.dto;

import lombok.Value;

@Value
public class OrderItemResponse {
    Long id;
    Long  quantity;
    Double unitPrice;
    Double totalPrice;
}
