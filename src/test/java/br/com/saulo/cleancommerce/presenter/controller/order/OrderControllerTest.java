package br.com.saulo.cleancommerce.presenter.controller.order;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import org.apache.http.HttpStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.net.URI;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;


@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class OrderControllerTest {

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
    void createOrder() throws Exception {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer " + token);

        //language=JSON
        String json = """
                {
                  "customerId": 1
                }""";
        //@formatter:off
        given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .body(json)
                .when()
                .post("orders/create")
                .then()
                .statusCode(HttpStatus.SC_OK);
        //@formatter:on
    }

    @Test
    void listOrders() {
        Map<String, String> headers = new HashMap<>();
        headers.put("Accept", "application/json");
        headers.put("Authorization", "Bearer " + token);

        //@formatter:off
        given()
                .headers(headers)
                .contentType(ContentType.JSON)
                .when()
                .get("orders/list")
                .then()
                .statusCode(HttpStatus.SC_OK);
        //@formatter:on
    }
}