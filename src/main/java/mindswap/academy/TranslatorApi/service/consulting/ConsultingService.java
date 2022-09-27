package mindswap.academy.TranslatorApi.service.consulting;

import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;
import java.util.Queue;

public interface ConsultingService {
    Map<Languages, Long> getAllSrcLanguages();

    Map<Languages, Long> getAllTrgLanguages();

    Map<Languages, Long> getSrcLangClientTranslations(HttpServletRequest request);

    Map<Languages, Long> getTrgLangClientTranslations(HttpServletRequest request);

    Queue<TranslationWithText> getLastTranslations(HttpServletRequest request);
}
