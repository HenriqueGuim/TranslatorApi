package mindswap.academy.TranslatorApi.Repository;

import mindswap.academy.TranslatorApi.Models.Role;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Repository
public class RoleRepository {

    private List<Role> roles = new ArrayList<>(Arrays.asList(
            new Role("FREE"),
            new Role("ADMIN"),
            new Role("PREMIUM")));

    public Role getRole(String role){
        return roles.stream().filter(role1 -> role1.getTypeRole().equals(role)).toList().get(0);
    }
}

