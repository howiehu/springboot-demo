package name.huhao.springbootdemo.e2e;

import io.restassured.http.ContentType;
import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.flywaydb.test.FlywayTestExecutionListener;
import org.flywaydb.test.annotation.FlywayTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.hasItems;
import static org.hamcrest.CoreMatchers.is;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@TestExecutionListeners({DependencyInjectionTestExecutionListener.class, FlywayTestExecutionListener.class})
public class UserApiTest {

    @LocalServerPort
    private int port;

    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    @FlywayTest
    public void setUp() {

    }

    @Test
    public void indexShouldReturnUsers() {
        userRepository.save(new User("Alex", 18));

        given().port(port).when().get("/users")
                .then()
                .statusCode(HttpStatus.OK.value()).contentType(ContentType.JSON)
                .body("size()", is(1))
                .body("name", hasItems("Alex"));
    }

    @Test
    public void createShouldCreateUser() {
        given().port(port).contentType(MediaType.APPLICATION_JSON_UTF8_VALUE)
                .body("{\n" +
                        "  \"name\": \"Alex\",\n" +
                        "  \"age\": 18\n" +
                        "}")
                .when().post("/users")
                .then()
                .statusCode(HttpStatus.CREATED.value());
    }
}
