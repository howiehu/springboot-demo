package name.huhao.springbootdemo.unit.controller;

import name.huhao.springbootdemo.controller.UserController;
import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.assertj.core.util.Lists;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(SpringExtension.class)
public class UserControllerTest {

    @Mock
    private UserRepository userRepository;

    private UserController controller;

    @BeforeEach
    public void setUp() {
        controller = new UserController(userRepository);
    }

    @Test
    public void indexShouldReturnUsers() {

        var usersFromDB = Lists.newArrayList(new User("Alex", 18));
        when(userRepository.findAll()).thenReturn(usersFromDB);

        var users = controller.index();

        assertThat(users).isEqualTo(usersFromDB);
    }

    @Test
    public void createShouldCreateUser() {
        var user = new User("Alex", 18);

        controller.create(user);

        verify(userRepository).save(user);
    }
}
