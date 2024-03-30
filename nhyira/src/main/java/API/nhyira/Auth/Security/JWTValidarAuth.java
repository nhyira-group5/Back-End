package API.nhyira.Auth.Security;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.www.BasicAuthenticationFilter;

import java.io.IOException;
import java.util.ArrayList;

public class JWTValidarAuth extends BasicAuthenticationFilter {

    public static final String HEADER_ATTRIBUTE = "Authorization";
    public static final String ATTRIBUTE_PREFIX = "Bearer ";
    public JWTValidarAuth(AuthenticationManager authenticationManager) {
        super(authenticationManager);
    }

    private UsernamePasswordAuthenticationToken getToken (String token) {
        String usuario = JWT.require(Algorithm.HMAC512(JWTAuth.TOKEN_SENHA))
                .build()
                .verify(token)
                .getSubject();
        return usuario == null ? null : new UsernamePasswordAuthenticationToken(usuario, null, new ArrayList<>()); // A lista seria as permissões de acessos do usuário
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {
        String atributo = request.getHeader(HEADER_ATTRIBUTE);

        if (atributo == null) {
            chain.doFilter(request, response);
            return;
        } else if (!atributo.startsWith(ATTRIBUTE_PREFIX)) {
            chain.doFilter(request, response);
            return;
        } else {
            String token = atributo.replace(ATTRIBUTE_PREFIX, "");
            UsernamePasswordAuthenticationToken authenticationToken = getToken(token);
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            chain.doFilter(request, response);
        }
    }
}
