package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import mindswap.academy.TranslatorApi.service.role.RoleServiceImpl;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@SpringBootApplication
public class TranslatorApiApplication {

	//private final ClientService clientService;
	private final ClientRepositoryJpa clientRepository;
	private final RoleServiceImpl roleServiceImpl;

	public TranslatorApiApplication(ClientRepositoryJpa clientRepository, RoleServiceImpl roleServiceImpl) {
		//this.clientService = clientService;
		this.clientRepository = clientRepository;
		this.roleServiceImpl = roleServiceImpl;
	}


	public static void main(String[] args) {
		SpringApplication.run(TranslatorApiApplication.class, args);
	}

	@Bean
	BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}


	/*
	@PostConstruct
	public void addClient(){
		roleService.createRole(new Role("ROLE_FREE"));
		roleService.createRole(new Role("ROLE_PREMIUM"));
		roleService.createRole(new Role("ROLE_ADMIN"));

		//clientService.addClient(new Client(1L,"Henrique","zpatins", "1234", "asda"));
	}
	 */

}
