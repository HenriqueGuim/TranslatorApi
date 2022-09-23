package mindswap.academy.TranslatorApi.controller;

import mindswap.academy.TranslatorApi.Commands.CreateClientDto;
import mindswap.academy.TranslatorApi.service.RegisterService;
import mindswap.academy.TranslatorApi.utils.Verifiers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterService registerService;

    public RegisterController(RegisterService registerService){
        this.registerService = registerService;
    }

    @PostMapping
    public ResponseEntity<?> clientRegister(@Valid @RequestBody CreateClientDto createClientDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(Verifiers.getErrors(result), HttpStatus.BAD_REQUEST);
        }

        boolean hasCreated = registerService.addClient(createClientDto);

        if(hasCreated){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }



}
