package API.nhyira.apivitalis.Chat.Repository;

import API.nhyira.apivitalis.Chat.Entity.Chat;
import API.nhyira.apivitalis.Chat.Entity.Mensagem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import java.util.Optional;

@Repository
public interface MensagemRepository extends JpaRepository<Mensagem, Integer> {
    List<Mensagem> findByChatIdIs(Chat chat);
}

