package br.com.saulo.cleancommerce.data.repositories;
import br.com.saulo.cleancommerce.core.domain.Order;
import br.com.saulo.cleancommerce.data.entities.OrderData;
import br.com.saulo.cleancommerce.data.entities.dto.OrderResponse;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPAOrderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class OrderRepository {

    @Autowired
    JPAOrderRepository orderRepository;

    @Autowired
    ProductRepository productRepository;
    public OrderResponse persist(Order order) {

        orderRepository.save(OrderData.from(order));

        return null;
    }
}
