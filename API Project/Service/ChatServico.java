import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.http.ResponseEntity;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public ResponseEntity<Void> sendMessage(ChatMessage message) {
        chatMessageRepository.save(message);
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<List<ChatMessage>> getChatHistory() {
        List<ChatMessage> chatHistory = chatMessageRepository.findAll();
        return ResponseEntity.ok(chatHistory);
    }

    public ResponseEntity<Void> createChatRoom(String roomName) {
        
        return ResponseEntity.ok().build();
    }

    public ResponseEntity<Page<ChatMessage>> searchMessagesByCriteria(String criteria, PageRequest pageRequest) {
        Page<ChatMessage> messages = chatMessageRepository.findByContentContaining(criteria, pageRequest);
        return ResponseEntity.ok(messages);
    }
}
