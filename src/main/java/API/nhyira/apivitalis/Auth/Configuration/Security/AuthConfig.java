package API.nhyira.apivitalis.Auth.Configuration.Security;

import API.nhyira.apivitalis.Auth.Configuration.AuthEntryPoint;
import API.nhyira.apivitalis.Auth.Usuario.AuthUsuarioService;
import API.nhyira.apivitalis.Auth.Usuario.Security.AuthUsuarioFilter;
import API.nhyira.apivitalis.Auth.Usuario.Security.AuthUsuarioProvider;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
import org.springframework.security.config.annotation.web.configurers.HeadersConfigurer;
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
            new AntPathRequestMatcher("/login/**"),
            new AntPathRequestMatcher("/swagger-ui/**"),
            new AntPathRequestMatcher("/swagger-ui.html"),
            new AntPathRequestMatcher("/swagger-resources"),
            new AntPathRequestMatcher("/swagger-resources/**"),
            new AntPathRequestMatcher("/configuration/ui"),
            new AntPathRequestMatcher("/configuration/security"),
            new AntPathRequestMatcher("/api/public/**"),
            new AntPathRequestMatcher("/api/public/authenticate"),
            new AntPathRequestMatcher("/webjars/**"),
            new AntPathRequestMatcher("/v3/api-docs/**"),
            new AntPathRequestMatcher("/actuator/*"),
            new AntPathRequestMatcher("/usuarios/por-username", "GET"),
            new AntPathRequestMatcher("/usuarios", "GET"),
            new AntPathRequestMatcher("/usuarios", "POST"),
            new AntPathRequestMatcher("/usuarios/**", "GET"),
            new AntPathRequestMatcher("/usuarios/**", "DELETE"),
            new AntPathRequestMatcher("/usuarios/**", "PUT"),
            new AntPathRequestMatcher("/usuarios/**", "POST"),
            new AntPathRequestMatcher("/usuarios/**", "PATCH"),
            new AntPathRequestMatcher("/usuarios/download", "GET"),
            new AntPathRequestMatcher("/enderecos/**"),
            new AntPathRequestMatcher("/especialidades/**"),
            new AntPathRequestMatcher("/especialidadesPersonais/**"),
            new AntPathRequestMatcher("/cartoes/**"),
            new AntPathRequestMatcher("/assinaturas/**"),
            new AntPathRequestMatcher("/pagamentos/**"),
            new AntPathRequestMatcher("/rotinaTreinos/**"),
            new AntPathRequestMatcher("/email", "POST"),
            new AntPathRequestMatcher("/fichas/**"),
            new AntPathRequestMatcher("/midias/**"),
            new AntPathRequestMatcher("/midias/uploadImage", "POST"),
            new AntPathRequestMatcher("/midias/uploadVideo", "POST"),
            new AntPathRequestMatcher("/especialidadesPorMetas/**"),
            new AntPathRequestMatcher("/rotinaUsuarios/**"),
            new AntPathRequestMatcher("/rotinaMensais/**"),
            new AntPathRequestMatcher("/rotinaSemanais/**"),
            new AntPathRequestMatcher("/rotinaDiarias/**"),
            new AntPathRequestMatcher("/treinos/**"),
            new AntPathRequestMatcher("/refeicaoDiarias/**"),
            new AntPathRequestMatcher("/metas/**"),
            new AntPathRequestMatcher("/chats/**"),
            new AntPathRequestMatcher("/murais/**"),
            new AntPathRequestMatcher("/dietas/**"),
            new AntPathRequestMatcher("/exercicios/**"),
            new AntPathRequestMatcher("/alimentos/**"),
            new AntPathRequestMatcher("/refeicoes/**"),
            new AntPathRequestMatcher("/lembretes/**"),
            new AntPathRequestMatcher("/contratos/**"),
            new AntPathRequestMatcher("/alimentos-por-refeicoes/**"),
            new AntPathRequestMatcher("/refeicoes-por-dietas/**"),
            new AntPathRequestMatcher("/sqlserver/data/insert**"),
            new AntPathRequestMatcher("/usuarios", "POST"),
            new AntPathRequestMatcher("/tagExercicios/**"),
            new AntPathRequestMatcher("/tags/**"),
            new AntPathRequestMatcher("/login/usuario", "POST"),
            new AntPathRequestMatcher("/error/**"),
    };

    @Autowired
    private AuthUsuarioService authUsuarioService;

    @Autowired
    private AuthEntryPoint authEntryPoint;

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http
                .headers(headers -> headers
                        .frameOptions(HeadersConfigurer.FrameOptionsConfig::disable))
                .cors(Customizer.withDefaults())
                .csrf(CsrfConfigurer<HttpSecurity>::disable)
                .authorizeHttpRequests(authorize -> authorize.requestMatchers(URLS_PERMITIDAS)
                        .permitAll()
                        .anyRequest()
                        .authenticated()
                )
                .exceptionHandling(handling -> handling
                        .authenticationEntryPoint(authEntryPoint))
                .sessionManagement(management -> management
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.addFilterBefore(jwtAuthenticationFilterBean(), UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManagerForUsuarios(HttpSecurity http) throws Exception {
        AuthenticationManagerBuilder authenticationManagerBuilder = http.getSharedObject(AuthenticationManagerBuilder.class);
        authenticationManagerBuilder.authenticationProvider(new AuthUsuarioProvider(authUsuarioService, passwordEncoder()));
        return authenticationManagerBuilder.build();
    }

    @Bean
    public AuthEntryPoint jwtAuthenticationEntryPoint() {
        return new AuthEntryPoint();
    }

    @Bean
    public AuthUsuarioFilter jwtAuthenticationFilterBean() {
        return new AuthUsuarioFilter(authUsuarioService, jwtAuthenticationUntilBean());
    }

    @Bean
    public TokenGenJwt jwtAuthenticationUntilBean() {
        return new TokenGenJwt();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.applyPermitDefaultValues();
        corsConfiguration.setAllowedMethods(
                Arrays.asList(
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
