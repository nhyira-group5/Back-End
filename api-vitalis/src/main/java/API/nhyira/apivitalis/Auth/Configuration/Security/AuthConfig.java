package API.nhyira.apivitalis.Auth.Configuration.Security;

import API.nhyira.apivitalis.Auth.Configuration.AuthEntryPoint;
import API.nhyira.apivitalis.Auth.Personal.AuthPersonalService;
import API.nhyira.apivitalis.Auth.Usuario.AuthUsuarioService;
import API.nhyira.apivitalis.Auth.Usuario.Security.AuthUsuarioFilter;
import API.nhyira.apivitalis.Auth.Usuario.Security.AuthUsuarioProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.List;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class AuthConfig {
    private static final String ORIGENS_PERMITIDAS = "*";

    // INSERIR URLS PADRÃO DE ACESSO
    private static final AntPathRequestMatcher[] URLS_PERMITIDAS = {
            new AntPathRequestMatcher("/login/usuario/**"),
            new AntPathRequestMatcher("/login/personal/**"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/**"),
            new AntPathRequestMatcher("/error/**"),
    };

    @Autowired
    private AuthUsuarioService authUsuarioService;

//    @Autowired
//    private AuthPersonalService authPersonalService;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthEntryPoint jwtAuthenticationEntryPoint() {
        return new AuthEntryPoint();
    }

    @Bean
    public TokenGenJwt jwtAuthenticationUntilBean() {
        return new TokenGenJwt();
    }

    @Bean
    public AuthUsuarioFilter jwtAuthenticationFilterBean() {
        return new AuthUsuarioFilter(authUsuarioService, jwtAuthenticationUntilBean());
    }

    @Bean
    public AuthenticationManager authenticationManager(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);

        authenticationManagerBuilder.authenticationProvider(new AuthUsuarioProvider(authUsuarioService, passwordEncoder()));
        return authenticationManagerBuilder.build();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer<HttpSecurity>::disable)
                .authorizeHttpRequests(
                        auth -> auth.requestMatchers(URLS_PERMITIDAS)
                                .permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .exceptionHandling(
                        handling -> handling.authenticationEntryPoint(authEntryPoint)
                )
                .sessionManagement(
                        manage -> manage.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                );
        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);
        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(
                Arrays.asList (
                        HttpMethod.GET.name(),
                        HttpMethod.POST.name(),
                        HttpMethod.PUT.name(),
                        HttpMethod.PATCH.name(),
                        HttpMethod.DELETE.name(),
                        HttpMethod.OPTIONS.name(),
                        HttpMethod.HEAD.name(),
                        HttpMethod.TRACE.name()
                )
        );
        corsConfiguration.setExposedHeaders(List.of(HttpHeaders.CONTENT_DISPOSITION));
        UrlBasedCorsConfigurationSource origem = new UrlBasedCorsConfigurationSource();
        origem.registerCorsConfiguration("/**", corsConfiguration);
        return origem;
    }
}
