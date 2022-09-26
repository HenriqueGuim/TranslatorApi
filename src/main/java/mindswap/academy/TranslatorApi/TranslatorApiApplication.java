package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.Role;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.Repository.ClientRepositoryJpa;
import mindswap.academy.TranslatorApi.service.ClientService;
import mindswap.academy.TranslatorApi.service.RoleService;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.hibernate.SessionFactory;
import org.hibernate.jpa.HibernateEntityManagerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

@SpringBootApplication
public class TranslatorApiApplication {

	//private final ClientService clientService;
	private final ClientRepositoryJpa clientRepository;
	private final RoleService roleService;

	public TranslatorApiApplication(ClientRepositoryJpa clientRepository, RoleService roleService) {
		//this.clientService = clientService;
		this.clientRepository = clientRepository;
		this.roleService = roleService;
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
