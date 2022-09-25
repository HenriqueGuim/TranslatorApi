package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Role;
import mindswap.academy.TranslatorApi.Repository.RoleRepository;
import mindswap.academy.TranslatorApi.Repository.RoleRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepositoryJpa roleRepository;

    public RoleService(RoleRepositoryJpa roleRepository) {
        this.roleRepository = roleRepository;
    }


    public Role get(String role) {
        return roleRepository.findRoleByTypeRole(role);
    }
    public void createRole(Role role){
        roleRepository.save(role);
    }
}
