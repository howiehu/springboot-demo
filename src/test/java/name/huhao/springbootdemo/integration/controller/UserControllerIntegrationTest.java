package name.huhao.springbootdemo.integration.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import name.huhao.springbootdemo.controller.UserController;
import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.given;
import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
public class UserControllerIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @BeforeEach
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(new UserController(userRepository));
    }

    @Test
    public void indexShouldReturnUsers() {
        var usersFromDB = Lists.newArrayList(new User("Alex", 18));
        Mockito.when(userRepository.findAll()).thenReturn(usersFromDB);

        when().get("/users")
                .then()
                .statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
                .body("size()", is(1))
                .body("name", hasItems("Alex"));
    }

    @Test
    public void createShouldCreateUser() {
        given().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body("{\n" +
                        "  \"name\": \"Alex\",\n" +
                        "  \"age\": 18\n" +
                        "}")
                .when().post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
