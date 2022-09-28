package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Models.Role;
import mindswap.academy.TranslatorApi.service.role.RoleService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TranslatorApiApplication {
	;
	private final RoleService roleService;

	public TranslatorApiApplication(RoleService roleService) {
		this.roleService = roleService;
	}


	public static void main(String[] args) {
		SpringApplication.run(TranslatorApiApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}



	@PostConstruct
	public void addRoles(){
		if (roleService.getById(1L) == null){
		roleService.createRole(new Role("ROLE_FREE"));
		roleService.createRole(new Role("ROLE_PREMIUM"));
		roleService.createRole(new Role("ROLE_ADMIN"));
		}
	}



}
