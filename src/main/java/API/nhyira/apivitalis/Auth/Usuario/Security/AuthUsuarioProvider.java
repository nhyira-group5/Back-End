package API.nhyira.apivitalis.Auth.Usuario.Security;

import API.nhyira.apivitalis.Auth.Usuario.AuthUsuarioService;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthUsuarioProvider implements AuthenticationProvider {

    private final AuthUsuarioService authUsuarioService;
    private final PasswordEncoder passwordEncoder;

    public AuthUsuarioProvider(AuthUsuarioService authUsuarioService, PasswordEncoder passwordEncoder) {
        this.authUsuarioService = authUsuarioService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String username = authentication.getName(); // Aqui obtém o nome de usuário ou email
        final String password = authentication.getCredentials().toString();

        UserDetails usuario = this.authUsuarioService.loadUserByUsername(username);

        if (this.passwordEncoder.matches(password, usuario.getPassword())
        ) {
            return new UsernamePasswordAuthenticationToken(usuario, null, usuario.getAuthorities());
        } else {
            throw new BadCredentialsException("Credencial de login ou Senha inválidos!");
        }
    }


    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
