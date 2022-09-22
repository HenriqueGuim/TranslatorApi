package mindswap.academy.TranslatorApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import mindswap.academy.TranslatorApi.service.TranslatorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/translator")
public class TranslatorController {
    private final TranslatorService translatorService;

    public TranslatorController(TranslatorService translatorService) {
        this.translatorService = translatorService;
    }

    @GetMapping("/{text}")
    public String getTranslator(@PathVariable String text) throws JsonProcessingException, URISyntaxException {
        return translatorService.getTranslator("PT", "EN", text);
    }
}
