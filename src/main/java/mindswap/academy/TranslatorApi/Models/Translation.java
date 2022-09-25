package mindswap.academy.TranslatorApi.Models;

import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Translation {

    private Languages sourceLanguage;
    private Languages finalLanguage;
    private int count;
}
