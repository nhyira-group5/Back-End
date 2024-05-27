package API.nhyira.apivitalis.Chat.DTO;

import API.nhyira.apivitalis.Entity.Usuario;
import API.nhyira.apivitalis.Chat.Entity.Chat;

import java.util.List;
import java.util.stream.Collectors;

public class ChatMapper {
    public static ChatExibitionDto toDto(Chat entity) {
        if (entity == null) return null;
        ChatExibitionDto dto = new ChatExibitionDto();
        dto.setIdChat(entity.getIdChat());
        dto.setUsuarioId(toUsuarioDto(entity.getUsuarioId()));
        dto.setPersonalId(toUsuarioDto(entity.getPersonalId()));
        dto.setAtivo(entity.isAtivo());
        return dto;
    }

    public static ChatExibitionDto.toUsuarioDto toUsuarioDto(Usuario entity) {
        if (entity == null) return null;
        ChatExibitionDto.toUsuarioDto dto = new ChatExibitionDto.toUsuarioDto();
        dto.setId(entity.getIdUsuario());
        dto.setNome(entity.getNome());
        dto.setNickname(entity.getNickname());
        return dto;
    }

    public static List<ChatExibitionDto> toDtoList(List<Chat> entities) {
        if (entities == null) return null;
        return entities.stream()
                .map(ChatMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Chat toEntity(ChatCreateEditDto dto) {
        if (dto == null) return null;
        Chat chat = new Chat();
        chat.setAtivo(dto.isAtivo());
        return chat;
    }
}