import java.time.LocalDateTime;
import java.util.List;
import org.springframework.http.ResponseEntity;

public class MensagemChat {
    private String idMensagem;
    private String conteudo;
    private String remetente;
    private LocalDateTime horario;
    private TipoMensagem tipo;
    private String idSala;
    private List<String> mencoes;
    private List<String> hashtags;
    private List<String> anexos;

    public MensagemChat() {
    }

    public MensagemChat(String idMensagem, String conteudo, String remetente, LocalDateTime horario, TipoMensagem tipo, String idSala, List<String> mencoes, List<String> hashtags, List<String> anexos) {
        this.idMensagem = idMensagem;
        this.conteudo = conteudo;
        this.remetente = remetente;
        this.horario = horario;
        this.tipo = tipo;
        this.idSala = idSala;
        this.mencoes = mencoes;
        this.hashtags = hashtags;
        this.anexos = anexos;
    }

    public ResponseEntity<String> getIdMensagem() {
        return ResponseEntity.ok(idMensagem);
    }

    public void setIdMensagem(String idMensagem) {
        this.idMensagem = idMensagem;
    }

    public ResponseEntity<String> getConteudo() {
        return ResponseEntity.ok(conteudo);
    }

    public void setConteudo(String conteudo) {
        this.conteudo = conteudo;
    }

    public ResponseEntity<String> getRemetente() {
        return ResponseEntity.ok(remetente);
    }

    public void setRemetente(String remetente) {
        this.remetente = remetente;
    }

    public ResponseEntity<LocalDateTime> getHorario() {
        return ResponseEntity.ok(horario);
    }

    public void setHorario(LocalDateTime horario) {
        this.horario = horario;
    }

    public ResponseEntity<TipoMensagem> getTipo() {
        return ResponseEntity.ok(tipo);
    }

    public void setTipo(TipoMensagem tipo) {
        this.tipo = tipo;
    }

    public ResponseEntity<String> getIdSala() {
        return ResponseEntity.ok(idSala);
    }

    public void setIdSala(String idSala) {
        this.idSala = idSala;
    }

    public ResponseEntity<List<String>> getMencoes() {
        return ResponseEntity.ok(mencoes);
    }

    public void setMencoes(List<String> mencoes) {
        this.mencoes = mencoes;
    }

    public ResponseEntity<List<String>> getHashtags() {
        return ResponseEntity.ok(hashtags);
    }

    public void setHashtags(List<String> hashtags) {
        this.hashtags = hashtags;
    }

    public ResponseEntity<List<String>> getAnexos() {
        return ResponseEntity.ok(anexos);
    }

    public void setAnexos(List<String> anexos) {
        this.anexos = anexos;
    }
}
