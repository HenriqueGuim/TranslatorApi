package mindswap.academy.TranslatorApi.service.translator;

import com.fasterxml.jackson.core.JsonProcessingException;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;

public interface TranslatorService {
    String getTranslator(String sourceLanguage, String trgLanguage, String text, HttpServletRequest request) throws JsonProcessingException, URISyntaxException;
}
