package mindswap.academy.TranslatorApi.service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import mindswap.academy.TranslatorApi.Models.Client;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.auth0.jwt.JWT.decode;
import static com.auth0.jwt.JWT.require;

@Service
public class RefreshTokenService {

    private final ClientService clientService;

    public RefreshTokenService(ClientService clientService) {
        this.clientService = clientService;
    }

    public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
        String authorizationHeader = request.getHeader("Authorization");

        if (authorizationHeader != null && authorizationHeader.startsWith("Bearer ")) {

            try {
                String token = authorizationHeader.substring("Bearer ".length());
                Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                JWTVerifier verifier = require(algorithm).build();
                DecodedJWT decodedJWT = JWT.decode(token);
                String username = decodedJWT.getSubject();
                Date expiresAt = decodedJWT.getExpiresAt();
                Date now = new Date();

                if (now.getTime()-expiresAt.getTime() >= 1000*60*60){
                    response.setHeader("error", "Token expired");
                    response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                    Map<String, String> error = Map.of("error_message", "Token expired");
                    response.setContentType("application/json");
                    new ObjectMapper().writeValue(response.getOutputStream(), error);

                }

                Client client = clientService.getClientByUsername(username);
                String clientRole = client.getRole().getTypeRole();

                String access_token = JWT.create()
                        .withSubject(client.getUsername())
                        .withExpiresAt(new java.util.Date(System.currentTimeMillis() + 10 * 60 * 1000))
                        .withIssuer(request.getRequestURL().toString())
                        .withClaim("role", clientRole)
                        .sign(algorithm);

                Map<String, String> refreshToken = Map.of("access_token", access_token);
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), refreshToken);
            } catch (Exception exception) {
                response.setHeader("error", exception.getMessage());
                response.setStatus(HttpServletResponse.SC_FORBIDDEN);
                Map<String, String> error = Map.of("error_message", exception.getMessage());
                response.setContentType("application/json");
                new ObjectMapper().writeValue(response.getOutputStream(), error);
            }
        } else {
            throw new RuntimeException("Refresh token is missing");
        }
    }
}
