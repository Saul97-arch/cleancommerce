package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.exceptions.OrderNotFoundException;
import br.com.saulo.cleancommerce.core.usecases.orderItem.IOrderItemRepository;
import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.data.entities.OrderData;
import br.com.saulo.cleancommerce.data.entities.OrderItemData;
import br.com.saulo.cleancommerce.data.entities.ProductData;
import br.com.saulo.cleancommerce.core.domain.exceptions.ProductNotFoundException;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAOrderItemRepository;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAOrderRepository;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class OrderItemRepository implements IOrderItemRepository {

    @Autowired
    private JPAOrderItemRepository jpaOrderItemRepository;

    @Autowired
    private JPAProductRepository productRepository;

    @Autowired
    private JPAOrderRepository orderRepository;

    @Async
    public OrderItemResponse persist(OrderItemRequest orderItemRequest) throws ProductNotFoundException, OrderNotFoundException {
        Long productId = orderItemRequest.getProductId();
        Optional<ProductData> product = productRepository.findById(productId);

        Long orderId = orderItemRequest.getOrderId();
        Optional<OrderData> orderData = orderRepository.findById(orderId);

        assert product.isPresent();

        if (orderData.isPresent()) {
            OrderItemData orderItemData = jpaOrderItemRepository.save(
                    OrderItemData.newInstance(orderItemRequest.getQuantity(),
                            product.get().getPrice(),
                            orderData.get(),
                            product.get())
            );

            Double totalPrice = orderItemData.getUnitPrice() * orderItemData.getQuantity();

            setOrderTotalPrice(orderData.get(), totalPrice);

            return new OrderItemResponse(
                    orderItemData.getId(),
                    orderItemData.getQuantity(),
                    orderItemData.getUnitPrice(),
                    totalPrice
            );
        }

        throw new OrderNotFoundException("Order with id : " + orderItemRequest.getOrderId() + " not found!");
    }

    private void setOrderTotalPrice(OrderData orderData, Double totalPrice) {
        if (orderData.getTotal() == 0) {
            orderData.setTotal(totalPrice);
        } else {
            var aux = orderData.getTotal() + totalPrice;
            orderData.setTotal(aux);
        }

        orderRepository.save(orderData);
    }
}
