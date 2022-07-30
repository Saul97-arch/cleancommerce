package br.com.saulo.cleancommerce.presenter.controller.customer;

import br.com.saulo.cleancommerce.core.domain.exceptions.UserNotFoundException;
import br.com.saulo.cleancommerce.data.dto.CustomerCreateRequest;
import br.com.saulo.cleancommerce.data.dto.CustomerResponse;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/customer")
public interface ICustomerController {
    @GetMapping("/list")
    List<CustomerResponse> listAllCustomers();

    @PostMapping("/create")
    CustomerResponse createCustomer(CustomerCreateRequest customerCreateRequest);

    @GetMapping("/{email}")
    CustomerResponse findByEmail(@PathVariable String email) throws UserNotFoundException;
}
