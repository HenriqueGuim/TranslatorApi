package mindswap.academy.TranslatorApi.service.role;

import mindswap.academy.TranslatorApi.Models.Role;

public interface RoleService {
    Role get(String role);

    Role getById(Long id);

    void createRole(Role role);

    void save(Role role);
}
