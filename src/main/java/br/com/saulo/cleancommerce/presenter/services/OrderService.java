package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.OrderItem;
import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.data.dto.OrderRequest;
import br.com.saulo.cleancommerce.data.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.entities.exceptions.ProductNotFoundException;
import br.com.saulo.cleancommerce.data.repositories.CustomerRepository;
import br.com.saulo.cleancommerce.data.repositories.OrderRepository;
import br.com.saulo.cleancommerce.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Async
    public ResponseEntity<OrderResponse> orderItem(OrderRequest orderRequest) throws UserNotFoundException {

        List<OrderItem> orderItems = orderRequest.getOrderItemRequestList().stream().map(orderItemRequest -> {
            Product product;
            try {
                product = productRepository.findByName(orderItemRequest.getName());
            } catch (ProductNotFoundException e) {
                throw new RuntimeException(e);
            }
            return OrderItem.newInstance(orderItemRequest.getQuantity(), product);
        }).toList();

        Double totalPrice = orderItems
                .stream()
                .reduce(0.0, (total, orderItem) -> total + orderItem.getUnitPrice(), Double::sum);

        if (customerRepository.findById(orderRequest.getCustomerId()).isPresent()) {
            Customer customer = customerRepository.findById(orderRequest.getCustomerId()).get();
            OrderResponse persist = orderRepository.persist(Order.newInstance(orderItems, totalPrice, customer));
            return ResponseEntity.ok(persist);
        }

        return ResponseEntity.badRequest().build();
    }
    @Async
    public ResponseEntity<List<OrderResponse>> listOrders() {
        return ResponseEntity.ok(orderRepository.listAll());
    }
}
