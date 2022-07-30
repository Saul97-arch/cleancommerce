package br.com.saulo.cleancommerce.presenter.controller.customer;

import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.core.usecases.customer.CustomerUseCase;
import br.com.saulo.cleancommerce.data.dto.CustomerCreateRequest;
import br.com.saulo.cleancommerce.data.dto.CustomerResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;
import java.util.List;

@Component
public class CustomerController implements ICustomerController{

    @Autowired
    CustomerUseCase customerUseCase;

    @Override
    public List<CustomerResponse> listAllCustomers() {
        return customerUseCase.listAllCustomers();
    }

    @Override
    public CustomerResponse createCustomer(@Valid @RequestBody CustomerCreateRequest customerCreateRequest) {
        return customerUseCase.createCustomer(customerCreateRequest);
    }

    @Override
    public CustomerResponse findByEmail(String email) throws UserNotFoundException {
        return customerUseCase.findByEmail(email);
    }
}
