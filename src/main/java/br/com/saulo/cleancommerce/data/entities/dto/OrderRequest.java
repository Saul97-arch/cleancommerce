package br.com.saulo.cleancommerce.data.entities.dto;

import lombok.Data;
import lombok.Getter;

import javax.validation.constraints.NotNull;
import java.util.List;

@Data
@Getter
public class OrderRequest {
    @NotNull(message = "id is null send a valid id!")
    Long customerId;
    List<OrderItemRequest> orderItemRequestList;
}
