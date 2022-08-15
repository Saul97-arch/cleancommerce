package br.com.saulo.cleancommerce.presenter.controller.product;

import br.com.saulo.cleancommerce.core.usecases.products.ProductsUseCase;
import br.com.saulo.cleancommerce.data.dto.ProductResponse;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.net.URI;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static io.restassured.RestAssured.given;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class ProductsControllerTest {
    @LocalServerPort
    protected int port;

    private String token = "";

    @BeforeEach
    public void beforeTest() {


        RestAssured.port = port;
        RestAssured.enableLoggingOfRequestAndResponseIfValidationFails();

        //language=JSON
        String body = """
                    {
                        "user": "teste@email.com",
                        "password": "123456"
                    }
                """;
        given()
                .contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("auth")
                .then()
                .assertThat()
                .statusCode(HttpStatus.SC_OK);

        // generate token
        token = given().contentType(ContentType.JSON)
                .body(body)
                .when()
                .post("auth")
                .jsonPath()
                .get("token");
    }

    @Test
    void createProduct() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer " + token);

        //language=JSON
        String json = """
                {
                  "name": "product",
                  "description" : "description",
                  "price" : 778.0
                }""";
        //@formatter:off


        //@formatter:off
        given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("product/create")
                .then()
                .statusCode(HttpStatus.SC_OK);
        //@formatter:on
    }

    @Test
    void listProducts() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer " + token);

        //@formatter:off
        given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get("product/list")
                .then()
                .statusCode(HttpStatus.SC_OK);
        //@formatter:on
    }
}
