package mindswap.academy.TranslatorApi.Commands;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.apache.commons.lang3.SerializationUtils;

import java.util.HashMap;
import java.util.LinkedList;

public class ClientConverter {

    public static Client convertToEntity(CreateClientDto createClientDto){
        return Client.builder()
                .id(createClientDto.getId())
                .name(createClientDto.getName())
                .username(createClientDto.getUsername())
                .password(createClientDto.getPassword())
                .email(createClientDto.getEmail())
                .translations(SerializationUtils.serialize(new HashMap<Languages, HashMap<Languages,Long>>()))
                .translationsWithText(SerializationUtils.serialize(new LinkedList<TranslationWithText>()))
                .build();
    }

}
