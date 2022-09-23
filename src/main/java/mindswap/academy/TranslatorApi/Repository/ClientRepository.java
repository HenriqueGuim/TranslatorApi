package mindswap.academy.TranslatorApi.Repository;

import mindswap.academy.TranslatorApi.Models.Client;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository
public class ClientRepository{
    Set<Client> clientList = new HashSet<>();

    public Set<Client> getClientList() {
        return clientList;
    }

    public boolean addClient(Client client) {
       return this.clientList.add(client);
    }
}
