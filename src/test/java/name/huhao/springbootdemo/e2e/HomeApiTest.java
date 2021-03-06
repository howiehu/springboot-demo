package name.huhao.springbootdemo.e2e;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.containsString;

@ExtendWith(SpringExtension.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class HomeApiTest {

    @LocalServerPort
    private int port;

    @Test
    public void indexShouldReturnHelloWorld() {
        given().port(port).when().get("/").then()
                .statusCode(200).contentType(ContentType.TEXT)
                .body(containsString("Hello World!"));
    }
}
