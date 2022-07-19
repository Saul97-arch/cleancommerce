package br.com.saulo.cleancommerce.presenter.controller.order;

import br.com.saulo.cleancommerce.core.usecases.order.OrderUseCase;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.entities.dto.OrderRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
public class OrderController implements IOrderController{

    @Autowired
    private OrderUseCase orderUseCase;

    @Override
    public ResponseEntity<OrderResponse> orderItem(@Valid @RequestBody OrderRequest orderRequest) throws Exception {
        return orderUseCase.orderItem(orderRequest);
    }
}
