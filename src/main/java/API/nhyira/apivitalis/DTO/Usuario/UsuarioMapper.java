package API.nhyira.apivitalis.DTO.Usuario;

import API.nhyira.apivitalis.Auth.Usuario.DTO.UsuarioTokenDto;
import API.nhyira.apivitalis.Entity.Endereco;
import API.nhyira.apivitalis.Entity.Ficha;
import API.nhyira.apivitalis.Entity.Meta;
import API.nhyira.apivitalis.Entity.Usuario;

import java.util.ArrayList;
import java.util.List;
public class UsuarioMapper {

    public static Usuario toDto(UsuarioCreateEditDto dto) {
        if (dto != null) {
            Usuario user = new Usuario();
            user.setNome(dto.getNome());
            user.setNickname(dto.getNickname());
            user.setCpf(dto.getCpf());
            user.setEmail(dto.getEmail());
            user.setSenha(dto.getSenha());
            user.setSexo(dto.getSexo());
            user.setDtNasc(dto.getDtNasc());
            user.setTipo(dto.getTipo() != null ?  Usuario.TipoUsuario.valueOf(dto.getTipo().name()) : null);
            return user;
        }
        return null;
    }

    public static UsuarioExibitionDto toExibition(Usuario entity) {
        if (entity != null) {
            UsuarioExibitionDto user = new UsuarioExibitionDto();

            user.setId(entity.getIdUsuario());
            user.setNome(entity.getNome());
            user.setNickname(entity.getNickname());
            user.setCpf(entity.getCpf());
            user.setEmail(entity.getEmail());
            user.setSexo(entity.getSexo());
            user.setDtNasc(entity.getDtNasc());
            user.setTipo(entity.getTipo());
            user.setMidia(entity.getMidiaId());
            user.setPersonalId(usuarioDto(entity.getPersonalId()));
            user.setAcademiaId(endercoDto(entity.getEnderecoId()));
            return user;
        }
        return null;
    }

    public static UsuarioExibitionDto toExibition(Usuario entity, Meta metaEntity) {
        if (entity != null) {
            UsuarioExibitionDto user = new UsuarioExibitionDto();

            user.setId(entity.getIdUsuario());
            user.setNome(entity.getNome());
            user.setNickname(entity.getNickname());
            user.setCpf(entity.getCpf());
            user.setEmail(entity.getEmail());
            user.setSexo(entity.getSexo());
            user.setDtNasc(entity.getDtNasc());
            user.setTipo(entity.getTipo());
            user.setMidia(entity.getMidiaId());
            user.setPersonalId(usuarioDto(entity.getPersonalId()));
            user.setAcademiaId(endercoDto(entity.getEnderecoId()));
            user.setMeta(toMetaDto(metaEntity));
            return user;
        }
        return null;
    }

    public static UsuarioFichaDto toExibitionIMC(Usuario entity, double imc){
        if (entity == null) return null;

            UsuarioFichaDto user = new UsuarioFichaDto();

            user.setIdUsuario(entity.getIdUsuario());
            user.setNome(entity.getNome());
            user.setNickname(entity.getNickname());
            user.setCpf(entity.getCpf());
            user.setEmail(entity.getEmail());
            user.setTipo(entity.getTipo());
            user.setDtNasc(entity.getDtNasc());
            user.setSexo(entity.getSexo());
            user.setIMC(imc);
            return user;
    }

    public static Usuario toEditDto(Usuario user, UsuarioCreateEditDto dto) {
        if (dto != null) {
            user.setNome(dto.getNome());
            user.setNickname(dto.getNickname());
            user.setCpf(dto.getCpf());
            user.setEmail(dto.getEmail());
            user.setSenha(dto.getSenha());
            user.setSexo(dto.getSexo());
            user.setDtNasc(dto.getDtNasc());
            user.setTipo(dto.getTipo() != null ? Usuario.TipoUsuario.valueOf(dto.getTipo().name()) : null);

            return user;
        }
        return null;
    }

    // public static List<UsuarioExibitionDto> toExibitionList(List<Usuario> entities) {
    //     List<UsuarioExibitionDto> dtos = new ArrayList<>();
    //     for (Usuario entity : entities) {
    //         UsuarioExibitionDto dto = toExibition(entity);
    //         if (dto != null) {
    //             dtos.add(dto);
    //         }
    //     }
    //     return dtos;
    // }

    public static Usuario of(UsuarioCreateEditDto usuarioCriacaoDto) {
        Usuario usuario = new Usuario();

        usuario.setNickname(usuarioCriacaoDto.getNickname());
        usuario.setCpf(usuarioCriacaoDto.getCpf());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setSexo(usuarioCriacaoDto.getSexo());
        usuario.setDtNasc(usuarioCriacaoDto.getDtNasc());
        usuario.setEmail(usuarioCriacaoDto.getEmail());
        usuario.setNome(usuarioCriacaoDto.getNome());
        usuario.setSenha(usuarioCriacaoDto.getSenha());
        usuario.setTipo(usuarioCriacaoDto.getTipo());
        return usuario;
    }

    public static UsuarioTokenDto of(Usuario user, String token) {
        UsuarioTokenDto usuario = new UsuarioTokenDto();
        usuario.setId(user.getIdUsuario());
        usuario.setNome(user.getNome());
        usuario.setEmail(user.getEmail());
        usuario.setNickname(user.getNickname());
        usuario.setTipo(user.getTipo());
        usuario.setToken(token);

        return usuario;
    }

    public static UsuarioDto usuarioDto(Usuario usuario){
        if (usuario == null)return null;
        UsuarioDto usuarioDto = new UsuarioDto();

        usuarioDto.setIdUsuario(usuario.getIdUsuario());
        usuarioDto.setNome(usuario.getNome());
        usuarioDto.setSexo(usuario.getSexo());
        usuarioDto.setEmail(usuario.getEmail());
        usuarioDto.setCpf(usuario.getEmail());
        usuarioDto.setTipo(usuario.getTipo());
        usuarioDto.setDtNasc(usuario.getDtNasc());
        usuarioDto.setNickname(usuario.getNickname());
        return usuarioDto;
    }

    public static UsuarioExibitionDto.EnderecoDto endercoDto(Endereco endereco) {
        if (endereco == null) return null;

        UsuarioExibitionDto.EnderecoDto enderecoDto = new UsuarioExibitionDto.EnderecoDto();
        enderecoDto.setId(endereco.getIdEndereco());
        enderecoDto.setLogradouro(endereco.getLogradouro());
        enderecoDto.setBairro(endereco.getBairro());
        enderecoDto.setCidade(endereco.getCidade());
        enderecoDto.setCep(endereco.getCep());
        enderecoDto.setComplemento(endereco.getComplemento());
        enderecoDto.setEstado(endereco.getEstado());
        enderecoDto.setNumero(endereco.getNumero());
        return enderecoDto;
    }

    public static UsuarioExibitionDto.MetaDto toMetaDto(Meta meta) {
        if (meta == null) return null;

        UsuarioExibitionDto.MetaDto metaDto = new UsuarioExibitionDto.MetaDto();
        metaDto.setId(meta.getIdMeta());
        metaDto.setNome(meta.getNome());
        return metaDto;
    }
}
