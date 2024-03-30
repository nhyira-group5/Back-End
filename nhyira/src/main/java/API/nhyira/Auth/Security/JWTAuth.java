package API.nhyira.Auth.Security;

import API.nhyira.Auth.Data.DetailsUsuarioData;
import API.nhyira.CrudEntity.Model.UsuarioModel;
import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

public class JWTAuth extends UsernamePasswordAuthenticationFilter {
    public static final int TOKEN_EXPIRATION = 3_600_000;                           // 1 Hora para expirar
    public static final String TOKEN_SENHA = String.valueOf(UUID.randomUUID());     // Senha do token
    private final AuthenticationManager authenticationManager;

    public JWTAuth(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        try {
            UsuarioModel usuario = new ObjectMapper().readValue(request.getInputStream(), UsuarioModel.class);
            return authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                    usuario.getUsername(),
                    usuario.getSenha(),
                    new ArrayList<>() // Lista de autorizações
            ));
        } catch (IOException e) {
            throw new RuntimeException("Falha ao autenticar o usuário: ", e);
        }
    }

    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {
        DetailsUsuarioData usuarioData = (DetailsUsuarioData) authResult.getPrincipal();

        String token = JWT.create()
                .withSubject(usuarioData.getUsername())
                .withExpiresAt(new Date(TOKEN_EXPIRATION))
                .sign(Algorithm.HMAC512(TOKEN_SENHA));

        response.getWriter().write(token);
        response.getWriter().flush();
    }
}
