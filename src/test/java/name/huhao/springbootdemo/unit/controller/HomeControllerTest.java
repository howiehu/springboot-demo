package name.huhao.springbootdemo.unit.controller;

import name.huhao.springbootdemo.controller.HomeController;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
public class HomeControllerTest {

    private HomeController controller;

    @BeforeEach
    public void setUp() {
        controller = new HomeController();
    }

    @Test
    public void indexShouldReturnHelloWorld() {
        assertThat(controller.index()).isEqualTo("Hello World!");
    }
}
