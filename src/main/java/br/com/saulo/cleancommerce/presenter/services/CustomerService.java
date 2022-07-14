package br.com.saulo.cleancommerce.presenter.services;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.data.entities.dto.CustomerCreateRequest;
import br.com.saulo.cleancommerce.data.entities.dto.CustomerResponse;
import br.com.saulo.cleancommerce.data.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CustomerService {

    @Autowired
    CustomerRepository customerRepository;

    public List<CustomerResponse> listAllCustomers() {
        return customerRepository.listAllCustomers()
                .stream()
                .map(CustomerResponse::fromCustomer)
                .collect(Collectors.toList());
    }

    public CustomerResponse createCustomer(CustomerCreateRequest customerCreateRequest) {
        return customerRepository.persist(Customer.newInstance(
                customerCreateRequest.getCpf(),
                customerCreateRequest.getName(),
                customerCreateRequest.getAddress(),
                customerCreateRequest.getPassword(),
                customerCreateRequest.getEmail())
        ).fromThisCustomer();
    }

    public CustomerResponse findByEmail(String email) throws UserNotFoundException {
        return customerRepository.findByEmail(email).fromThisCustomer();
    }
}
