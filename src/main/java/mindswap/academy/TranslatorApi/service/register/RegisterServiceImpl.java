package mindswap.academy.TranslatorApi.service.register;

import mindswap.academy.TranslatorApi.Commands.ClientConverter;
import mindswap.academy.TranslatorApi.Commands.CreateClientDto;
import mindswap.academy.TranslatorApi.service.client.ClientServiceImpl;
import org.springframework.stereotype.Service;

@Service
public class RegisterServiceImpl implements RegisterService {

    private final ClientServiceImpl clientServiceImpl;


    public RegisterServiceImpl(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @Override
    public boolean addClient(CreateClientDto createClientDto) {

        return clientServiceImpl.addClient(ClientConverter.convertToEntity(createClientDto));
    }
}
