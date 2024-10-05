package API.nhyira.apivitalis.DTO.RotinaUsuario;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.List;

import static API.nhyira.apivitalis.DTO.Usuario.UsuarioMapper.midiaExibitionDto;

public class RotinaUsuarioMapper {
    public static RotinaUsuario toEntity(RotinaUsuarioCreateEditDto dto) {
        if (dto == null) return null;

        RotinaUsuario rUser = new RotinaUsuario();
        rUser.setRotinaAlternativa(dto.getRotinaAlternativa());
        return rUser;
    }

    public static RotinaUsuarioExibitionDto toDto(RotinaUsuario entity){
        if (entity == null) return null;

        RotinaUsuarioExibitionDto rUser = new RotinaUsuarioExibitionDto();
        rUser.setIdRotinaUsuario(entity.getIdRotinaUsuario());
        rUser.setUsuarioId(usuarioDto(entity.getUsuarioId()));
        rUser.setMetaId(entity.getMetaId());
        if (entity.getRotinaAlternativa() > 0) rUser.setRotinaAlternativa(true); else rUser.setRotinaAlternativa(false);
        return rUser;
    }

    public static UsuarioDto usuarioDto(Usuario usuario){
        if (usuario == null) return null;

        UsuarioDto usuarioDto = new UsuarioDto();
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setDtNasc(usuario.getDtNasc());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setMidiaDto(midiaExibitionDto(usuario.getMidiaId()));
        return usuarioDto;
    }

//    public static List<RotinaUsuarioExibitionDto> toDto(List<RotinaUsuario> entities){
//        return entities.stream().map(RotinaUsuarioMapper::toDto).toList();
//    }
}