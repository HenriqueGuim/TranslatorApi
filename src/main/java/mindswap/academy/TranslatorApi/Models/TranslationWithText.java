package mindswap.academy.TranslatorApi.Models;

import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TranslationWithText {

    private Languages sourceLanguage;
    private Languages finalLanguage;
    private String text;
    private Client client;
}
