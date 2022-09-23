package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Commands.ClientConverter;
import mindswap.academy.TranslatorApi.Commands.CreateClientDto;
import mindswap.academy.TranslatorApi.Repository.ClientRepository;
import org.springframework.stereotype.Service;

@Service
public class RegisterService {

    private final ClientService clientService;


    public RegisterService(ClientService clientService) {
        this.clientService = clientService;
    }

    public boolean addClient(CreateClientDto createClientDto) {

        return clientService.addClient(ClientConverter.convertToEntity(createClientDto));
    }
}
