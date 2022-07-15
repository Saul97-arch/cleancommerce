package br.com.saulo.cleancommerce.core.usecases.order;

import br.com.saulo.cleancommerce.data.entities.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import br.com.saulo.cleancommerce.presenter.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component
public class OrderUseCase {

    @Autowired
    private OrderService orderService;
    public ResponseEntity<OrderResponse> orderItem(OrderItemRequest orderItemRequest) {
        return orderService.orderItem(orderItemRequest);
    }
}
