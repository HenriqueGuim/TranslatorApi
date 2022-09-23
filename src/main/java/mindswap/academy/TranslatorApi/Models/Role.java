package mindswap.academy.TranslatorApi.Models;

import lombok.*;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Role {

    private Long id;
    private String typeRole;
}
