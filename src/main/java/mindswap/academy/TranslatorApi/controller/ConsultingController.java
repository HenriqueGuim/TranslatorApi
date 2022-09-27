package mindswap.academy.TranslatorApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.service.consulting.ConsultingServiceImpl;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Queue;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.*;

@RestController
@RequestMapping("/consult")
public class ConsultingController {

    private final ConsultingServiceImpl consultingServiceImpl;

    public ConsultingController(ConsultingServiceImpl consultingServiceImpl) {
        this.consultingServiceImpl = consultingServiceImpl;
    }

    @Operation(summary = SUMMARY_ONE, description = DESCRIPTION_ONE)
    @GetMapping("/admin/SrcLanguage")
    @PreAuthorize(ADMIN)
    public ResponseEntity<?> getAllSrcLanguage() {
        Map<Languages,Long> map = consultingServiceImpl.getAllSrcLanguages();
        if (map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_TWO, description = DESCRIPTION_TWO)
    @GetMapping("/admin/TrgLanguage")
    @PreAuthorize(ADMIN)
    public ResponseEntity<?> getAllTrgLanguage() {
        Map<Languages,Long> map = consultingServiceImpl.getAllTrgLanguages();
        if (map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }


    @Operation(summary = SUMMARY_THREE, description = DESCRIPTION_THREE)
    @GetMapping("premium/SrcLanguage")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> getSrcLangClientTranslations(HttpServletRequest request) {
        Map<Languages,Long> map = consultingServiceImpl.getSrcLangClientTranslations(request);
        if(map != null) {
            return ResponseEntity.ok().body(map);
        }
        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_FOUR, description = DESCRIPTION_FOUR)
    @GetMapping("premium/TrgLanguage")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> getTrgLangClientTranslations(HttpServletRequest request) {
        Map<Languages,Long> map = consultingServiceImpl.getTrgLangClientTranslations(request);

        if(map != null) {
            return ResponseEntity.ok().body(map);
        }

        return ResponseEntity.badRequest().build();
    }

    @Operation(summary = SUMMARY_FIVE, description = DESCRIPTION_FIVE)
    @GetMapping("premium/lastTranslations")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> getLastTranslations(HttpServletRequest request) {
        Queue<TranslationWithText> queue = consultingServiceImpl.getLastTranslations(request);
        if(queue.size() !=0) {
            return ResponseEntity.ok().body(queue);
        }

        return ResponseEntity.noContent().build();
    }
}
