package mindswap.academy.TranslatorApi.Models;

import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

import java.util.*;

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
    private Map<Languages, Map<Languages, Long>> translations = new HashMap<>();
    private Queue<TranslationWithText> translationsWithText = new LinkedList<>();

    public Client( String name, String username, String password, String email) {
        this.name = name;
        this.username = username;
        this.password = password;
        this.email=email;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Client client)) return false;
        return getUsername().equals(client.getUsername()) || getEmail().equals(client.getEmail());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getUsername(), getEmail());
    }

    public Client addRole(Role role) {
        if (roles == null){
            roles = new ArrayList<>();
        }
        roles.add(role);
        return this;
    }

    public Map<Languages, Map<Languages,Long>> addTranslation(Languages srcLang, Languages trgLang){

        if(translations.containsKey(srcLang) && translations.get(srcLang).containsKey(trgLang)){
            Long counter = translations.get(srcLang).get(trgLang);
            counter++;
            translations.get(srcLang).put(trgLang,counter);
            return translations;
        }
        if(translations.containsKey(srcLang) && !translations.get(srcLang).containsKey(trgLang)){
            translations.get(srcLang).put(trgLang,1L);
            return translations;
        }

        Map<Languages, Long> map = new HashMap<>();
        map.put(trgLang,1L);
        translations.put(srcLang, new HashMap<>(map));

        return translations;
    }

    public Queue<TranslationWithText> addTranslationWithText(TranslationWithText translationWithText){

        if(translationsWithText.size() == 5){
            translationsWithText.remove();
            translationsWithText.offer(translationWithText);
            return translationsWithText;
        }

        translationsWithText.offer(translationWithText);
        return translationsWithText;
    }
}
