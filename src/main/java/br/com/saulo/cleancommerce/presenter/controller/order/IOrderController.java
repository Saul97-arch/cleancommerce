package br.com.saulo.cleancommerce.presenter.controller.order;

import br.com.saulo.cleancommerce.data.entities.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/orders")
public interface IOrderController {
    @PostMapping("/buy")
    ResponseEntity<OrderResponse> orderItem(OrderRequest orderRequest) throws Exception;
}
