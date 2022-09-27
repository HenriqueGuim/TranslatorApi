package mindswap.academy.TranslatorApi.service.register;

import mindswap.academy.TranslatorApi.Commands.CreateClientDto;

public interface RegisterService {
    boolean addClient(CreateClientDto createClientDto);
}
