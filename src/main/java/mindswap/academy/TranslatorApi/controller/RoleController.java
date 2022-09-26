package mindswap.academy.TranslatorApi.controller;

import com.fasterxml.jackson.databind.JsonNode;
import mindswap.academy.TranslatorApi.service.ClientService;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin/role")
public class RoleController {

    private final ClientService clientService;

    public RoleController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PutMapping("/{role}/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable String role, @PathVariable String username){



        return ResponseEntity.ok(clientService.updateRole(username, Long.parseLong(role)));
    }
}
