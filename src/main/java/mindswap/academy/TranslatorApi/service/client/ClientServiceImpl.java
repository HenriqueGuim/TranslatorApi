package mindswap.academy.TranslatorApi.service.client;

import lombok.extern.slf4j.Slf4j;
import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import mindswap.academy.TranslatorApi.service.role.RoleServiceImpl;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.*;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.NOT_FOUND;
import static mindswap.academy.TranslatorApi.utils.UtilStrings.ROLE_UPDATED;

@Service
@Slf4j
public class ClientServiceImpl implements ClientService {


    private final ClientRepositoryJpa clientRepository;
    private final RoleServiceImpl roleServiceImpl;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientServiceImpl(ClientRepositoryJpa clientRepository, RoleServiceImpl roleServiceImpl, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.roleServiceImpl = roleServiceImpl;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    @Override
    public boolean addClient(Client client) {
        if (clientRepository.findByUsername(client.getUsername()) != null) {
            log.error("Client already exists");
            return false;
        }

        client.setRole(roleServiceImpl.get("ROLE_FREE"));
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        Client client1 =clientRepository.save(client);
        roleServiceImpl.save(client1.getRole());
        log.info("Client added");
        return true;
    }


    @Override
    public Client getClientByUsername(String username) {

        log.info("Client found with username: " + username);
        return clientRepository.findByUsername(username);
    }


    @Override
    public List<Client> getAllClients() {

        log.info("All clients found");
        return clientRepository.findAll();
    }

    @Override
    public Client getClientById(Long id) {
        return clientRepository.findAll().stream().filter(client -> client.getId().equals(id)).findFirst().orElse(null);

    }

    @Override
    public HashMap<Languages, HashMap<Languages,Long>> addTranslation(Languages srcLang, Languages trgLang, Client client) {
        HashMap<Languages, HashMap<Languages, Long>> translations = client.getTranslations();

        if(translations.containsKey(srcLang) && translations.get(srcLang).containsKey(trgLang)){
            Long counter = translations.get(srcLang).get(trgLang);
            counter++;
            translations.get(srcLang).put(trgLang,counter);
            client.setTranslations(translations);
            clientRepository.save(client);
            log.info("Translation added to language: " + srcLang + " to language: " + trgLang);
            return translations;
        }
        if(translations.containsKey(srcLang) && !translations.get(srcLang).containsKey(trgLang)){
            translations.get(srcLang).put(trgLang,1L);
            client.setTranslations(translations);
            clientRepository.save(client);
            log.info("Translation added to language: " + srcLang + " to language: " + trgLang);

            return translations;
        }

        HashMap<Languages, Long> map = new HashMap<>();
        map.put(trgLang,1L);
        translations.put(srcLang, new HashMap<>(map));
        client.setTranslations(translations);
        clientRepository.save(client);
        Map<Languages, HashMap<Languages, Long>> map1 = clientRepository.findByUsername(client.getUsername()).getTranslations();
        client.getTranslationsWithText();
        log.info("Translation added to language: " + srcLang + " to language: " + trgLang);

        return translations;
    }

    @Override
    public void addTranslationWithText(TranslationWithText translationWithText, Client client) {
        LinkedList<TranslationWithText> translationsWithText = client.getTranslationsWithText();

        if(translationsWithText.size() == 5){
            translationsWithText.remove();
            translationsWithText.offer(translationWithText);
            client.setTranslationsWithText(translationsWithText);
            clientRepository.save(client);
            log.info("Translation with text added to client: " + client.getUsername());
            return;
        }

        translationsWithText.offer(translationWithText);
        client.setTranslationsWithText(translationsWithText);
        clientRepository.save(client);
        log.info("Translation with text added to client: " + client.getUsername());
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException(NOT_FOUND);
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(client.getRole().getTypeRole()));

        return new User(client.getUsername(), client.getPassword(), authorities);
    }


    @Override
    public String updateRole(String username, Long role) {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {

            log.error("Client not found");
            return NOT_FOUND;
        }
        client.setRole(roleServiceImpl.getById(role));
        clientRepository.save(client);

        log.info("Role updated");
        return ROLE_UPDATED;
    }
}
