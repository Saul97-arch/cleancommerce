package br.com.saulo.cleancommerce.core.usecases.orderItem;

import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.presenter.services.OrderItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

@Component
public class OrderItemUseCase {

    @Autowired
    private OrderItemService orderItemService;

    @Async
    public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) {
        return orderItemService.createOrderItem(orderItemRequest);
    }
}
