package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.data.entities.CustomerData;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CustomerRepository extends JpaRepository<CustomerData, Long> {
    Optional<CustomerData> findByEmail(String username);
}
