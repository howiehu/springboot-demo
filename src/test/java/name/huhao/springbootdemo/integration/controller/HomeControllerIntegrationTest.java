package name.huhao.springbootdemo.integration.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import name.huhao.springbootdemo.controller.HomeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.containsString;

@ExtendWith(SpringExtension.class)
public class HomeControllerIntegrationTest {

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(new HomeController());
    }

    @Test
    public void indexShouldReturnHelloWorld() {
        when().get("/")
                .then()
                .statusCode(200).contentType(ContentType.TEXT)
                .body(containsString("Hello World!"));
    }
}
