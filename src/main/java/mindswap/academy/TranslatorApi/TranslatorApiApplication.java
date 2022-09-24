package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.service.ClientService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;

@SpringBootApplication
public class TranslatorApiApplication {

	private final ClientService clientService;

	public TranslatorApiApplication(ClientService clientService) {
		this.clientService = clientService;
	}


	public static void main(String[] args) {
		SpringApplication.run(TranslatorApiApplication.class, args);
	}

	@PostConstruct
	public void addClient(){
		clientService.addClient(new Client("Henrique","zpatins", "1234", "asda"));
	}

}
