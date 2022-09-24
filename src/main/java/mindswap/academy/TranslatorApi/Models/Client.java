package mindswap.academy.TranslatorApi.Models;

import lombok.*;
import mindswap.academy.TranslatorApi.utils.enums.Languages;

import java.util.*;

@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Client {

    private Long id;
    private String name;
    private String username;
    private String password;
    private String email;
    private List<Role> roles = new ArrayList<>();
    private Map<Languages, Map<Languages, Long>> translations = new HashMap<>();
    private Queue<TranslationWithText> translationsWithText = new LinkedList<>();

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

    public Client addRole(Role role) {
        roles.add(role);
        return this;
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
}
