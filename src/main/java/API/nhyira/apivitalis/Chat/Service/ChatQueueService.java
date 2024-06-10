package API.nhyira.apivitalis.Chat.Service;



import API.nhyira.apivitalis.Chat.Entity.Mensagem;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.LinkedList;
import java.util.Queue;

@Service
@RequiredArgsConstructor
public class ChatQueueService {

    private final Queue<Mensagem> mensagemQueue = new LinkedList<>();

    public synchronized void addMensagemToQueue(Mensagem mensagem) {
        mensagemQueue.add(mensagem);
        notifyAll();
    }

    public synchronized Mensagem getMensagemFromQueue() throws InterruptedException {
        while (mensagemQueue.isEmpty()) {
            wait();
        }
        return mensagemQueue.poll();
    }

    public synchronized int getQueueSize() {
        return mensagemQueue.size();
    }
}