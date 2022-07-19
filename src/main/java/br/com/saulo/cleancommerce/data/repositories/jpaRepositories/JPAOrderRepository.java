package br.com.saulo.cleancommerce.data.repositories.jpaRepositories;

import br.com.saulo.cleancommerce.data.entities.OrderData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAOrderRepository extends JpaRepository<OrderData, Long> {
}
