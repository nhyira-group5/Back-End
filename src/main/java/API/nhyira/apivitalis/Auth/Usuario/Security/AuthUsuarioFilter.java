package API.nhyira.apivitalis.Auth.Usuario.Security;

import API.nhyira.apivitalis.Auth.Configuration.Security.TokenGenJwt;
import API.nhyira.apivitalis.Auth.Usuario.AuthUsuarioService;
import API.nhyira.apivitalis.Auth.Usuario.DTO.UserDetailsUsuario;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Objects;

public class AuthUsuarioFilter extends OncePerRequestFilter {

    private static final Logger LOGGER = LoggerFactory.getLogger(AuthUsuarioFilter.class);
    private final AuthUsuarioService authService;
    private final TokenGenJwt jwtTokenManager;

    public AuthUsuarioFilter(AuthUsuarioService authService, TokenGenJwt jwtTokenManager) {
        this.authService = authService;
        this.jwtTokenManager = jwtTokenManager;
    }


    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        String tipo = null;
        String jwtToken = null;
        String requestTokenHeader = request.getHeader("Authorization");

        if (Objects.nonNull(requestTokenHeader) && requestTokenHeader.startsWith("Bearer ")) {
            jwtToken = requestTokenHeader.substring(7);
            try {
                tipo = jwtTokenManager.getUsernameFromToken(jwtToken);
            } catch (ExpiredJwtException e) {

                System.out.println("[Falha na autenticação] - Token Expirado \n" + e.getClaims().getSubject() + e.getMessage());
                response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            }
        }

        if (tipo != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            addTipoInContext(request, tipo, jwtToken);
        }

        filterChain.doFilter(request, response);
    }


    private void addTipoInContext(HttpServletRequest request, String tipo, String jwtToken) {
        UserDetailsUsuario userDetails = authService.loadUserByUsername(tipo);

        if (jwtTokenManager.validateToken(jwtToken, userDetails)) {
            UsernamePasswordAuthenticationToken userPasswordAuthToken = new UsernamePasswordAuthenticationToken(
                    userDetails, null, userDetails.getAuthorities()
            );

            userPasswordAuthToken.setDetails(
                    new WebAuthenticationDetailsSource().buildDetails(request)
            );

            userPasswordAuthToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

            SecurityContextHolder.getContext().setAuthentication(userPasswordAuthToken);
        }
    }
}
