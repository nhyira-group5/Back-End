import org.springframework.http.ResponseEntity;
import java.util.Date;
import java.util.List;

public class NotificacaoServico {

    public ResponseEntity<Void> enviarNotificacao(String idUsuario, String mensagem) {
      
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> agendarNotificacao(String idUsuario, String mensagem, Date horario) {
        
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> enviarNotificacaoGrupo(List<String> idsUsuarios, String mensagem) {
     
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Void> enviarNotificacaoBroadcast(String mensagem) {  return ResponseEntity.ok().build();
    }
}
