
package API.nhyira.apivitalis.Chat.DTO;

import API.nhyira.apivitalis.Chat.Entity.Chat;
import API.nhyira.apivitalis.Chat.Entity.Mensagem;
import API.nhyira.apivitalis.Entity.Usuario;

import java.time.LocalDate;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

public class MensagemMapper {
    public static Mensagem toEntity(MensagemCreateEditDto dto) {
        if (dto == null) return null;
        Mensagem mensagem = new Mensagem();
        mensagem.setAssunto(dto.getAssunto());
        mensagem.setDateTime(LocalDateTime.now());
        return mensagem;
    }

    public static MensagemExibitionDto toDto(Mensagem entity) {
        if (entity == null) return null;
        MensagemExibitionDto dto = new MensagemExibitionDto();
        dto.setChatId(toChatDto(entity.getChatId()));
        dto.setRemetenteId(toUsuarioDto(entity.getRemetenteId()));
        dto.setDestinatarioId(toUsuarioDto(entity.getDestinatarioId()));
        dto.setAssunto(entity.getAssunto());
        dto.setDateTime(entity.getDateTime());
        return dto;
    }

    public static MensagemExibitionDto.toChatDto toChatDto(Chat entity) {
        if (entity == null) return null;
        MensagemExibitionDto.toChatDto dto = new MensagemExibitionDto.toChatDto();
        dto.setId(entity.getIdChat());
        dto.setUsuarioId(entity.getUsuarioId().getIdUsuario());
        dto.setPersonalId(entity.getPersonalId().getIdUsuario());
        dto.setAtivo(entity.isAtivo());
        return dto;
    }

    public static MensagemExibitionDto.toUsuarioDto toUsuarioDto(Usuario entity) {
        if (entity == null) return null;
        MensagemExibitionDto.toUsuarioDto dto = new MensagemExibitionDto.toUsuarioDto();
        dto.setId(entity.getIdUsuario());
        dto.setNome(entity.getNome());
        dto.setNickname(entity.getNickname());
        return dto;
    }

    public static List<MensagemExibitionDto> toDtoList(List<Mensagem> mensagens) {
        return mensagens.stream()
                .map(MensagemMapper::toDto)
                .collect(Collectors.toList());
    }
}
