package API.nhyira.apivitalis.Chat.DTO;

import lombok.*;

@Data
public class ChatCreateEditDto {
    private Integer usuarioId;
    private Integer personalId;
    private boolean ativo;
}
