package mindswap.academy.TranslatorApi.Models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

import java.io.Serializable;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class TranslationWithText implements Serializable {

    private Languages sourceLanguage;
    private Languages finalLanguage;
    private String text;
}
