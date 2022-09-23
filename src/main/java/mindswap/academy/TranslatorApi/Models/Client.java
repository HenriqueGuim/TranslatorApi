package mindswap.academy.TranslatorApi.Models;

import lombok.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
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
}
