package mindswap.academy.TranslatorApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import mindswap.academy.TranslatorApi.Commands.CreateClientDto;
import mindswap.academy.TranslatorApi.service.register.RegisterServiceImpl;
import mindswap.academy.TranslatorApi.utils.Verifiers;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.*;


@RestController
@RequestMapping("/register")
public class RegisterController {

    private final RegisterServiceImpl registerServiceImpl;

    public RegisterController(RegisterServiceImpl registerServiceImpl){
        this.registerServiceImpl = registerServiceImpl;
    }

    @Operation(summary = SUMMARY_EIGHT, description = DESCRIPTION_SEVEN)
    @ApiResponse(responseCode = "200", content = @Content(schema =@Schema(implementation = CreateClientDto.class)))
    @PostMapping
    public ResponseEntity<?> clientRegister(@Valid @RequestBody CreateClientDto createClientDto, BindingResult result){

        if(result.hasErrors()){
            return new ResponseEntity<>(Verifiers.getErrors(result), HttpStatus.BAD_REQUEST);
        }

        boolean hasCreated = registerServiceImpl.addClient(createClientDto);

        if(hasCreated){
            return new ResponseEntity<>(HttpStatus.CREATED);
        }

        return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
    }



}
