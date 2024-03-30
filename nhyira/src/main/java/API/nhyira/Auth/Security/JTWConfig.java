package API.nhyira.Auth.Security;

import API.nhyira.Auth.Service.DetailsUsuarioServiceImp;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@EnableWebSecurity
public class JTWConfig extends WebSecurityConfigurerAdapter {
    private final DetailsUsuarioServiceImp usuarioServiceImp;
    private final PasswordEncoder passwordEncoder;

    public JTWConfig(DetailsUsuarioServiceImp usuarioServiceImp, PasswordEncoder passwordEncoder) {
        this.usuarioServiceImp = usuarioServiceImp;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(usuarioServiceImp).passwordEncoder(passwordEncoder);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.csrf().disable().authorizeHttpRequests()                                   // Habilitar quando estiver em PRD
                .antMatchers(HttpMethod.POST, "/login").permitAll()     // Sempre será permitido ações na URI /login
                .anyRequest().authenticated()
                .and()
                .addFilter(new JWTAuth(authenticationManager()))
                .addFilter(new JWTValidarAuth(authenticationManager()))
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);
    }

    // Cors é aquele que permite a API receber requisições de outros domínios sem ser o dela
    // Pode retirar se não usar req de outros lugares
    @Bean
    CorsConfigurationSource corsConfigurationSource () {
        final UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration corsConfiguration = new CorsConfiguration().applyPermitDefaultValues();
        source.registerCorsConfiguration("/**", corsConfiguration);
        return source;
    }
}
