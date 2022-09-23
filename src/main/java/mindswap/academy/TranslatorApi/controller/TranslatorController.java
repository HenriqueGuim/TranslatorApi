package mindswap.academy.TranslatorApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import mindswap.academy.TranslatorApi.service.TranslatorService;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/translator")
public class TranslatorController {
    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @PostMapping()
    public String getTranslator(@RequestParam(name = "text") String text, @RequestParam(name = "src_lang", required = false) String sourceLanguage, @RequestParam(name = "trg_lang") String languageToTranslate) throws JsonProcessingException, URISyntaxException {
        return translatorService.getTranslator(sourceLanguage, languageToTranslate, text);
    }
}
