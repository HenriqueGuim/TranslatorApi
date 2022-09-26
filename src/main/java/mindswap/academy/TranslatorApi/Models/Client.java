package mindswap.academy.TranslatorApi.Models;

import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.apache.commons.lang3.SerializationUtils;

import javax.persistence.*;
import java.util.*;


@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    @ManyToOne
    private Role role;
    @Column(columnDefinition = "longblob")
    private byte[] translations = SerializationUtils.serialize(new HashMap<Languages,HashMap<Languages,Long>>());
    //private Map<Languages, Map<Languages, Long>> translations = new HashMap<>();
    //private Queue<TranslationWithText> translationsWithText = new LinkedList<>();
    @Column(columnDefinition = "longblob")
    private byte[] translationsWithText = SerializationUtils.serialize(new LinkedList<TranslationWithText>());

    public Client(Long id, String name, String username, String password, String email) {
        this.id = id;
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


    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", username='" + username + '\'' +
                ", email='" + email + '\'' +
                '}';
    }

    public HashMap<Languages,HashMap<Languages,Long>> getTranslations() {
        return SerializationUtils.deserialize(translations);
    }

    public void setTranslations(HashMap<Languages,HashMap<Languages,Long>> translations) {
        this.translations = SerializationUtils.serialize(translations);

    }

    public LinkedList<TranslationWithText> getTranslationsWithText() {

        return SerializationUtils.deserialize(translationsWithText);
    }

    public void setTranslationsWithText(LinkedList<TranslationWithText> translationsWithText) {
        this.translationsWithText = SerializationUtils.serialize(translationsWithText);
    }
}
