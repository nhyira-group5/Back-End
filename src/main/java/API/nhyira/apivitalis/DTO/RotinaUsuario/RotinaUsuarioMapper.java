package API.nhyira.apivitalis.DTO.RotinaUsuario;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.RotinaUsuario;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.List;

public class RotinaUsuarioMapper {




    public static RotinaUsuarioExibitionDto toDto(RotinaUsuario entity){
        if (entity == null)return null;

        RotinaUsuarioExibitionDto user = new RotinaUsuarioExibitionDto();
        user.setIdRotinaUsuario(entity.getIdRotinaUsuario());
        user.setUsuarioId(usuarioDto(entity.getUsuarioId()));
        user.setMetaId(entity.getMetaId());
        return user;
    }

    public static UsuarioDto usuarioDto(Usuario usuario){
        if (usuario == null)return null;
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setDtNasc(usuario.getDtNasc());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        return usuarioDto;
    }
    public static List<RotinaUsuarioExibitionDto> toDto(List<RotinaUsuario> entities){
        return entities.stream().map(RotinaUsuarioMapper::toDto).toList();
    }









}
