package br.com.saulo.cleancommerce.data.dto;

import lombok.Value;

import java.util.List;

@Value
public class OrderResponse {
    Long id;
    String status;
    Double total;
    List<OrderItemResponse> orderItemResponses;
}
