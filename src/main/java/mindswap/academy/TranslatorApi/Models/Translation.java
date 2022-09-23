package mindswap.academy.TranslatorApi.Models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Translation {

    private Long id;
    private String sourceLanguage;
    private String finalLanguage;
    private Client client;
}
