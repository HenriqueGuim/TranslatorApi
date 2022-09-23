package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class ClientService {

    private final ClientRepository clientRepository;
    private final RoleService roleService;

    public ClientService(ClientRepository clientRepository, RoleService roleService) {
        this.clientRepository = clientRepository;
        this.roleService = roleService;
    }


    public boolean addClient(Client client) {
        return clientRepository.addClient(client.addRole(roleService.get("FREE")));
    }
}
