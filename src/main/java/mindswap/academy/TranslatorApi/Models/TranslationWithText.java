package mindswap.academy.TranslatorApi.Models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class TranslationWithText {

    private Long id;
    private String sourceLanguage;
    private String finalLanguage;
    private String text;
    private Client client;
}
