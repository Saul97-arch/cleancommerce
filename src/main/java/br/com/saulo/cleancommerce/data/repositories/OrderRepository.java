package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.OrderItem;
import br.com.saulo.cleancommerce.data.entities.OrderData;
import br.com.saulo.cleancommerce.data.entities.OrderItemData;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class OrderRepository {

    @Autowired
    JPAOrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Async
    public OrderResponse persist(Order order) {

        List<OrderItem> orderItemList = order.getOrderItemList();
        List<OrderItemData> from = OrderItemData.from(orderItemList);
        orderRepository.save(OrderData.newInstance(from));

        return null;
    }


    @Async
    public List<OrderResponse> listAll() {
        return orderRepository.findAll().stream().map((orderData ->
                new OrderResponse(
                        orderData.getStatus().toString(),
                        orderData.getTotal()
                )
        )).collect(Collectors.toList());
    }
}
