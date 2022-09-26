package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ClientService implements UserDetailsService {


    private final ClientRepositoryJpa clientRepository;
    private final RoleService roleService;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    public ClientService(ClientRepositoryJpa clientRepository, RoleService roleService, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.clientRepository = clientRepository;
        this.roleService = roleService;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }


    public boolean addClient(Client client) {
        if (clientRepository.findByUsername(client.getUsername()) != null) {
            return false;
        }

        client.setRole(roleService.get("ROLE_FREE"));
        client.setPassword(bCryptPasswordEncoder.encode(client.getPassword()));
        Client client1 =clientRepository.save(client);
        roleService.save(client1.getRole());
        return true;
    }


    public Client getClientByUsername(String username) {
        return clientRepository.findByUsername(username);
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
        Map<Languages, HashMap<Languages, Long>> map1 = clientRepository.findByUsername(client.getUsername()).getTranslations();
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

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            throw new UsernameNotFoundException("User not found");
        }

        Collection<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(client.getRole().getTypeRole()));

        return new User(client.getUsername(), client.getPassword(), authorities);
    }


    public Object updateRole(String username, Long role) {
        Client client = clientRepository.findByUsername(username);
        if (client == null) {
            return "User not found";
        }
        client.setRole(roleService.getById(role));
        clientRepository.save(client);
        return "Role updated";
    }
}
