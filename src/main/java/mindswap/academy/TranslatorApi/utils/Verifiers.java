package mindswap.academy.TranslatorApi.utils;

import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class Verifiers {

    public static boolean isLanguageSupported(String language) {
        return Arrays.stream(Languages.values()).anyMatch(l -> l.getLanguageCode().equals(language));
    }

    public static String getLanguage(String language) {
        return Arrays.stream(Languages.values()).filter(l -> l.getLanguageCode().equals(language)).findFirst().get().getLanguage();
    }

    public static Map<String, String> getErrors(BindingResult result){
        Map<String, String> errors = new HashMap<>();
        result.getAllErrors().forEach(error -> errors.put(((FieldError)error).getField()  ,error.getDefaultMessage()));
        return errors;
    }

}
