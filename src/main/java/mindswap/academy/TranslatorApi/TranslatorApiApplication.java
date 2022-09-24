package mindswap.academy.TranslatorApi;

import mindswap.academy.TranslatorApi.Models.Client;
import mindswap.academy.TranslatorApi.Models.TranslationWithText;
import mindswap.academy.TranslatorApi.service.ClientService;
import mindswap.academy.TranslatorApi.utils.enums.Languages;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import javax.annotation.PostConstruct;
import java.util.LinkedList;

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
		clientService.addClient(new Client(1L,"Henrique","zpatins", "1234", "asda"));
		clientService.getClientById(1L).getTranslationsWithText().add(new TranslationWithText(Languages.PT, Languages.EN, "Hello World", clientService.getClientById(1L)));
	}

}
