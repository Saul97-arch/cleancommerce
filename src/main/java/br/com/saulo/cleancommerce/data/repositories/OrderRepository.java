package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.OrderItem;
import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.data.entities.CustomerData;
import br.com.saulo.cleancommerce.data.entities.OrderData;
import br.com.saulo.cleancommerce.data.entities.OrderItemData;
import br.com.saulo.cleancommerce.data.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

// TODO use a interface
@Repository
public class OrderRepository {

    @Autowired
    JPAOrderRepository orderRepository;
    @Async
    public OrderResponse persist(Order order) {

        List<OrderItem> orderItemList = order.getOrderItemList();
        List<OrderItemData> from = OrderItemData.from(orderItemList);

        // TODO make a better return for order creation
        OrderData orderData = orderRepository.save(OrderData.newInstance(
                from,
                CustomerData.fromCustomer(order.getCustomer())
        ));

        return new OrderResponse(orderData.getStatus().toString(), orderData.getTotal());
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

    public Order findById(Long id) throws OrderNotFoundException {
        Optional<OrderData> orderData = orderRepository.findById(id);
        if (orderData.isPresent()) {
            return new Order(
                    orderData.get().getId(),
                    orderData.get().getStatus(),
                    OrderItem.from(orderData.get().getOrderItems()),
                    orderData.get().getCreatedAt(),
                    orderData.get().getTotal(),
                    Customer.from(orderData.get().getCustomerData())
            );
        }

        throw new OrderNotFoundException("Order not found!");
    }
}
