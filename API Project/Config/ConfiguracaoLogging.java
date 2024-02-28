import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.boot.logging.LoggingSystem;
import org.springframework.http.ResponseEntity;

@Configuration
public class ConfiguracaoLogging {

    @Bean
    public ResponseEntity<LoggingSystem> sistemaDeLogging() {
        LoggingSystem loggingSystem = LoggingSystem.get(getClass().getClassLoader());
        return ResponseEntity.ok(loggingSystem);
    }
}
