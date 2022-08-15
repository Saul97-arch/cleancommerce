package br.com.saulo.cleancommerce.presenter.controller.orderitem;

import br.com.saulo.cleancommerce.core.usecases.order.OrderUseCase;
import br.com.saulo.cleancommerce.core.usecases.orderItem.OrderItemUseCase;
import br.com.saulo.cleancommerce.data.dto.OrderItemRequest;
import br.com.saulo.cleancommerce.data.dto.OrderItemResponse;
import br.com.saulo.cleancommerce.data.dto.OrderRequest;
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

@SpringBootTest
@AutoConfigureMockMvc
class OrderItemControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private OrderItemUseCase orderItemUseCase;

    @Autowired
    private OrderUseCase orderUseCase;

    @Test
    void createOrderItemShouldCreateOrderItem() throws Exception {

        OrderItemRequest orderItemRequest = new OrderItemRequest(1L, 1L, 5L, "finneas");
        OrderRequest orderRequest = new OrderRequest(1L);
        orderUseCase.orderItem(orderRequest);
        URI uri = new URI("/orderItem/create");

        OrderItemResponse orderItem = orderItemUseCase.createOrderItem(orderItemRequest);

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(asJsonString(orderRequest))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(MockMvcResultMatchers.status().isOk());
        Assertions.assertNotNull(orderItem);
    }

    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}