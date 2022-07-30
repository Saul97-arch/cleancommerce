package br.com.saulo.cleancommerce.presenter.controller.orderitem;

import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.core.usecases.orderItem.OrderItemUseCase;
import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.data.entities.exceptions.ProductNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

@Component
public class OrderItemController implements IOrderItemController {

    @Autowired
    private OrderItemUseCase orderItemUseCase;

    @Override
    public CompletableFuture<ResponseEntity<OrderItemResponse>> createOrderItem(OrderItemRequest orderItemRequest) {
        return CompletableFuture.supplyAsync(() ->
        {
            try {
                return ResponseEntity.ok(orderItemUseCase.createOrderItem(orderItemRequest));
            } catch (OrderNotFoundException | ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
