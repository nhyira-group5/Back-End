package API.nhyira.apivitalis.Chat.Controller;

import API.nhyira.apivitalis.Chat.DTO.*;
import API.nhyira.apivitalis.Chat.Entity.Mensagem;
import API.nhyira.apivitalis.Chat.Service.ChatService;
import API.nhyira.apivitalis.Chat.Service.ChatQueueService;
import API.nhyira.apivitalis.Chat.Service.MensagemService;
import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Service.UsuarioService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.DestinationVariable;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/chats")
public class ChatController {
    private final ChatService chatService;
    private final MensagemService mensagemService;
    private final UsuarioService usuarioService;
    private final SimpMessagingTemplate messagingTemplate;
    private final ChatQueueService chatQueueService;

    @GetMapping
    public ResponseEntity<List<ChatExibitionDto>> getAllChats() {
        List<ChatExibitionDto> chats = ChatMapper.toDtoList(chatService.getAllChats());
        return chats.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(chats);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ChatExibitionDto> getChatById(@PathVariable int id) {
        if (id <= 0) return ResponseEntity.badRequest().build();
        return ResponseEntity.ok(ChatMapper.toDto(chatService.getChatById(id)));
    }

    @PostMapping
    public ResponseEntity<ChatExibitionDto> createChat(@RequestBody ChatCreateEditDto chatDto) {
        Usuario usuario = usuarioService.showUserById(chatDto.getUsuarioId());
        Usuario personal = usuarioService.showUserById(chatDto.getPersonalId());
        return ResponseEntity.status(201).body(ChatMapper.toDto(chatService.saveChat(chatDto, usuario, personal)));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteChat(@PathVariable int id) {
        if (id <= 0) return ResponseEntity.status(400).body("ID invalido!");
        try {
            chatService.deleteChatById(id);
            return ResponseEntity.status(204).body("Deletado com sucesso!");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Houve algum erro: " + e.getMessage());
        }
    }

    @GetMapping("/{id}/mensagens")
    public ResponseEntity<List<MensagemExibitionDto>> getMensagensByChatId(@PathVariable int id) {
        if (id <= 0) return ResponseEntity.status(400).build();
        List<MensagemExibitionDto> mensagens = MensagemMapper.toDtoList(mensagemService.getMensagensByChatId(id));
        return mensagens.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(mensagens);
    }

    @PostMapping("/{id}/mensagens")
    public ResponseEntity<MensagemExibitionDto> createMensagem(@PathVariable int id, @RequestBody @Valid MensagemCreateEditDto mensagemDto) {
        Usuario remetente = usuarioService.showUserById(mensagemDto.getRemetenteId());
        Usuario destinatario = usuarioService.showUserById(mensagemDto.getDestinatarioId());
        Mensagem mensagem = mensagemService.saveMensagem(mensagemDto, id, remetente, destinatario);

        chatQueueService.addMensagemToQueue(mensagem);
        MensagemExibitionDto mensagemCriada = MensagemMapper.toDto(mensagem);

        messagingTemplate.convertAndSend("/topic/chat/" + id, mensagemCriada);
        return ResponseEntity.status(201).body(mensagemCriada);
    }

    @MessageMapping("/{id}/message")
    @SendTo("/topic/chat/{id}")
    public MensagemExibitionDto handleWebSocketMessage(@DestinationVariable int id, MensagemCreateEditDto mensagemDto) {
        Usuario remetente = usuarioService.showUserById(mensagemDto.getRemetenteId());
        Usuario destinatario = usuarioService.showUserById(mensagemDto.getDestinatarioId());
        Mensagem mensagem = mensagemService.saveMensagem(mensagemDto, id, remetente, destinatario);

        chatQueueService.addMensagemToQueue(mensagem);
        return MensagemMapper.toDto(mensagem);
    }
}