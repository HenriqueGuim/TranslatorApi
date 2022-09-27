package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.AssertionsForInterfaceTypes.assertThat;

@ContextConfiguration(classes = ClientRepositoryJpa.class)
@ExtendWith(SpringExtension.class)
public class ClientRepositoryTest {

    @MockBean
    ClientRepositoryJpa repository;


    @Test
    public void saveClient(){
        Client client = Client.builder()
                .id(5L)
                .name("Fernando Santos")
                .username("Fernando")
                .password("1234")
                .email("f@gmail.com").build();

        repository.save(client);

        assertThat(repository.findByUsername("Fernando").getId()).isGreaterThan(0);
    }
}
