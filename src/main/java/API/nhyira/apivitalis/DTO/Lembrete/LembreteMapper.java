package API.nhyira.apivitalis.DTO.Lembrete;

import API.nhyira.apivitalis.DTO.Usuario.UsuarioDto;
import API.nhyira.apivitalis.Entity.Lembrete;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.List;

public class LembreteMapper {

    public static Lembrete toEntity(LembreteCreateEditDto lembreteCreateEditDto){
        if (lembreteCreateEditDto == null)return null;
        Lembrete lembrete = new Lembrete();
        lembrete.setDataLembrete(lembreteCreateEditDto.getDataLembrete());
        lembrete.setConteudo(lembreteCreateEditDto.getConteudo());
        return lembrete;
    }


    public static LembreteExibitionDto toDto(Lembrete lembrete){
        if (lembrete == null)return null;
        LembreteExibitionDto lembreteExibitionDto = new LembreteExibitionDto();
        lembreteExibitionDto.setId(lembrete.getIdLembrete());
        lembreteExibitionDto.setDataLembrete(lembrete.getDataLembrete());
        lembreteExibitionDto.setConteudo(lembrete.getConteudo());
        return lembreteExibitionDto;
    }


    public static List<LembreteExibitionDto> toDto(List<Lembrete> lembretes){
        return lembretes.stream().map(LembreteMapper::toDto).toList();
    }


    public static UsuarioDto usuarioDto(Usuario usuario){
        if (usuario == null)return null;
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setCpf(usuario.getCpf());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setNickname(usuario.getNickname());
        usuarioDto.setDtNasc(usuario.getDtNasc());
        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        return usuarioDto;
    }

}
