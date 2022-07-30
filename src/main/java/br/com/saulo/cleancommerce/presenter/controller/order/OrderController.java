package br.com.saulo.cleancommerce.presenter.controller.order;

import br.com.saulo.cleancommerce.core.usecases.order.OrderUseCase;
import br.com.saulo.cleancommerce.data.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@Component
public class OrderController implements IOrderController {

    @Autowired
    private OrderUseCase orderUseCase;

    @Override
    public CompletableFuture<ResponseEntity<OrderResponse>> createOrder(@Valid @RequestBody OrderRequest orderRequest) {
        return CompletableFuture.supplyAsync(() -> {
            try {
                return orderUseCase.orderItem(orderRequest);
            } catch (Exception e) {
                return ResponseEntity.badRequest().build();
            }
        });
    }

    public CompletableFuture<ResponseEntity<List<OrderResponse>>> listOrders() {
        return CompletableFuture.supplyAsync(() -> orderUseCase.listOrders());
    }
}
