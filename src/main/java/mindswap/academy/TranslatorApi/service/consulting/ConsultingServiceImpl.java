package mindswap.academy.TranslatorApi.service.consulting;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.service.client.ClientServiceImpl;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Service
public class ConsultingServiceImpl implements ConsultingService {
    private final ClientServiceImpl clientServiceImpl;

    public ConsultingServiceImpl(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }


    @Override
    public Map<Languages,Long> getAllSrcLanguages() {
        List<Client> clientList = clientServiceImpl.getAllClients();
        Map<Languages, Long> srcLanguages = new HashMap<>();
        Arrays.stream(Languages.values()).forEach(languages -> srcLanguages.put(languages, 0L));

        clientList.forEach(client ->{
            client.getTranslations().forEach((language, languageMap)->{
                long counter = 0;
                for (Map.Entry<Languages, Long> entry : languageMap.entrySet()) {
                    counter += entry.getValue();
                }
                srcLanguages.put(language, srcLanguages.get(language) + counter);
            });

        });

        return srcLanguages;
    }

    @Override
    public Map<Languages, Long> getAllTrgLanguages() {
        List<Client> clientList = clientServiceImpl.getAllClients();
        Map<Languages, Long> trgLanguages = new HashMap<>();
        Arrays.stream(Languages.values()).forEach(languages -> trgLanguages.put(languages, 0L));

        clientList.forEach(client ->{
            client.getTranslations().forEach((language, languageMap)->{
                languageMap.forEach((language1, aLong) -> {
                    trgLanguages.put(language1, trgLanguages.get(language1) + aLong);
                });
            });
        });
        return trgLanguages;
    }

    @Override
    public Map<Languages, Long> getSrcLangClientTranslations(HttpServletRequest request) {
        DecodedJWT jwt = JWT.decode(request.getHeader("Authorization").substring(7));
        String usernameFromToken = jwt.getSubject();
        Client client = clientServiceImpl.getClientByUsername(usernameFromToken);

        if (client == null) {
            return null;
        }
        Map<Languages, Long> map = new HashMap<>();
        Arrays.stream(Languages.values()).forEach(languages -> map.put(languages, 0L));

        client.getTranslations().forEach((language, languageMap)->{
            AtomicReference<Long> counter = new AtomicReference<>(0L);
            languageMap.forEach((language1, aLong) -> {
                counter.updateAndGet(v -> v + aLong);
            });
            map.put(language, Long.valueOf(String.valueOf(counter)));
        });


        return map;
    }

    @Override
    public Map<Languages, Long> getTrgLangClientTranslations(HttpServletRequest request) {
        DecodedJWT jwt = JWT.decode(request.getHeader("Authorization").substring(7));
        String usernameFromToken = jwt.getSubject();
        Client client = clientServiceImpl.getClientByUsername(usernameFromToken);

        if (client == null) {
            return null;
        }
        Map<Languages, Long> map = new HashMap<>();
        Arrays.stream(Languages.values()).forEach(languages -> map.put(languages, 0L));

        client.getTranslations().forEach((language, languageMap)->{
            map.putAll(languageMap);
        });
        return map;
    }

    @Override
    public Queue<TranslationWithText> getLastTranslations(HttpServletRequest request) {
        DecodedJWT jwt = JWT.decode(request.getHeader("Authorization").substring(7));
        String usernameFromToken = jwt.getSubject();
        Client client = clientServiceImpl.getClientByUsername(usernameFromToken);

        return client.getTranslationsWithText();
    }
}
