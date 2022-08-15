package br.com.saulo.cleancommerce.presenter.controller.customer;

import br.com.saulo.cleancommerce.core.usecases.customer.CustomerUseCase;
import br.com.saulo.cleancommerce.data.dto.CustomerCreateRequest;
import br.com.saulo.cleancommerce.data.dto.CustomerResponse;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.List;


@SpringBootTest
@AutoConfigureMockMvc
class CustomerControllerTest {

    @Autowired
    private CustomerUseCase customerUseCase;

    @Autowired
    private MockMvc mockMvc;

    @Test
    void listAllCustomers_shouldReturnAListOfCustomers() throws Exception {
        List<CustomerResponse> customerResponses = customerUseCase.listAllCustomers();
        URI uri = new URI("/customer/list");

        Assertions.assertNotNull(customerResponses);
        mockMvc.perform(MockMvcRequestBuilders
                .get(uri)).andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void createCustomerShouldCreateACustomer() throws Exception {
        CustomerCreateRequest customerCreateRequest = new CustomerCreateRequest(
                "212121211",
                "oluas",
                "duart.email@email",
                "5545",
                "Rua dos canos"
        );
        URI uri = new URI("/customer/create");

        CustomerResponse customer = customerUseCase.createCustomer(customerCreateRequest);

        Assertions.assertEquals("duart.email@email", customer.getEmail());
        Assertions.assertNotNull(customer);
        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(asJsonString(customerCreateRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    void findByEmail_shouldFindUserByEmail() throws Exception {
        CustomerResponse byEmail = customerUseCase.findByEmail("teste@email.com");

        Assertions.assertNotNull(byEmail);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/customer/{email}", "teste@email.com")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}