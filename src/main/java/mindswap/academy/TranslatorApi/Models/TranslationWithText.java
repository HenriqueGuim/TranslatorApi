package mindswap.academy.TranslatorApi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationWithText {

    private Languages sourceLanguage;
    private Languages finalLanguage;
    private String text;
    @JsonIgnore
    private Client client;
}
