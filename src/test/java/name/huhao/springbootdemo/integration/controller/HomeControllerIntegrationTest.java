package name.huhao.springbootdemo.integration.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import name.huhao.springbootdemo.controller.HomeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.junit4.SpringRunner;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.containsString;

@RunWith(SpringRunner.class)
public class HomeControllerIntegrationTest {

    @Before
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
