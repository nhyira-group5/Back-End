package API.nhyira.apivitalis.Chat.Service;

import API.nhyira.apivitalis.Chat.DTO.ChatCreateEditDto;
import API.nhyira.apivitalis.Chat.DTO.ChatExibitionDto;
import API.nhyira.apivitalis.Chat.DTO.ChatMapper;
import API.nhyira.apivitalis.Chat.Entity.Chat;
import API.nhyira.apivitalis.Chat.Repository.ChatRepository;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Exception.NaoEncontradoException;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChatService {
    private final ChatRepository chatRepository;

    public List<Chat> getAllChats() {
        return chatRepository.findAll();
    }

    public Chat getChatById(int id) {
        Optional<Chat> chat = chatRepository.findById(id);
        chat.orElseThrow(() -> new NaoEncontradoException("Chat"));
        return chat.get();
    }

    public Chat saveChat(ChatCreateEditDto chatDto, Usuario usuario, Usuario personal) {
        Chat chat = ChatMapper.toEntity(chatDto);
        chat.setUsuarioId(usuario);
        chat.setPersonalId(personal);
        chat = chatRepository.save(chat);
        return chat;
    }

    public boolean deleteChatById(int id) {
        Optional<Chat> chat = chatRepository.findById(id);
        chat.orElseThrow(() -> new NaoEncontradoException("Chat"));
        chatRepository.delete(chat.get());
        return true;
    }
}
