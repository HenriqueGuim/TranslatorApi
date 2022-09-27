package mindswap.academy.TranslatorApi.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mindswap.academy.TranslatorApi.service.translator.TranslatorServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.SUMMARY_SEVEN;

@RestController
@RequestMapping("/translator")
public class TranslatorController {
    private final TranslatorServiceImpl translatorServiceImpl;

    public TranslatorController(TranslatorServiceImpl translatorServiceImpl) {
        this.translatorServiceImpl = translatorServiceImpl;
    }

    @Operation(summary = SUMMARY_SEVEN, description = SUMMARY_SEVEN)
    @ApiResponse(responseCode = "200")
    @PostMapping()
    public ResponseEntity<?> getTranslator(@RequestParam(name = "text") String text, @RequestParam(name = "src_lang", required = false) String sourceLanguage, @RequestParam(name = "trg_lang") String languageToTranslate, HttpServletRequest request) throws JsonProcessingException, URISyntaxException {
        String translation = translatorServiceImpl.getTranslator(sourceLanguage, languageToTranslate, text, request);
        if (translation == null){
            Map<String, String> response = new HashMap<>();
            response.put("error", "Language not supported");

            return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
        }

        return ResponseEntity.ok(translation);
    }
}
