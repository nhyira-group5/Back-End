package API.nhyira.apivitalis.Chat.DTO;

import lombok.*;

@Data
public class ChatExibitionDto {
    private Integer idChat;
    private toUsuarioDto usuarioId;
    private toUsuarioDto personalId;
    private boolean ativo;

    @Data
    public static class toUsuarioDto {
        private Integer id;
        private String nome;
        private String nickname;
    }
}
