package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.core.domain.exceptions.ProductNotFoundException;
import br.com.saulo.cleancommerce.data.repositories.OrderItemRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class OrderItemService {

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Async
    public OrderItemResponse createOrderItem(OrderItemRequest orderItemRequest) throws OrderNotFoundException, ProductNotFoundException {
        return orderItemRepository.persist(orderItemRequest);
    }
}
