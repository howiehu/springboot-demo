package name.huhao.springbootdemo.integration.repository;

import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import static org.assertj.core.api.Assertions.assertThat;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserRepositoryTest {

    @Autowired
    private TestEntityManager entityManager;

    @Autowired
    private UserRepository repository;

    @Test
    public void userCanFindByName() {
        entityManager.persist(new User("Alex", 18));

        var foundUsers = repository.findByName("Alex");

        assertThat(foundUsers).hasSize(1)
                .first()
                .hasFieldOrPropertyWithValue("name", "Alex")
                .hasFieldOrPropertyWithValue("age", 18);
    }

    @Test
    public void userCanSave() {
        var user = repository.save(new User("Alex", 18));

        var createdUser = entityManager.find(User.class, user.getId());

        assertThat(createdUser)
                .hasFieldOrPropertyWithValue("name", "Alex")
                .hasFieldOrPropertyWithValue("age", 18);
    }
}
