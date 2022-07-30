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
import java.util.Optional;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    @Autowired
    CustomerRepository customerRepository;

    @Async
    public ResponseEntity<OrderResponse> createOrder(OrderRequest orderRequest) throws UserNotFoundException {

        Optional<Customer> customer = customerRepository.findById(orderRequest.getCustomerId());
        if (customer.isPresent()) {
            OrderResponse persist = orderRepository.persist(Order.newInstance(customer.get()));
            return ResponseEntity.ok(persist);
        }

        throw new UserNotFoundException("This customer does not exists!");
    }
    @Async
    public ResponseEntity<List<OrderResponse>> listOrders() {
        return ResponseEntity.ok(orderRepository.listAll());
    }
}
