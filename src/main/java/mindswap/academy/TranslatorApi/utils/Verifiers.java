package mindswap.academy.TranslatorApi.utils;

import mindswap.academy.TranslatorApi.utils.enums.Languages;

import java.util.Arrays;

public class Verifiers {

    public static boolean isLanguageSupported(String language) {
        return Arrays.stream(Languages.values()).anyMatch(l -> l.getLanguageCode().equals(language));
    }

    public static String getLanguage(String language) {
        return Arrays.stream(Languages.values()).filter(l -> l.getLanguageCode().equals(language)).findFirst().get().getLanguage();
    }

}
