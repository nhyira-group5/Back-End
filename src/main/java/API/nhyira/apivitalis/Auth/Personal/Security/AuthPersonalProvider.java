package API.nhyira.apivitalis.Auth.Personal.Security;

import API.nhyira.apivitalis.Auth.Personal.AuthPersonalService;
import API.nhyira.apivitalis.Auth.Usuario.AuthUsuarioService;
import API.nhyira.apivitalis.Auth.Usuario.Security.AuthUsuarioProvider;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

public class AuthPersonalProvider implements AuthenticationProvider{

    private final AuthPersonalService authPersonalService;
    private final PasswordEncoder passwordEncoder;

    public AuthPersonalProvider(AuthPersonalService authPersonalService, PasswordEncoder passwordEncoder) {
        this.authPersonalService = authPersonalService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        final String login = authentication.getName();
        final String password = authentication.getCredentials().toString();

        UserDetails personal = authPersonalService.loadUserByUsername(login);

        if (
                this.passwordEncoder.matches(password, personal.getPassword())
        ) {
            return new UsernamePasswordAuthenticationToken(personal, null, personal.getAuthorities());
        } else {
            throw new BadCredentialsException("Credencial de login ou Senha inválidos!");
        }
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(UsernamePasswordAuthenticationToken.class);
    }
}
