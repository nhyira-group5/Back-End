package API.nhyira.apivitalis.Chat.DTO;

import lombok.*;

@Data
public class MensagemCreateEditDto {
    private Integer chatId;
    private Integer remetenteId;
    private Integer destinatarioId;
    private String assunto;
}

