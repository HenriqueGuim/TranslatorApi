package mindswap.academy.TranslatorApi.controller;

import mindswap.academy.TranslatorApi.service.client.ClientServiceImpl;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/Admin/role")
public class RoleController {

    private final ClientServiceImpl clientServiceImpl;

    public RoleController(ClientServiceImpl clientServiceImpl) {
        this.clientServiceImpl = clientServiceImpl;
    }

    @PutMapping("/{role}/username/{username}")
    @PreAuthorize("hasRole('ROLE_ADMIN')")
    public ResponseEntity<?> updateRole(@PathVariable String role, @PathVariable String username){



        return ResponseEntity.ok(clientServiceImpl.updateRole(username, Long.parseLong(role)));
    }
}
