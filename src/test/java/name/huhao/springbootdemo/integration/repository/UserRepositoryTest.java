package name.huhao.springbootdemo.integration.repository;

import name.huhao.springbootdemo.model.User;
import name.huhao.springbootdemo.repository.UserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
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
