package br.com.saulo.cleancommerce.presenter.controller.authentication;

import br.com.saulo.cleancommerce.CleancommerceApplication;
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


@SpringBootTest(classes = CleancommerceApplication.class)
@AutoConfigureMockMvc
class AuthenticationControllerTest {

    @Autowired
    private IAuthenticationController iAuthenticationController;

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void contextLoads() {
        Assertions.assertNotNull(iAuthenticationController);
    }

    @Test
    public void shouldReturnBadRequestForInvalidUserData() throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"email\": \"xurba@gmail.com\", \"senha\": \"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().is(400));
    }

    @Test
    public void shouldReturnStatusOkWithValidUserData() throws Exception {
        URI uri = new URI("/auth");
        String json = "{\"user\": \"teste@email.com\", \"password\": \"123456\"}";

        mockMvc.perform(MockMvcRequestBuilders
                        .post(uri)
                        .content(json)
                        .contentType(MediaType.APPLICATION_JSON)
                )
                .andExpect(MockMvcResultMatchers.status().is(200));
    }
}