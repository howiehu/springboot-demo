package name.huhao.springbootdemo.e2e;

import io.restassured.http.ContentType;
import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class UserApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @Before
    @FlywayTest
    public void setUp() {

    }

    @Test
    public void indexShouldReturnUsers() {
        userRepository.save(new User("Alex", 18));

        given().port(port).when().get("/users")
                .then()
                .statusCode(200).contentType(ContentType.JSON)
                .body("size()", is(1))
                .body("name", hasItems("Alex"));
    }
}
