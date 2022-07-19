package br.com.saulo.cleancommerce.data.repositories;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.core.usecases.customer.ICustomerRepository;
import br.com.saulo.cleancommerce.data.entities.CustomerData;
import br.com.saulo.cleancommerce.data.repositories.jpaRepositories.JPACustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class CustomerRepository implements ICustomerRepository {

    @Autowired
    JPACustomerRepository jpaCustomerRepository;

    @Override
    public List<Customer> listAllCustomers() {
        return jpaCustomerRepository
                .findAll()
                .stream()
                .map(CustomerData::fromCustomerData)
                .collect(Collectors.toList());
    }

    @Override
    public Customer persist(Customer customer) {
        CustomerData customerData = CustomerData.fromCustomer(customer);
        return jpaCustomerRepository.save(customerData).fromCustomerData();
    }

    @Override
    public Customer findByEmail(String email) throws UserNotFoundException {
        Optional<CustomerData> customerData = jpaCustomerRepository.findByEmail(email);
        if (customerData.isPresent()) {
            return new Customer(customerData.get().getId(),
                    customerData.get().getCPF(),
                    customerData.get().getName(),
                    customerData.get().getAddress(),
                    customerData.get().getPassword(),
                    customerData.get().getPassword());
        }
        throw new UserNotFoundException("User not found!");
    }
}
