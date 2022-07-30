package br.com.saulo.cleancommerce.presenter.controller.orderitem;

import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/orderItem")
public interface IOrderItemController {

    @PostMapping("/create")
    CompletableFuture<ResponseEntity<OrderItemResponse>> createOrderItem(@Valid @RequestBody OrderItemRequest orderItemRequest);
}
