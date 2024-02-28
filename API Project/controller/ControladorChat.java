import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class ControladorChat {

    @MessageMapping("/chat/enviar")
    @SendTo("/topico/chat")
    public ResponseEntity<MensagemChat> enviarMensagem(MensagemChat mensagem) {
        mensagem.setIdMensagem(UUID.randomUUID().toString());
        mensagem.setTimestamp(LocalDateTime.now());
        mensagem.setTipo(TipoMensagem.TEXTO);

        List<String> mencoes = extrairMencoes(mensagem.getConteudo());
        mensagem.setMencoes(mencoes);

        List<String> hashtags = extrairHashtags(mensagem.getConteudo());
        mensagem.setHashtags(hashtags);

        List<String> anexos = extrairAnexos(mensagem.getConteudo());
        mensagem.setAnexos(anexos);

        return ResponseEntity.ok(mensagem);
    }

    @MessageMapping("/chat/historico/{salaId}")
    @SendTo("/topico/chat/{salaId}")
    public ResponseEntity<List<MensagemChat>> obterHistoricoChat(@PathVariable String salaId) {
        List<MensagemChat> historico = new ArrayList<>();
      
        return ResponseEntity.ok(historico);
    }

    @MessageMapping("/chat/criarSala")
    @SendTo("/topico/salaCriada")
    public ResponseEntity<String> criarSalaChat() {
      
        return ResponseEntity.status(HttpStatus.CREATED).body("Sala criada com sucesso");
    }

    @MessageMapping("/chat/privado/{idUsuario}")
    @SendTo("/topico/privado/{idUsuario}")
    public ResponseEntity<MensagemChat> enviarMensagemPrivada(@PathVariable String idUsuario, MensagemChat mensagem) {
      
        return ResponseEntity.ok(mensagem);
    }

    @MessageMapping("/chat/entrarSala/{salaId}")
    @SendTo("/topico/entrarSala/{salaId}")
    public ResponseEntity<String> entrarSalaChat(@PathVariable String salaId) {
        
        return ResponseEntity.ok("Usuário entrou na sala com sucesso");
    }

    private List<String> extrairMencoes(String conteudo) {
        
        return new ArrayList<>();
    }

    private List<String> extrairHashtags(String conteudo) {
        
        return new ArrayList<>();
    }

    private List<String> extrairAnexos(String conteudo) {
       
        return new ArrayList<>();
    }
}
