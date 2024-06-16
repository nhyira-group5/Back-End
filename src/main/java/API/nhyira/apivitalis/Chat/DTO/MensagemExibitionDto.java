package API.nhyira.apivitalis.Chat.DTO;

import lombok.*;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class MensagemExibitionDto {
    private Integer idMensagem;
    private toChatDto chatId;
    private toUsuarioDto remetenteId;
    private toUsuarioDto destinatarioId;
    private String assunto;
    private LocalDateTime dateTime;

    @Data
    public static class toChatDto {
        private Integer id;
        private Integer usuarioId;
        private Integer personalId;
        private boolean ativo;
    }

    @Data
    public static class toUsuarioDto {
        private Integer id;
        private String nome;
        private String nickname;
        private Integer personalId;
    }
}
