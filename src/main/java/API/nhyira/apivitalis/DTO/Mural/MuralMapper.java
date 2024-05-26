package API.nhyira.apivitalis.DTO.Mural;

import API.nhyira.apivitalis.Entity.Mural;
import API.nhyira.apivitalis.Entity.Usuario;

public class MuralMapper {


    public static MuralExibitionDto toDto(Mural mural){
        if (mural == null)return null;

        MuralExibitionDto muralExibitionDto = new MuralExibitionDto();

        muralExibitionDto.setIdMural(mural.getIdMural());
        muralExibitionDto.setDtPostagem(mural.getDtPostagem());
        muralExibitionDto.setMidiaId(mural.getMidiaId());
        muralExibitionDto.setUsuarioId(usuarioDto(mural.getUsuarioId()));
        return muralExibitionDto;
    }

    public static MuralExibitionDto.UsuarioDto usuarioDto(Usuario usuario){
        if (usuario == null)return null;

        MuralExibitionDto.UsuarioDto usuarioDto = new MuralExibitionDto.UsuarioDto();

        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setTipo(usuario.getTipo());
        return usuarioDto;
    }
}
