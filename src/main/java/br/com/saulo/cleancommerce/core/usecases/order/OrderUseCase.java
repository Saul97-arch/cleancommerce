package br.com.saulo.cleancommerce.core.usecases.order;
import br.com.saulo.cleancommerce.data.entities.dto.OrderRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import br.com.saulo.cleancommerce.presenter.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class OrderUseCase {

    @Autowired
    private OrderService orderService;

    @Async
    public ResponseEntity<OrderResponse> orderItem(OrderRequest orderItemRequest) {
        return orderService.orderItem(orderItemRequest);
    }

    @Async
    public ResponseEntity<List<OrderResponse>> listOrders() {
        return orderService.listOrders();
    }
}
