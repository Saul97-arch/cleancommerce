package br.com.saulo.cleancommerce.presenter.controller.orderitem;

import br.com.saulo.cleancommerce.core.usecases.orderItem.OrderItemUseCase;
import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;

import java.util.concurrent.CompletableFuture;

public class OrderItemController implements IOrderItemController {

    @Autowired
    private OrderItemUseCase orderItemUseCase;

    @Override
    public CompletableFuture<ResponseEntity<OrderItemResponse>> createOrderItem(OrderItemRequest orderItemRequest) {
        return CompletableFuture.supplyAsync(() ->
                ResponseEntity.ok(orderItemUseCase.createOrderItem(orderItemRequest)));
    }
}
