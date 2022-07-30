package br.com.saulo.cleancommerce.core.usecases.order;

import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.data.dto.OrderResponse;

import java.util.List;

public interface IOrderRepository {
    OrderResponse persist(Order order);
    List<OrderResponse> listAll();
    Order findById(Long id) throws OrderNotFoundException;
}
