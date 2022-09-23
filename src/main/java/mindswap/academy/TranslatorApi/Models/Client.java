package mindswap.academy.TranslatorApi.Models;

import lombok.*;

import java.util.List;
import java.util.Queue;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Client {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private List<Role> roles;
    private List<Translation> translations;
    private Queue<TranslationWithText> translationWithTextQueue;
}
