package mindswap.academy.TranslatorApi.controller;

import mindswap.academy.TranslatorApi.service.RefreshTokenService;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/refresh")
public class RefreshTokenController {
    private final RefreshTokenService refreshTokenService;

    public RefreshTokenController(RefreshTokenService refreshTokenService) {
        this.refreshTokenService = refreshTokenService;
    }

    @PostMapping
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        try {
            refreshTokenService.refreshToken(request, response);
        } catch (IOException ignored) {
        }


    }
}
