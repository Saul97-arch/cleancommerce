package br.com.saulo.cleancommerce.data.repositories.jpaRepositories;

import br.com.saulo.cleancommerce.data.entities.ProductData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JPAProductRepository extends JpaRepository<ProductData, Long> {
        ProductData findByName(String name);
}
