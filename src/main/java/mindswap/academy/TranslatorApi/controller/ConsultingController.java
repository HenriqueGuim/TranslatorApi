package mindswap.academy.TranslatorApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.service.ConsultingService;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Queue;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.*;

@RestController
@RequestMapping("/consult")
public class ConsultingController {

    private final ConsultingService consultingService;

    public ConsultingController(ConsultingService consultingService) {
        this.consultingService = consultingService;
    }

    @Operation(summary = SUMMARY_ONE, description = DESCRIPTION_ONE)
    @GetMapping("/admin/SrcLanguage")
    @PreAuthorize(ADMIN)
    public ResponseEntity<?> getAllSrcLanguage() {
        Map<Languages,Long> map = consultingService.getAllSrcLanguages();
        if (map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_TWO, description = DESCRIPTION_TWO)
    @GetMapping("/admin/TrgLanguage")
    @PreAuthorize(ADMIN)
    public ResponseEntity<?> getAllTrgLanguage() {
        Map<Languages,Long> map = consultingService.getAllTrgLanguages();
        if (map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_THREE, description = DESCRIPTION_THREE)
    @GetMapping("premium/{id}/SrcLanguage")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> getSrcLangClientTranslations(@PathVariable(name = "id") Long id) {
        Map<Languages,Long> map = consultingService.getSrcLangClientTranslations(id);
        if(map != null) {
            return ResponseEntity.ok().body(map);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_FOUR, description = DESCRIPTION_FOUR)
    @GetMapping("premium/{id}/TrgLanguage")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> getTrgLangClientTranslations(@PathVariable(name = "id") Long id) {
        Map<Languages,Long> map = consultingService.getTrgLangClientTranslations(id);

        if(map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_FIVE, description = DESCRIPTION_FIVE)
    @GetMapping("premium/{id}/lastTranslations")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> getLastTranslations(@PathVariable(name = "id") Long id) {
        Queue<TranslationWithText> queue = consultingService.getLastTranslations(id);
        if(queue.size() !=0) {
            return ResponseEntity.ok().body(queue);
        }

        return ResponseEntity.noContent().build();
    }
}
