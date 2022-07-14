package br.com.saulo.cleancommerce.core.usecases.customer;

import br.com.saulo.cleancommerce.core.domain.Customer;
import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.data.entities.dto.CustomerCreateRequest;
import br.com.saulo.cleancommerce.data.entities.dto.CustomerResponse;
import br.com.saulo.cleancommerce.presenter.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class CustomerUseCase {

    @Autowired
    private CustomerService customerService;

    public List<CustomerResponse> listAllCustomers() {
       return customerService.listAllCustomers();
    }

    public CustomerResponse createCustomer(CustomerCreateRequest customer) {
        return customerService.createCustomer(customer);
    }

    public CustomerResponse findByEmail(String email) throws UserNotFoundException {
        return customerService.findByEmail(email);
    }
}
