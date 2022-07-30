package br.com.saulo.cleancommerce.presenter.controller.order;

import br.com.saulo.cleancommerce.data.dto.OrderRequest;
import br.com.saulo.cleancommerce.data.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public interface IOrderController {
    @PostMapping("/create")
    CompletableFuture<ResponseEntity<OrderResponse>> createOrder(OrderRequest orderRequest) throws Exception;

    @GetMapping("/list")
    CompletableFuture<ResponseEntity<List<OrderResponse>>> listOrders();
}
