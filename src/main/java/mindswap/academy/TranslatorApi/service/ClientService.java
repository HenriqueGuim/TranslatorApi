package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.Repository.ClientRepository;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.stereotype.Service;

import java.util.*;

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


    public Client getClientByUsername(String username) {
        return clientRepository.getClientByUsername(username);
    }

    public Set<Client> getAllClients() {
        return clientRepository.getClientList();
    }

    public Client getClientById(Long id) {
        return clientRepository.getClientList().stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);
    }

    public Map<Languages, Map<Languages,Long>> addTranslation(Languages srcLang, Languages trgLang, Client client) {
        Map<Languages, Map<Languages, Long>> translations = client.getTranslations();

        if(translations.containsKey(srcLang) && translations.get(srcLang).containsKey(trgLang)){
            Long counter = translations.get(srcLang).get(trgLang);
            counter++;
            translations.get(srcLang).put(trgLang,counter);
            return translations;
        }
        if(translations.containsKey(srcLang) && !translations.get(srcLang).containsKey(trgLang)){
            translations.get(srcLang).put(trgLang,1L);
            return translations;
        }

        Map<Languages, Long> map = new HashMap<>();
        map.put(trgLang,1L);
        translations.put(srcLang, new HashMap<>(map));

        return translations;
    }

    public void addTranslationWithText(TranslationWithText translationWithText, Client client) {
        Queue<TranslationWithText> translationsWithText = client.getTranslationsWithText();

        if(translationsWithText.size() == 5){
            translationsWithText.remove();
            translationsWithText.offer(translationWithText);
            return;
        }

        translationsWithText.offer(translationWithText);
    }
}
