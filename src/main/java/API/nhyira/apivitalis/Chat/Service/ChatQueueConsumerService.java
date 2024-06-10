package API.nhyira.apivitalis.Chat.Service;

import API.nhyira.apivitalis.Chat.Entity.Mensagem;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ChatQueueConsumerService {
    private final ChatQueueService chatQueueService;

    @Scheduled(fixedRate = 5000)
    public void processQueue() {
        while (chatQueueService.getQueueSize() > 0) {
            try {
                Mensagem mensagem = chatQueueService.getMensagemFromQueue();
                System.out.println("Processando mensagem: " + mensagem.getAssunto());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                e.printStackTrace();
            }
        }
    }
}