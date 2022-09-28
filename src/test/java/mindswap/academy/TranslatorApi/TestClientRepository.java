package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.transaction.annotation.Transactional;

@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ExtendWith(SpringExtension.class)
@Transactional
@DataJpaTest
public class TestClientRepository {

    @Autowired
    private ClientRepositoryJpa clientRepositoryJpa;


    @Test
    public void testClientRepositoryAddClient() {

        //Given
        Client client = new Client(1L,"Henrique","zpatins","1234","asdf");
        //When
        Client client1 = clientRepositoryJpa.save(client);
        //Then
        Assertions.assertEquals(clientRepositoryJpa.findById(1L).get(),client1);
    }
}
