package mindswap.academy.TranslatorApi.controller;

import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.service.ConsultingService;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Queue;

@RestController
@RequestMapping("/consult")
public class ConsultingController {

    private final ConsultingService consultingService;

    public ConsultingController(ConsultingService consultingService) {
        this.consultingService = consultingService;
    }


    @GetMapping("/admin/SrcLanguage")
    public ResponseEntity<?> getAllSrcLanguage() {
        Map<Languages,Long> map = consultingService.getAllSrcLanguages();
        if (map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("/admin/TrgLanguage")
    public ResponseEntity<?> getAllTrgLanguage() {
        Map<Languages,Long> map = consultingService.getAllTrgLanguages();
        if (map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @GetMapping("premium/{id}/SrcLanguage")
    public ResponseEntity<?> getSrcLangClientTranslations(@PathVariable(name = "id") Long id) {
        Map<Languages,Long> map = consultingService.getSrcLangClientTranslations(id);
        if(map != null) {
            return ResponseEntity.ok().body(map);
        }
        return ResponseEntity.badRequest().build();
    }

    @GetMapping("premium/{id}/TrgLanguage")
    public ResponseEntity<?> getTrgLangClientTranslations(@PathVariable(name = "id") Long id) {
        Map<Languages,Long> map = consultingService.getTrgLangClientTranslations(id);

        if(map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }
    @GetMapping("premium/{id}/lastTranslations")
    public ResponseEntity<?> getLastTranslations(@PathVariable(name = "id") Long id) {
        Queue<TranslationWithText> queue = consultingService.getLastTranslations(id);
        if(queue.size() !=0) {
            return ResponseEntity.ok().body(queue);
        }

        return ResponseEntity.noContent().build();
    }
}
