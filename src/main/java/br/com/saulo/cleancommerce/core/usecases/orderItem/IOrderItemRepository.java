package br.com.saulo.cleancommerce.core.usecases.orderItem;

import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.data.entities.exceptions.ProductNotFoundException;

public interface IOrderItemRepository {
    OrderItemResponse persist(OrderItemRequest orderItemRequest) throws ProductNotFoundException, OrderNotFoundException;
}
