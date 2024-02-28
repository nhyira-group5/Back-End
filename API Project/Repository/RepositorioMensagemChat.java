import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.http.ResponseEntity;

@Repository
public interface RepositorioMensagemChat extends JpaRepository<MensagemChat, Long> {

    ResponseEntity<Page<MensagemChat>> findByConteudoContaining(String conteudo, Pageable pageable);

    ResponseEntity<Page<MensagemChat>> findByTimestampBetween(LocalDateTime dataInicio, LocalDateTime dataFim, Pageable pageable);

    ResponseEntity<Page<MensagemChat>> findByIdUsuario(Long idUsuario, Pageable pageable);

    ResponseEntity<Page<MensagemChat>> findByIdUsuarioIn(List<Long> idsUsuarios, Pageable pageable);
}
