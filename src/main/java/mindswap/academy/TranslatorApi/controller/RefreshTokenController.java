package mindswap.academy.TranslatorApi.controller;

import io.swagger.v3.oas.annotations.Operation;
import mindswap.academy.TranslatorApi.service.RefreshToken.RefreshTokenServiceImpl;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static mindswap.academy.TranslatorApi.utils.UtilStrings.DESCRIPTION_SIX;
import static mindswap.academy.TranslatorApi.utils.UtilStrings.SUMMARY_SIX;

@RestController
@RequestMapping("/refresh")
public class RefreshTokenController {
    private final RefreshTokenServiceImpl refreshTokenServiceImpl;

    public RefreshTokenController(RefreshTokenServiceImpl refreshTokenServiceImpl) {
        this.refreshTokenServiceImpl = refreshTokenServiceImpl;
    }

    @Operation(summary = SUMMARY_SIX, description = DESCRIPTION_SIX)
    @PostMapping
    public void refreshToken(HttpServletRequest request, HttpServletResponse response){
        try {
            refreshTokenServiceImpl.refreshToken(request, response);
        } catch (IOException ignored) {
        }


    }
}
