package mindswap.academy.TranslatorApi.Repository;
import mindswap.academy.TranslatorApi.Models.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepositoryJpa extends JpaRepository<Client, Long> {

    Client findByUsername(String username);
}

