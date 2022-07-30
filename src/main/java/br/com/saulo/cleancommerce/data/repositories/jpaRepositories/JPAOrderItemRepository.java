package br.com.saulo.cleancommerce.data.repositories.jpaRepositories;

import br.com.saulo.cleancommerce.data.entities.OrderItemData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAOrderItemRepository extends JpaRepository<OrderItemData, Long> {
}
