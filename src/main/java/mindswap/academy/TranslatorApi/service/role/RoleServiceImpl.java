package mindswap.academy.TranslatorApi.service.role;

import mindswap.academy.TranslatorApi.Models.Role;
import mindswap.academy.TranslatorApi.Repository.RoleRepositoryJpa;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    private final RoleRepositoryJpa roleRepository;

    public RoleServiceImpl(RoleRepositoryJpa roleRepository) {
        this.roleRepository = roleRepository;
    }


    @Override
    public Role get(String role) {
        List<Role> roleList = roleRepository.findAll();

        return roleList.stream().filter(role1 -> role1.getTypeRole().equals(role)).findFirst().orElse(null);
    }

    @Override
    public Role getById(Long id) {
        List<Role> roleList = roleRepository.findAll();

        return roleList.stream().filter(role1 -> role1.getId().equals(id)).findFirst().orElse(null);
    }
    @Override
    public void createRole(Role role){
        roleRepository.save(role);

    }

    @Override
    public void save(Role role) {
        roleRepository.save(role);
    }
}
