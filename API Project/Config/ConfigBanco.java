import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Bean;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import javax.sql.DataSource;
import org.springframework.http.ResponseEntity;

@Configuration
public class ConfigBanco {

    @Bean
    public ResponseEntity<DataSource> dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/salada");
        dataSource.setUsername("salada");
        dataSource.setPassword("salada");
        return ResponseEntity.ok(dataSource);
    }
}
