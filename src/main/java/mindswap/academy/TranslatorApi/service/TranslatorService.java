package mindswap.academy.TranslatorApi.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.swagger.v3.oas.models.parameters.QueryParameter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.DefaultUriBuilderFactory;
import org.springframework.web.util.UriBuilder;
import org.springframework.web.util.UriComponentsBuilder;
import org.springframework.web.util.UriUtils;

import java.net.URI;
import java.net.URISyntaxException;
import java.nio.charset.StandardCharsets;

import static java.nio.charset.StandardCharsets.UTF_8;

@Service
public class TranslatorService {


    private final String translatorApiAddress = "https://api-free.deepl.com/v2/translate";
    private final String translatorApiKey = "0914e154-53d3-a0b8-1587-8152fd90b0b5:fx";
    private final String SCHEME = "https";
    private final String AUTHORITY = "api-free.deepl.com";
    private final String PATH = "/v2/translate";
    private final RestTemplate restTemplate = new RestTemplate();



    public String getTranslator(String sourceLanguage, String languageToTranslate, String text) throws JsonProcessingException, URISyntaxException {
        String query = "?text=" + UriUtils.encode(text, UTF_8) + "&target_lang=" + languageToTranslate;
        if (sourceLanguage != null)query = "?text=" + UriUtils.encode(text, UTF_8) + "&target_lang=" + languageToTranslate + "&source_lang=" + sourceLanguage;
        URI uri = new URI(translatorApiAddress + query);


        HttpHeaders headers = new HttpHeaders();
        headers.add("Authorization", "DeepL-Auth-Key " + translatorApiKey);

        HttpEntity<String> entity = new HttpEntity<String>("body", headers);


        ResponseEntity<String> response = restTemplate.exchange(uri, HttpMethod.POST, entity, String.class);
        ObjectMapper mapper = new ObjectMapper();
        JsonNode root = mapper.readTree(response.getBody());
        JsonNode translation = root.path("translations").get(0).path("text");
        JsonNode language = root.path("translations").get(0).path("detected_source_language");


        return "Translated from " + language + " to " + languageToTranslate + " : " + translation.toString();
    }
}
