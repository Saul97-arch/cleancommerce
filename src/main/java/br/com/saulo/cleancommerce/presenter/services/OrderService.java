package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.core.domain.OrderItem;
import br.com.saulo.cleancommerce.core.domain.Product;
import br.com.saulo.cleancommerce.data.entities.dto.OrderRequest;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.repositories.OrderRepository;
import br.com.saulo.cleancommerce.data.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;

    public ResponseEntity<OrderResponse> orderItem(OrderRequest orderRequest) throws Exception{

        try {
            List<OrderItem> orderItems = orderRequest.getOrderItemRequestList().stream().map(orderItemRequest -> {
                // TODO go to optional and async
                Product product = productRepository.findByName(orderItemRequest.getName());
                return OrderItem.newInstance(orderItemRequest.getQuantity(), product);
            }).toList();

            Double totalPrice = orderItems
                    .stream()
                    .reduce(0.0, (total, orderItem) -> total + orderItem.getTotal(), Double::sum);
            orderRepository.persist(Order.newInstance(orderItems, totalPrice, getCustomer()));

            return ResponseEntity.ok().build();
        } catch (Exception e) {
            System.out.println("ESSECAO");
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    private Customer getCustomer() {
        return new Customer(
                1L,
                "33333",
                "Rob",
                "Avenue 7th ",
                "23324324",
                "teste@teste.com");
    }
}
