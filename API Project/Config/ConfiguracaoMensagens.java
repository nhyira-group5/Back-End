import org.springframework.context.annotation.Configuration;
import org.springframework.messaging.support.converter.MappingJackson2MessageConverter;
import org.springframework.messaging.support.converter.MessageConverter;
import org.springframework.messaging.support.converter.StringMessageConverter;
import org.springframework.http.ResponseEntity;
import org.springframework.context.annotation.Bean;

@Configuration
public class ConfiguracaoMensagens {

    @Bean
    public ResponseEntity<MessageConverter> conversorMensagem() {
        MessageConverter messageConverter = new MappingJackson2MessageConverter();
        return ResponseEntity.ok(messageConverter);
    }

    @Bean
    public ResponseEntity<MessageConverter> conversorMensagemString() {
        MessageConverter stringMessageConverter = new StringMessageConverter();
        return ResponseEntity.ok(stringMessageConverter);
    }
}
