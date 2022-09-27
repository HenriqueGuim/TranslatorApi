package mindswap.academy.TranslatorApi.service.client;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.HashMap;
import java.util.List;

public interface ClientService extends UserDetailsService {
    boolean addClient(Client client);

    Client getClientByUsername(String username);

    List<Client> getAllClients();

    Client getClientById(Long id);

    HashMap<Languages, HashMap<Languages, Long>> addTranslation(Languages srcLang, Languages trgLang, Client client);

    void addTranslationWithText(TranslationWithText translationWithText, Client client);

    @Override
    UserDetails loadUserByUsername(String username) throws UsernameNotFoundException;

    String updateRole(String username, Long role);
}
