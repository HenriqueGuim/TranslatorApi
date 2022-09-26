package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class ClientService {


    private final ClientRepositoryJpa clientRepository;
    private final RoleService roleService;

    public ClientService(ClientRepositoryJpa clientRepository, RoleService roleService) {
        this.clientRepository = clientRepository;
        this.roleService = roleService;
    }


    public boolean addClient(Client client) {

        client.setRole(roleService.get("ROLE_FREE"));
        Client client1 =clientRepository.save(client);
        roleService.save(client1.getRole());
        return client1 != null;
    }


    public Client getClientByUsername(String username) {
        return clientRepository.getClientByUsername(username);
    }


    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client getClientById(Long id) {
        return clientRepository.findAll().stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);

    }

    public HashMap<Languages, HashMap<Languages,Long>> addTranslation(Languages srcLang, Languages trgLang, Client client) {
        HashMap<Languages, HashMap<Languages, Long>> translations = client.getTranslations();

        if(translations.containsKey(srcLang) && translations.get(srcLang).containsKey(trgLang)){
            Long counter = translations.get(srcLang).get(trgLang);
            counter++;
            translations.get(srcLang).put(trgLang,counter);
            client.setTranslations(translations);
            clientRepository.save(client);
            return translations;
        }
        if(translations.containsKey(srcLang) && !translations.get(srcLang).containsKey(trgLang)){
            translations.get(srcLang).put(trgLang,1L);
            client.setTranslations(translations);
            clientRepository.save(client);

            return translations;
        }

        HashMap<Languages, Long> map = new HashMap<>();
        map.put(trgLang,1L);
        translations.put(srcLang, new HashMap<>(map));
        client.setTranslations(translations);
        clientRepository.save(client);
        Map<Languages, HashMap<Languages, Long>> map1 = clientRepository.getClientByUsername(client.getUsername()).getTranslations();
        client.getTranslationsWithText();

        return translations;
    }

    public void addTranslationWithText(TranslationWithText translationWithText, Client client) {
        LinkedList<TranslationWithText> translationsWithText = client.getTranslationsWithText();

        if(translationsWithText.size() == 5){
            translationsWithText.remove();
            translationsWithText.offer(translationWithText);
            client.setTranslationsWithText(translationsWithText);
            clientRepository.save(client);
            return;
        }

        translationsWithText.offer(translationWithText);
        client.setTranslationsWithText(translationsWithText);
        clientRepository.save(client);
    }
}
