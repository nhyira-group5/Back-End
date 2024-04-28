package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.ArrayList;
import java.util.List;
public class UsuarioMapper {

    public static Usuario toDto(UsuarioCreateEditDto dto) {
        if (dto != null) {
            Usuario user = new Usuario();
            user.setNome(dto.getNome());
            user.setUsername(dto.getUsername());
            user.setCpf(dto.getCpf());
            user.setEmail(dto.getEmail());
            user.setEmail2(dto.getEmail2());
            user.setSenha(dto.getSenha());
            user.setGenero(dto.getGenero());
            user.setDtNasc(dto.getDtNasc());
            user.setTipo(dto.getTipo() != null ?  Usuario.TipoUsuario.valueOf(dto.getTipo().name()) : null);
            user.setMeta(dto.getMeta());
            user.setMidia(dto.getMidia());

            return user;
        }
        return null;
    }

    public static UsuarioExibitionDto toExibition(Usuario entity) {
        if (entity != null) {
            UsuarioExibitionDto user = new UsuarioExibitionDto();
            user.setNome(entity.getNome());
            user.setUsername(entity.getUsername());
            user.setCpf(entity.getCpf());
            user.setEmail(entity.getEmail());
            user.setEmail2(entity.getEmail2());
            user.setGenero(entity.getGenero());
            user.setDtNasc(entity.getDtNasc());
            user.setTipo(entity.getTipo());
            user.setMeta(entity.getMeta());
            user.setMidia(entity.getMidia());
            user.setSenha(entity.getSenha());

            return user;
        }
        return null;
    }

    public static Usuario toEditDto(Usuario user, UsuarioCreateEditDto dto) {
        if (dto != null) {
            user.setNome(dto.getNome());
            user.setUsername(dto.getUsername());
            user.setCpf(dto.getCpf());
            user.setEmail(dto.getEmail());
            user.setEmail2(dto.getEmail2());
            user.setSenha(dto.getSenha());
            user.setGenero(dto.getGenero());
            user.setDtNasc(dto.getDtNasc());
            user.setTipo(dto.getTipo() != null ? Usuario.TipoUsuario.valueOf(dto.getTipo().name()) : null);
            user.setMeta(dto.getMeta());
            user.setMidia(dto.getMidia());

            return user;
        }
        return null;
    }

    public static List<UsuarioExibitionDto> toExibitionList(List<Usuario> entities) {
        List<UsuarioExibitionDto> dtos = new ArrayList<>();
        for (Usuario entity : entities) {
            UsuarioExibitionDto dto = toExibition(entity);
            if (dto != null) {
                dtos.add(dto);
            }
        }
        return dtos;
    }


    public static Usuario of(UsuarioCreateEditDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setUsername(usuarioCriacaoDto.getUsername());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setEmail2(usuarioCriacaoDto.getEmail2());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setGenero(usuarioCriacaoDto.getGenero());
        usuario.setDtNasc(usuarioCriacaoDto.getDtNasc());
        usuario.setMeta(usuarioCriacaoDto.getMeta());
        usuario.setMidia(usuarioCriacaoDto.getMidia());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setUsername(usuarioCriacaoDto.getUsername());
        usuario.setTipo(usuarioCriacaoDto.getTipo());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario user, String token) {
        UsuarioTokenDto usuario = new UsuarioTokenDto();
        usuario.setId(user.getIdUsuario());
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setUsername(user.getUsername());
        usuario.setTipo(user.getTipo());
        usuario.setToken(token);

        return usuario;
    }
}
