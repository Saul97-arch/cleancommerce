package br.com.saulo.cleancommerce.core.usecases.customer;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.data.entities.CustomerData;

import java.util.List;
import java.util.Optional;

public interface ICustomerRepository {
    List<Customer> listAllCustomers();
    Customer persist(Customer customer);
    Customer findByEmail(String email) throws UserNotFoundException;

    Optional<Customer> findById(long id) throws UserNotFoundException;
}
