package mindswap.academy.TranslatorApi.service.translator;

import com.auth0.jwt.JWT;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.service.client.ClientServiceImpl;
import mindswap.academy.TranslatorApi.utils.Verifiers;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.concurrent.ExecutorService;

import static java.nio.charset.StandardCharsets.UTF_8;
import static java.util.concurrent.Executors.newCachedThreadPool;

@Service
@Slf4j
public class TranslatorServiceImpl implements TranslatorService {


    @Value("${jwt.deepl.address}")
    private String translatorApiAddress;
    @Value("${jwt.deepl.key}")
    private String translatorApiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    private final ClientServiceImpl clientServiceImpl;
    private ExecutorService executorService = newCachedThreadPool();

    public TranslatorServiceImpl(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }


    @Override
    public String getTranslator(String sourceLanguage, String trgLanguage, String text, HttpServletRequest request) throws JsonProcessingException, URISyntaxException {
        if (!Verifiers.isLanguageSupported(trgLanguage)){

            log.error("Language not supported");
            return null;
        }

        String query = "?text=" + UriUtils.encode(text, UTF_8) + "&target_lang=" + trgLanguage;
        if (sourceLanguage != null){
            if (!Verifiers.isLanguageSupported(sourceLanguage)){
                log.error("Language not supported");
                return null;
            }
            query = "?text=" + UriUtils.encode(text, UTF_8) + "&target_lang=" + trgLanguage + "&source_lang=" + sourceLanguage;
        }
        URI uri = new URI(translatorApiAddress + query);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DeepL-Auth-Key " + translatorApiKey);

        HttpEntity<String> entity = new HttpEntity<String>("body", headers);

        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);

        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode translation = root.path("translations").get(0).path("text");
        JsonNode language = root.path("translations").get(0).path("detected_source_language");

        DecodedJWT jwt = JWT.decode(request.getHeader("Authorization").substring(7));
        String usernameFromToken = jwt.getSubject();
        Client client = clientServiceImpl.getClientByUsername(usernameFromToken);

        executorService.submit(new SaveTranslation(client, language.asText(), trgLanguage, translation.asText()));

        log.info("Translation: " + translation.asText());

        return "Translated from " + Verifiers.getLanguage(language.asText()) + " to " + Verifiers.getLanguage(trgLanguage) + " : " + translation.asText();
    }

    public class SaveTranslation implements Runnable {
        private final Client client;
        private final String sourceLanguage;
        private final String finalLanguage;
        private final String text;

        public SaveTranslation(Client client, String sourceLanguage, String finalLanguage, String text) {
            this.client = client;
            this.sourceLanguage = sourceLanguage;
            this.finalLanguage = finalLanguage;
            this.text = text;
        }

        @Override
        public void run() {
            try {
                saveTranslations(client,sourceLanguage,finalLanguage,text);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }

        private void saveTranslations(Client client, String sourceLanguage, String trgLanguage, String translation) throws InterruptedException {

            Languages srcLanguage = Arrays.stream(Languages.values())
                    .filter(lang -> lang.getLanguageCode().equals(sourceLanguage)).findFirst().get();
            Languages targetLanguage = Arrays.stream(Languages.values())
                    .filter(lang -> lang.getLanguageCode().equals(trgLanguage)).findFirst().get();

            clientServiceImpl.addTranslation(srcLanguage, targetLanguage, client);

            clientServiceImpl.addTranslationWithText(new TranslationWithText(srcLanguage,targetLanguage, translation), client);

        }

    }
}
