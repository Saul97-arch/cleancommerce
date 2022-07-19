package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Value;

import java.util.List;

@Data
@Getter
public class OrderRequest {
     List<OrderItemRequest> orderItemRequestList;
}
