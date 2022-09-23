package mindswap.academy.TranslatorApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import mindswap.academy.TranslatorApi.service.TranslatorService;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMapAdapter;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/translator")
public class TranslatorController {
    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @PostMapping()
    public ResponseEntity<?> getTranslator(@RequestParam(name = "text") String text, @RequestParam(name = "src_lang", required = false) String sourceLanguage, @RequestParam(name = "trg_lang") String languageToTranslate) throws JsonProcessingException, URISyntaxException {
        String translation = translatorService.getTranslator(sourceLanguage, languageToTranslate, text);
        if (translation == null){
            String message = "Language not supported";
            Map<String, String> response = new HashMap<>();
            response.put("error", "Language not supported");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(translation);
    }
}
