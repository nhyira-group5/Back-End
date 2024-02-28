import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChatService {

    private final ChatMessageRepository chatMessageRepository;

    @Autowired
    public ChatService(ChatMessageRepository chatMessageRepository) {
        this.chatMessageRepository = chatMessageRepository;
    }

    public void sendMessage(ChatMessage message) {
      
        chatMessageRepository.save(message);
    }

    public List<ChatMessage> getChatHistory() {
      
        return chatMessageRepository.findAll();
    }

   
    public void createChatRoom(String roomName) {
     
    }


    public Page<ChatMessage> searchMessagesByCriteria(String criteria, PageRequest pageRequest) {
        /
        return chatMessageRepository.findByContentContaining(criteria, pageRequest);
    }
}
