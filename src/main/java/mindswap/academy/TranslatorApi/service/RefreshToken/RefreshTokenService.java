package mindswap.academy.TranslatorApi.service.RefreshToken;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public interface RefreshTokenService {
    void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException;
}
