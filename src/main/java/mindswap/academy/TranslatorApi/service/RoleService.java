package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Role;
import mindswap.academy.TranslatorApi.Repository.RoleRepositoryJpa;
import org.springframework.stereotype.Service;

@Service
public class RoleService {

    private final RoleRepositoryJpa roleRepositoryJpa;

    public RoleService(RoleRepositoryJpa roleRepositoryJpa) {
        this.roleRepositoryJpa = roleRepositoryJpa;
    }


    public Role get(String role) {
        return roleRepositoryJpa.getRole(role);
    }
}
