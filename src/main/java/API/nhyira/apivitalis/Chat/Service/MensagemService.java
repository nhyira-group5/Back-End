package API.nhyira.apivitalis.Chat.Service;

import API.nhyira.apivitalis.Chat.DTO.MensagemCreateEditDto;
import API.nhyira.apivitalis.Chat.DTO.MensagemMapper;
import API.nhyira.apivitalis.Chat.Entity.Chat;
import API.nhyira.apivitalis.Chat.Entity.Mensagem;
import API.nhyira.apivitalis.Chat.Repository.ChatRepository;
import API.nhyira.apivitalis.Chat.Repository.MensagemRepository;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.ForbiddenException;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class MensagemService {
    private final ChatRepository chatRepository;
    private final MensagemRepository mensagemRepository;
    private final ChatQueueService chatQueueService;

    public List<Mensagem> getMensagensByChatId(int chatId) {
        Optional<Chat> chat = chatRepository.findById(chatId);
        chat.orElseThrow(() -> new NaoEncontradoException("Chat"));
        return mensagemRepository.findByChatIdIs(chat.get());
    }

    public Mensagem saveMensagem(MensagemCreateEditDto mensagemDto, int chatId, Usuario remetente, Usuario destinatario) {
        Chat chat = chatRepository.findById(chatId).orElseThrow(() -> new NaoEncontradoException("Chat"));
        if (!chat.isAtivo()) throw new ForbiddenException("Usuario");
        Mensagem mensagem = MensagemMapper.toEntity(mensagemDto);
        mensagem.setChatId(chat);
        mensagem.setRemetenteId(remetente);
        mensagem.setDestinatarioId(destinatario);
        mensagemRepository.save(mensagem);
        chatQueueService.addMensagemToQueue(mensagem);
        return mensagem;
    }
}