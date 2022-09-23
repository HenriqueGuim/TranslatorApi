package mindswap.academy.TranslatorApi.Commands;

import mindswap.academy.TranslatorApi.Models.Client;

public class ClientConverter {

    public Client convertToEntity(CreateClientDto createClientDto){
        return Client.builder()
                .id(createClientDto.getId())
                .name(createClientDto.getName())
                .username(createClientDto.getUsername())
                .password(createClientDto.getPassword())
                .email(createClientDto.getEmail())
                .build();
    }

}
