package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.OrderItem;
import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.core.usecases.order.IOrderRepository;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.data.entities.OrderData;
import br.com.saulo.cleancommerce.data.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.entities.OrderItemData;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import static br.com.saulo.cleancommerce.data.entities.CustomerData.fromCustomer;

@Repository
public class OrderRepository implements IOrderRepository {

    @Autowired
    JPAOrderRepository orderRepository;

    @Async
    public OrderResponse persist(Order order) {

        OrderData orderData = orderRepository.save(OrderData.newInstance(fromCustomer(order.getCustomer())));

        return new OrderResponse(
                orderData.getId(),
                orderData.getStatus().toString(),
                orderData.getTotal(),
                convertToOrderItemResponseList(orderData.getOrderItems())
        );
    }

    @Async
    public List<OrderResponse> listAll() {
        return orderRepository.findAll().stream().map((orderData ->
                new OrderResponse(
                        orderData.getId(),
                        orderData.getStatus().toString(),
                        orderData.getTotal(),
                        convertToOrderItemResponseList(orderData.getOrderItems())
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

    private List<OrderItemResponse> convertToOrderItemResponseList(List<OrderItemData> orderItemDataList) {
        if (orderItemDataList == null) {
            return Collections.emptyList();
        }

        return orderItemDataList.stream().map((orderItemData -> new OrderItemResponse(
                orderItemData.getId(),
                orderItemData.getQuantity(),
                orderItemData.getUnitPrice(),
                orderItemData.getUnitPrice() * orderItemData.getQuantity()
        ))).collect(Collectors.toList());
    }
}
