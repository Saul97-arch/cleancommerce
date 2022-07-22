package br.com.saulo.cleancommerce.presenter.controller.order;

import br.com.saulo.cleancommerce.data.entities.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orders")
public interface IOrderController {
    @PostMapping("/buy")
    CompletableFuture<ResponseEntity<OrderResponse>> orderItem(OrderRequest orderRequest) throws Exception;

    @GetMapping("/list")
    CompletableFuture<ResponseEntity<List<OrderResponse>>> listOrders();
}