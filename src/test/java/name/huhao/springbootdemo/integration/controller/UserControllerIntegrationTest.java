package name.huhao.springbootdemo.integration.controller;

import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import name.huhao.springbootdemo.controller.UserController;
import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static io.restassured.module.mockmvc.RestAssuredMockMvc.when;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
public class UserControllerIntegrationTest {

    @MockBean
    private UserRepository userRepository;

    @Before
    public void setUp() {
        RestAssuredMockMvc.standaloneSetup(new UserController(userRepository));
    }

    @Test
    public void indexShouldReturnUsers() {
        List<User> usersFromDB = Lists.newArrayList(new User("Alex", 18));
        Mockito.when(userRepository.findAll()).thenReturn(usersFromDB);

        when().get("/users")
                .then()
                .statusCode(200).contentType(ContentType.JSON)
                .body("size()", is(1))
                .body("name", hasItems("Alex"));
    }
}
