package mindswap.academy.TranslatorApi.service;

import mindswap.academy.TranslatorApi.Models.Role;
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
        List<Role> roleList = roleRepository.findAll();

        return roleList.stream().filter(role1 -> role1.getTypeRole().equals(role)).findFirst().orElse(null);
    }

    public Role getById(Long id) {
        List<Role> roleList = roleRepository.findAll();

        return roleList.stream().filter(role1 -> role1.getId().equals(id)).findFirst().orElse(null);
    }
    public void createRole(Role role){
        roleRepository.save(role);

    }

    public void save(Role role) {
        roleRepository.save(role);
    }
}
