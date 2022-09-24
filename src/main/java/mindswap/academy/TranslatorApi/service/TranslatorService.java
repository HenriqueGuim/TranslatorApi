package mindswap.academy.TranslatorApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.utils.Verifiers;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class TranslatorService {


    @Value("${jwt.deepl.address}")
    private String translatorApiAddress;
    @Value("${jwt.deepl.key}")
    private String translatorApiKey;
    private final RestTemplate restTemplate = new RestTemplate();

    private final ClientService clientService;

    public TranslatorService(ClientService clientService) {
        this.clientService = clientService;
    }


    public String getTranslator(String sourceLanguage, String trgLanguage, String text, String username) throws JsonProcessingException, URISyntaxException {
        if (!Verifiers.isLanguageSupported(trgLanguage)){
            return null;
        }

        String query = "?text=" + UriUtils.encode(text, UTF_8) + "&target_lang=" + trgLanguage;
        if (sourceLanguage != null){
            if (!Verifiers.isLanguageSupported(sourceLanguage)){
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

        Client client = clientService.getClientByUsername(username);

        Languages srcLanguage = Arrays.stream(Languages.values())
                                      .filter(lang -> lang.getLanguageCode().equals(language.asText())).findFirst().get();
        Languages targetLanguage = Arrays.stream(Languages.values())
                                      .filter(lang -> lang.getLanguageCode().equals(trgLanguage)).findFirst().get();

        client.addTranslation(srcLanguage, targetLanguage);

        client.addTranslationWithText(new TranslationWithText(srcLanguage,targetLanguage, text, client));

        return "Translated from " + Verifiers.getLanguage(language.asText()) + " to " + Verifiers.getLanguage(trgLanguage) + " : " + translation.asText();
    }
}
