package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.Entity.Usuario;

public class UsuarioMapper {

    public static Usuario toDto(UsuarioCreateDto dto) {
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
            user.setAltura(dto.getAltura());
            user.setPeso((dto.getPeso()));
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
            user.setAltura(entity.getAltura());
            user.setPeso((entity.getPeso()));
            user.setMeta(entity.getMeta());
            user.setMidia(entity.getMidia());

            return user;
        }
        return null;
    }

    public static Usuario toEditDto(Usuario user, UsuarioCreateDto dto) {
        if (dto != null) {
            user.setNome(dto.getNome());
            user.setUsername(dto.getUsername());
            user.setCpf(dto.getCpf());
            user.setEmail(dto.getEmail());
            user.setEmail2(dto.getEmail2());
            user.setSenha(dto.getSenha());
            user.setGenero(dto.getGenero());
            user.setDtNasc(dto.getDtNasc());
            user.setAltura(dto.getAltura());
            user.setPeso((dto.getPeso()));
            user.setMeta(dto.getMeta());
            user.setMidia(dto.getMidia());

            return user;
        }
        return null;
    }

    public static UsuarioTokenDto of(Usuario user, String token) {
        UsuarioTokenDto usuario = new UsuarioTokenDto();

        usuario.setEmail(user.getEmail());
        usuario.setUsername(user.getUsername());
        usuario.setToken(token);

        return usuario;
    }
}
