import org.springframework.context.annotation.Configuration;
import org.springframework.http.ResponseEntity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
public class ConfigSeguranca extends WebSecurityConfigurerAdapter {

  

    public ResponseEntity<String> configurarSeguranca() {
       
        return ResponseEntity.ok("Configuração de segurança realizada com sucesso.");
    }

  
}
