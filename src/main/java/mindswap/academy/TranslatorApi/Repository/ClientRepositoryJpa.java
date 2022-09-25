package mindswap.academy.TranslatorApi.Repository;

import mindswap.academy.TranslatorApi.Models.Client;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

@Repository
public class ClientRepositoryJpa {
    Set<Client> clientList = new HashSet<>();

    public Set<Client> getClientList() {
        return clientList;
    }

    public boolean addClient(Client client) {
       return this.clientList.add(client);
    }

    public Client getClientByUsername(String username){
        return clientList.stream().filter(client1 -> client1.getUsername().equals(username)).findFirst().orElse(null);
    }
}
