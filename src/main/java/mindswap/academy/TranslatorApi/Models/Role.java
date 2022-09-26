package mindswap.academy.TranslatorApi.Models;

import lombok.*;

import javax.persistence.*;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Entity
public class Role {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String typeRole;
    @OneToMany(mappedBy = "role")
    private List<Client> clients;

    public Role(String typeRole) {
        this.typeRole = typeRole;
    }
}
