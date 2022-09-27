package mindswap.academy.TranslatorApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import mindswap.academy.TranslatorApi.service.client.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.*;

@RestController
@RequestMapping("/Admin/role")
public class RoleController {

    private final ClientServiceImpl clientServiceImpl;

    public RoleController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @Operation(summary = SUMMARY_NINE, description = DESCRIPTION_NINE)
    @PutMapping("/{role}/username/{username}")
    @PreAuthorize(ADMIN_PREMIUM)
    public ResponseEntity<?> updateRole(@PathVariable String role, @PathVariable String username){
        return ResponseEntity.ok(clientServiceImpl.updateRole(username, Long.parseLong(role)));
    }
}
