package mindswap.academy.TranslatorApi.Commands;

import lombok.Builder;
import lombok.Data;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Null;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.*;

@Data
@Builder
public class CreateClientDto {

    @Null(message = NULL)
    private Long id;
    @NotBlank(message = BLANK)
    private String name;
    @NotBlank(message = BLANK)
    private String username;
    @NotBlank(message = BLANK)
    private String password;
    @Email(message = VALID)
    private String email;


}
