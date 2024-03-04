import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class ServicoRecomendacao {

    private final ServicoAPIExterna servicoAPIExterna;

       @Value("${google.places.api.key}")
    private String googlePlacesApiKey;

    @Autowired
    public ServicoRecomendacao(ServicoAPIExterna servicoAPIExterna) {
        this.servicoAPIExterna = servicoAPIExterna;
    }

    public ResponseEntity<String> gerarRecomendacaoTreino(String userId) {
        ResponseEntity<String> respostaDadosAcademias = servicoAPIExterna.buscarAcademiasProximas("academia");
        String dadosAcademias = respostaDadosAcademias.getBody();

        

        String recomendacao = "Recomendação de treino para o usuário " + userId;
        return ResponseEntity.ok(recomendacao);
    }

    public ResponseEntity<String> gerarRecomendacaoDieta(String userId) {
        String recomendacao = "Recomendação de dieta para o usuário " + userId;
        return ResponseEntity.ok(recomendacao);
    }

    public ResponseEntity<String> atualizarRecomendacaoTreinoComFeedback(String userId, String feedback) {
        String recomendacaoAtualizada = "Recomendação de treino atualizada para o usuário " + userId + " com feedback: " + feedback;
        return ResponseEntity.ok(recomendacaoAtualizada);
    }
}
